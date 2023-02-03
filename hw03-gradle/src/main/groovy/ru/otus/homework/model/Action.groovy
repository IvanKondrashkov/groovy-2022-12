package ru.otus.homework.model

import groovy.transform.ToString
import groovy.beans.ListenerList
import java.time.LocalDateTime
import ru.otus.homework.service.event.EventListener

@ToString
class Action {
    Long id
    String name
    String description
    LocalDateTime start
    LocalDateTime end
    Long taskId
    @ListenerList
    List<EventListener> events

    Action(String name, String description, LocalDateTime start, LocalDateTime end, Long taskId) {
        this.name = name
        this.description = description
        this.start = start
        this.end = end
        this.taskId = taskId
    }
}
