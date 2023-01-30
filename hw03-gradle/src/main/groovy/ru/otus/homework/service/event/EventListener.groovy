package ru.otus.homework.service.event

import ru.otus.homework.model.Event

interface EventListener {
    void notify(Event event)
}
