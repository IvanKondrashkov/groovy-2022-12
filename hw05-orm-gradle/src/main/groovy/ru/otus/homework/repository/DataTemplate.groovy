package ru.otus.homework.repository

/**
 * CRUD операции (like JdbcTemplate)
 */
interface DataTemplate<T> {
    def findById(connection, id)

    def findAll(connection)

    int insert(connection, object)

    void update(connection, object)
}