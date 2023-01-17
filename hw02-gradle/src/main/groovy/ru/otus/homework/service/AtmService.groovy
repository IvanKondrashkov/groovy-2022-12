package ru.otus.homework.service

import ru.otus.homework.model.Atm

interface AtmService {
    Atm plus(Atm other)
    Atm minus(Atm other)
}
