package ru.otus.homework.service

import java.time.LocalDate
import java.time.LocalDateTime
import ru.otus.homework.model.Task
import ru.otus.homework.model.Event
import ru.otus.homework.model.Action
import ru.otus.homework.storage.TaskStorage
import ru.otus.homework.service.task.TaskService
import ru.otus.homework.storage.ActionStorage
import ru.otus.homework.service.action.ActionService
import ru.otus.homework.exception.ValidateException
import ru.otus.homework.exception.NotFoundException

class ManagerServiceImpl implements TaskService, TaskStorage, ActionService, ActionStorage {
    @Override
    Task createTask(Task task) {
        validateTask(task)
        task.id = currentTaskId.incrementAndGet()
        if (!tasks[task.id]) {
            tasks[task.id] = task
        }
        return task
    }

    @Override
    void deleteTask(Long id) {
        if (tasks[id]) {
            tasks.remove(id)
        }
    }

    @Override
    Task findTask(Long id) {
        if (tasks[id]) {
            return tasks[id]
        }
        throw new NotFoundException("Task by id=$id not found!")
    }

    @Override
    List<Task> findAllTaskByDate(LocalDate date) {
        return tasks.collect {
            if (it.value.start.toLocalDate().isEqual(date)) {
                it.value
            }
        }
    }

    @Override
    int numbersOfTaskByDate(LocalDate date) {
        return findAllTaskByDate(date).size()
    }

    @Override
    Action createAction(Action action) {
        validateAction(action)
        fireNotify(action)
        action.id = currentActionId.incrementAndGet()
        if (!actions[action.id]) {
            actions[action.id] = action
        }
        Task task = tasks[action.taskId]
        task.actions.with {
            it.add(action)
        }
        return action
    }

    @Override
    void deleteAction(Long id) {
        if (actions[id]) {
            Action action = actions[id]
            Task task = tasks[action.taskId]
            task.actions.with {
                it.remove(action)
            }
            actions.remove(id)
        }
    }

    @Override
    Action findAction(Long id) {
        if (actions[id]) {
            return actions[id]
        }
        throw new NotFoundException("Action by id=$id not found!")
    }

    @Override
    List<Action> findAllActionByDate(LocalDate date) {
        return actions.collect {
            if (it.value.start.toLocalDate().isEqual(date)) {
                it.value
            }
        }
    }

    @Override
    int numbersOfActionByDate(LocalDate date) {
        return findAllActionByDate(date).size()
    }

    static void validateTask(Task task) {
        tasks.each {
            if (it.value.start.isAfter(task.start) && it.value.end.isAfter(task.end)) {
                throw new ValidateException("""
                Task it.start=$it.value.start is after task.start=$task.start and it.end=$it.value.end is after task.end=$task.end
                """)
            }
        }
    }

    static void validateAction(Action action) {
        Task task = tasks[action.taskId]
        actions.each {
            if (it.value.start.isAfter(action.start) && it.value.end.isAfter(action.end)) {
                throw new ValidateException("""
                Action it.start=$it.value.start is after action.start=$action.start and action it.end=$it.value.end is after action.end=$action.end
                """)
            }
        }
        if (task.start.isAfter(action.start) || task.end.isBefore(action.end)) {
            throw new ValidateException("""
            Task start=$task.start is after action start=$action.start or task end=$task.end is before action=$action.end
            """)
        }
    }

    static void fireNotify(Action action) {
        action.fireNotify(new Event(action.name, LocalDateTime.now()))
        if (action.start.isBefore(LocalDateTime.now())) {
            action.addEventListener(event -> {
                println "An event has come ${event.name}, ${event.created}"
            })
        }
    }
}
