package ru.otus.homework.model

class Money {
    int amount
    Currency currency

    Money(amount = 0, currency) {
        this.amount = amount
        this.currency = currency
    }

    boolean equals(Object other) {
        if (null == other) return null
        if (!(other instanceof Money)) return false
        if (amount != other.amount) return false
        if (currency != other.currency) return false
        return true
    }

    int hashCode() {
        return amount.hashCode() + currency.hashCode()
    }
}
