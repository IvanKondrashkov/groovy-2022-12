package ru.otus.homework.service.task

import java.time.LocalDate
import ru.otus.homework.model.Task

interface TaskService {
    Task createTask(Task task)
    void deleteTask(Long id)
    Task findTask(Long id)
    List<Task> findAllTaskByDate(LocalDate date)
    int numbersOfTaskByDate(LocalDate date)
}
