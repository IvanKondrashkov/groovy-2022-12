package ru.otus.homework.model

class Card {
    String account
    Money money

    Card(account = '', money) {
        this.account = account ? account : createAccount()
        this.money = money
    }

    boolean equals(Object other) {
        if (null == other) return null
        if (!(other instanceof Card)) return false
        if (account != other.account) return false
        if (money != other.money) return false
        return true
    }

    int hashCode() {
        return account.hashCode() + money.hashCode()
    }

    String createAccount() {
        return account ?= ('0'..'9').shuffled().take(16).join()
    }
}
