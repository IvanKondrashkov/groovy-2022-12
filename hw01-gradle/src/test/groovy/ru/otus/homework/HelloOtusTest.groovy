package ru.otus.homework

import org.junit.jupiter.api.Test
import static org.junit.jupiter.api.Assertions.assertEquals

class HelloOtusTest {
    @Test
    void setEmail() {
        HelloOtus otus = new HelloOtus("Djon", "Doe")
        otus.setEmail(otus.getName(), otus.getSurname(), EMAIL.MAIL)

        assertEquals(otus.getName(), "Djon")
        assertEquals(otus.getSurname(), "Doe")
        assertEquals(otus.getEmail(), "djondoe@mail.ru")
    }
}
