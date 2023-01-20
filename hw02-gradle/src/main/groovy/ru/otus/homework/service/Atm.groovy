package ru.otus.homework.service

import ru.otus.homework.model.Card
import ru.otus.homework.model.Money
import ru.otus.homework.storage.AtmStorage

class Atm implements AtmService, AtmStorage {
    private Card card

    Atm(Card card) {
        this.card = card
    }

    @Override
    Money plus(Money other) {
        if (null == other) return null
        if (null == card.account) throw new IllegalAccessException("Account is not available!")
        if (other.currency != card.money.currency) throw new IllegalArgumentException("Currency account is not available!")
        pushAtm(other.amount, other.currency)
        card.money.amount += other.amount
        def currency = card.money.currency
        return new Money(balance(currency), currency)
    }

    @Override
    Money minus(Money other) {
        if (null == other) return null
        if (null == card.account) throw new IllegalAccessException("Account is not available!")
        if (other.currency != card.money.currency) throw new IllegalArgumentException("Currency account is not available!")
        popAtm(other.amount, other.currency)
        card.money.amount -= other.amount
        def currency = card.money.currency
        return new Money(balance(currency), currency)
    }
}
