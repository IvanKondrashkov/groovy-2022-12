package ru.otus.homework.service

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import ru.otus.homework.model.Atm
import ru.otus.homework.model.Currency
import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertThrows

class AtmServiceImplTest {
    private Atm atm
    private AtmServiceImpl service

    @BeforeEach
    void init() {
        atm = new Atm(Currency.RUB)
        service = new AtmServiceImpl(atm)
    }

    @AfterEach
    void tearDown() {
        atm = null
        service = null
    }

    @Test
    void plus() {
        Atm atm1 = new Atm(100, Currency.RUB, atm.score)
        atm = service + atm1

        assertEquals(atm.amount, 100)
    }

    @Test
    void minus() {
        Atm atm1 = new Atm(100, Currency.RUB, atm.score)
        atm = service + atm1
        Atm atm2 = new Atm(300, Currency.RUB, atm.score)
        atm = service + atm2
        Atm atm3 = new Atm(300, Currency.RUB, atm.score)
        atm = service - atm3

        assertEquals(atm.amount, 100)
    }

    @Test
    void shouldBeReturnException() {
        Atm atm1 = new Atm(1000, Currency.RUB, atm.score)

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            atm = service - atm1
        })

        String expectedMessage = exception.getMessage();
        String actualMessage = "Your balance=${service.balance} is less than the requested amount=${atm1.amount}!"

        assertEquals(expectedMessage, actualMessage);
    }
}
