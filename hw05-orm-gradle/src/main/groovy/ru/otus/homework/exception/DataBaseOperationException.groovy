package ru.otus.homework.exception

class DataBaseOperationException extends RuntimeException {
    DataBaseOperationException(String message, Throwable cause) {
        super(message, cause)
    }
}