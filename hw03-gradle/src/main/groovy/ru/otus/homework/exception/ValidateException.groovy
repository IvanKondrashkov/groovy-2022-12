package ru.otus.homework.exception

class ValidateException extends RuntimeException {
    ValidateException(String message) {
        super(message)
    }

    ValidateException(String message, Throwable cause) {
        super(message, cause)
    }
}
