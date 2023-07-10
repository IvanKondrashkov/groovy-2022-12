package ru.otus.homework.service

import ru.otus.homework.model.Manager

interface DBServiceManager {
    Manager saveManager(Manager manager)

    Manager getManager(long no)

    List<Manager> findAll()
}