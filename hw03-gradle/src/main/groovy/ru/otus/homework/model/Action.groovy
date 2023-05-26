package ru.otus.homework.model

import groovy.transform.ToString
import java.time.LocalDateTime
import ru.otus.homework.service.action.ActionExecutable

@ToString
class Action implements ActionExecutable {
    Long id
    String name
    String description
    LocalDateTime start
    LocalDateTime end
    Long taskId

    Action(String name, String description, LocalDateTime start, LocalDateTime end, Long taskId) {
        this.name = name
        this.description = description
        this.start = start
        this.end = end
        this.taskId = taskId
    }

    @Override
    void execute() {
        LocalDateTime currentTime = LocalDateTime.now()
        if (start.isAfter(currentTime)) {
            println "Execute ${new Event(name, currentTime)}"
        }
    }
}