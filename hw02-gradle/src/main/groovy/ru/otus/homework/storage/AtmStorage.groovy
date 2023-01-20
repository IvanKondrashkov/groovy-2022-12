package ru.otus.homework.storage

import ru.otus.homework.model.Currency

trait AtmStorage {
    private static def atm = [
            (Currency.RUB) : [10 : 0, 50 : 0, 100 : 0, 200 : 0, 500 : 0, 1000 : 0, 2000 : 0, 5000 : 0],
            (Currency.EUR) : [5 : 0, 10 : 0, 20 : 0, 50 : 0, 100 : 0, 200 : 0, 500 : 0],
            (Currency.USD) : [1 : 0, 2 : 0, 5 : 0, 10 : 0, 20 : 0, 50 : 0, 100 : 0, 500 : 0, 1000 : 0]
    ]

    Closure pushAtm = { int amount, Currency currency ->
        if (atm[currency].containsKey(amount)) {
            atm[currency][amount] = atm[currency][amount] + 1
        } else {
            atm[currency].reverseEach {entry ->
                int rem = amount % entry.key
                if (rem != 0) {
                    if (atm[currency].containsKey(amount - rem)) {
                        atm[currency][amount - rem] = atm[currency][amount - rem] + 1
                        pushAtm(rem, currency)
                    }
                }
            }
        }
    }

    Closure popAtm = { int amount, Currency currency ->
        if (balance(currency) < amount) {
            throw new IllegalArgumentException("Your balance=${balance(currency)} is less than the requested amount=$amount!")
        }
        if (atm[currency].containsKey(amount)) {
            atm[currency][amount] = atm[currency][amount] - 1
        } else {
            atm[currency].reverseEach {entry ->
                int rem = amount % entry.key
                if (rem != 0) {
                    if (atm[currency].containsKey(amount - rem)) {
                        atm[currency][amount - rem] = atm[currency][amount - rem] - 1
                        popAtm(rem, currency)
                    }
                }
            }
        }
    }

    Closure balance = { Currency currency ->
        int sum = 0
        atm[currency].each {entry ->
            if (entry.value != 0) sum += entry.key * entry.value
        }
        return sum
    }

    Closure clear = { Currency currency ->
        atm[currency] = atm[currency].collectEntries {entry -> [entry.key, 0]}
    }
}
