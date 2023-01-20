package ru.otus.homework.service

import ru.otus.homework.model.Money

interface AtmService {
    Money plus(Money other)
    Money minus(Money other)
}
