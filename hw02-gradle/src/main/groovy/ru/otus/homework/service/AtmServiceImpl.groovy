package ru.otus.homework.service

import ru.otus.homework.model.Atm
import ru.otus.homework.storage.AtmStorage

class AtmServiceImpl implements AtmService, AtmStorage {
    private Atm atm

    AtmServiceImpl(Atm atm) {
        this.atm = atm
    }

    @Override
    Atm plus(Atm other) {
        if (null == other) return null
        if (other.score != atm.score) throw new IllegalAccessException("Score account is not available!")
        if (other.currency != atm.currency) throw new IllegalArgumentException("Currency account is not available!")
        pushAtm(other.amount)
        def result = new Atm(atm.amount + other.amount, atm.currency, atm.score)
        this.atm.amount += other.amount
        return result
    }

    @Override
    Atm minus(Atm other) {
        if (null == other) return null
        if (other.score != atm.score) throw new IllegalAccessException("Score account is not available!")
        if (other.currency != atm.currency) throw new IllegalArgumentException("Currency account is not available!")
        popAtm(other.amount)
        def result = new Atm(atm.amount - other.amount, atm.currency, atm.score)
        this.atm.amount -= other.amount
        return result
    }
}
