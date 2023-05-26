package ru.otus.homework.storage

import java.util.concurrent.atomic.AtomicLong

trait ActionStorage {
    static def actions = [:]
    static AtomicLong currentActionId = new AtomicLong()

    void clearActions() {
        currentActionId = new AtomicLong()
        actions.clear()
    }
}
