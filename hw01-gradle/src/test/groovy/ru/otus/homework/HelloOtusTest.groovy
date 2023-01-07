package ru.otus.homework

import org.junit.jupiter.api.Test
import static org.junit.jupiter.api.Assertions.assertEquals

class HelloOtusTest {

    @Test
    void setData() {
        HelloOtus otus = new HelloOtus()
        otus.setData(10, 30)

        assertEquals(10, otus.start)
        assertEquals(30, otus.end)
    }
}
