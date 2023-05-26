package ru.otus.homework.model

import groovy.transform.ToString
import java.time.LocalDateTime

@ToString
class Task {
    Long id
    String name
    String description
    LocalDateTime start
    LocalDateTime end
    List<Action> actions

    Task(String name, String description, LocalDateTime start, LocalDateTime end) {
        this.name = name
        this.description = description
        this.start = start
        this.end = end
        this.actions = []
    }
}
