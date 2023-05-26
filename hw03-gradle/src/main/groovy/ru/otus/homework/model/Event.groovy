package ru.otus.homework.model

import groovy.transform.ToString
import java.time.LocalDateTime

@ToString
class Event {
    String name
    LocalDateTime created

    Event(String name, LocalDateTime created) {
        this.name = name
        this.created = created
    }
}
