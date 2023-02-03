package ru.otus.homework.service

import java.time.LocalDate
import java.time.LocalDateTime
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import ru.otus.homework.model.Task
import ru.otus.homework.model.Action
import ru.otus.homework.exception.NotFoundException
import static org.junit.jupiter.api.Assertions.assertThrows

class ManagerServiceImplTest {
    private ManagerServiceImpl service
    private Task task
    private Action action

    @BeforeEach
    void init() {
        service = new ManagerServiceImpl()
        task = new Task("Cook dinner!", "Cook soup and make tea!", LocalDateTime.now(), LocalDateTime.now().plusMinutes(30))
        task = service.createTask(task)
        action = new Action("Cook soup!", "10 minutes", LocalDateTime.now(), LocalDateTime.now().plusMinutes(10), task.id)
    }

    @AfterEach
    void tearDown() {
        service.clearTasks()
        service.clearActions()
    }

    @Test
    void createTask() {
        assert task.id == 1
    }

    @Test
    void deleteTask() {
        assert task.id == 1

        service.deleteTask(task.id)

        Exception ex = assertThrows(NotFoundException) {
            task = service.findTask(1)
        }

        def message = ex.getMessage()
        assert message == "Task by id=1 not found!"
    }

    @Test
    void findTask() {
        assert task.id == 1

        task = service.findTask(task.id)

        assert task.id == 1
        assert task.name == "Cook dinner!"
        assert task.description == "Cook soup and make tea!"
    }

    @Test
    void findTaskNotValidId() {
        assert task.id == 1

        Exception ex = assertThrows(NotFoundException) {
            task = service.findTask(2)
        }

        def message = ex.getMessage()
        assert message == "Task by id=2 not found!"
    }

    @Test
    void findAllTaskByDate() {
        assert task.id == 1

        def tasks = service.findAllTaskByDate(LocalDate.now())
        assert tasks.size() == 1
    }

    @Test
    void numbersOfTaskByDate() {
        assert task.id == 1

        def count = service.numbersOfTaskByDate(LocalDate.now())
        assert count == 1
    }

    @Test
    void createAction() {
        assert task.id == 1

        action = service.createAction(action)
        assert action.id == 1
    }

    @Test
    void deleteAction() {
        assert task.id == 1

        action = service.createAction(action)
        assert action.id == 1

        service.deleteAction(action.id)

        Exception ex = assertThrows(NotFoundException) {
            action = service.findAction(1)
        }

        def message = ex.getMessage()
        assert message == "Action by id=1 not found!"
    }

    @Test
    void findAction() {
        assert task.id == 1

        action = service.createAction(action)
        assert action.id == 1

        action = service.findAction(action.id)

        assert action.id == 1
        assert action.name == "Cook soup!"
        assert action.description == "10 minutes"
    }

    @Test
    void findActionNotValidId() {
        assert task.id == 1

        Exception ex = assertThrows(NotFoundException) {
            action = service.findAction(1)
        }

        def message = ex.getMessage()
        assert message == "Action by id=1 not found!"
    }

    @Test
    void findAllActionByDate() {
        assert task.id == 1

        action = service.createAction(action)
        assert action.id == 1

        def actions = service.findAllActionByDate(LocalDate.now())
        assert actions.size() == 1
    }

    @Test
    void numbersOfActionByDate() {
        assert task.id == 1

        action = service.createAction(action)
        assert action.id == 1

        def count = service.numbersOfActionByDate(LocalDate.now())
        assert count == 1
    }
}
