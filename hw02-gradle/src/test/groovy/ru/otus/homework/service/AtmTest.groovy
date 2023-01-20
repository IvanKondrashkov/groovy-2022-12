package ru.otus.homework.service

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import ru.otus.homework.model.Card
import ru.otus.homework.model.Money
import ru.otus.homework.model.Currency
import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertThrows

class AtmTest {
    private Money money
    private Card card
    private Atm service

    @BeforeEach
    void init() {
        money = new Money(Currency.RUB)
        card = new Card(money)
        service = new Atm(card)
    }

    @AfterEach
    void tearDown() {
        service.clear(money.currency)
    }

    @Test
    void plus() {
        Money money1 = new Money(100, Currency.RUB)
        money = service + money1

        assertEquals(service.balance(money.currency), 100)
    }

    @Test
    void minus() {
        Money money1 = new Money(100, Currency.RUB)
        money = service + money1
        Money money2 = new Money(300, Currency.RUB)
        money = service + money2
        Money money3 = new Money(200, Currency.RUB)
        money = service - money3

        assertEquals(service.balance(money.currency), 200)
    }

    @Test
    void takeByNotValidAmount() {
        Money money1 = new Money(1000, Currency.RUB)

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            money = service - money1
        })

        String expectedMessage = exception.getMessage()
        String actualMessage = "Your balance=${service.balance(money.currency)} is less than the requested amount=${money1.amount}!"

        assertEquals(expectedMessage, actualMessage)
    }

    @Test
    void balance() {
        Money money1 = new Money(100, Currency.RUB)
        money = service + money1

        assertEquals(service.balance(money.currency), 100)
    }

    @Test
    void clear() {
        Money money1 = new Money(100, Currency.RUB)
        money = service + money1
        service.clear(money.currency)

        assertEquals(service.balance(money.currency), 0)
    }
}
