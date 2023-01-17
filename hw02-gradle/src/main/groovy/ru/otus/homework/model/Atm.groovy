package ru.otus.homework.model

class Atm {
    private int amount
    private Currency currency
    private String score

    Atm(amount = 0, currency, score = '') {
        this.amount = amount
        this.currency = currency
        this.score = score ? score : createAccount()
    }

    boolean equals(Object other) {
        if (null == other) return null
        if (! (other instanceof Atm)) return false
        if (score != other.score) return false
        if (currency != other.currency) return false
        if (amount != other.amount) return false
        return true
    }

    int hashCode() {
        return score.hashCode() + currency.hashCode() + amount.hashCode()
    }

    int getAmount() {
        return amount
    }

    Currency getCurrency() {
        return currency
    }

    String getScore() {
        return score
    }

    void setAmount(int amount) {
        this.amount = amount
    }

    String createAccount() {
        return score ?= ('0'..'9').shuffled().take(16).join()
    }
}
