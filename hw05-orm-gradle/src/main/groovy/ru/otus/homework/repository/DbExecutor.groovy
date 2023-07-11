package ru.otus.homework.repository

import java.sql.Connection

interface DbExecutor {
    long executeStatement(Connection connection, String sql, List<Object> params)

    <T> Optional<T> executeSelect(Connection connection, String sql, Closure<T> rsHandler)
}