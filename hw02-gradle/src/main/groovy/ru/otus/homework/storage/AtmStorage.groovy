package ru.otus.homework.storage

trait AtmStorage {
    private static def atm = [
            100 : 0,
            200 : 0,
            500 : 0,
            1000 : 0,
            2000 : 0,
            5000 : 0
    ]

    Closure pushAtm = { int amount ->
        int count = 0
        if (atm.containsKey(amount)) {
            atm[amount] = ++count
            return count.is(1)
        }
        atm.reverseEach {entry ->
            int rem = amount % entry.getKey()
            if (rem != 0) {
                if (atm.containsKey(amount - rem)) {
                    atm[amount] = ++count
                    pushAtm(rem)
                }
            }
        }
    }

    Closure popAtm = { int amount ->
        int count = 0
        if (balance() < amount) {
            throw new IllegalArgumentException("Your balance=$balance is less than the requested amount=$amount!")
        }
        if (atm.containsKey(amount)) {
            atm[amount] = count--
            return count.is(0)
        }
        atm.reverseEach {entry ->
            int rem = amount % entry.getKey()
            if (rem != 0) {
                if (atm.containsKey(amount - rem)) {
                    atm[amount] = count--
                    popAtm(rem)
                }
            }
        }
    }

    Closure balance = {
        int sum = 0
        atm.each {entry ->
            if (entry.getValue() != 0) sum += entry.getKey() * entry.getValue()
        }
        return sum
    }
}
