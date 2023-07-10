package ru.otus.homework.sessionmanager

import groovy.sql.Sql
import javax.sql.DataSource
import java.util.concurrent.Callable
import ru.otus.homework.exception.DataBaseOperationException

class TransactionRunnerJdbc implements TransactionRunner {
    private final DataSource dataSource
    private final Sql connection

    TransactionRunnerJdbc(DataSource dataSource) {
        this.dataSource = dataSource
        this.connection = new Sql(dataSource)
    }

    @Override
    <T> T doInTransaction(Closure<T> action) {
        return wrapException(() -> {
            try (def connection = dataSource.getConnection()) {
                try {
                    def result = action.call(connection)
                    connection.commit()
                    return result
                } catch (ex) {
                    connection.rollback()
                    throw new DataBaseOperationException("doInTransaction ru.otus.homework.exception", ex)
                }
            }
        })
    }

    private <T> T wrapException(Callable<T> action) {
        try {
            return action.call()
        } catch (Exception ex) {
            throw new DataBaseOperationException("ru.otus.homework.exception", ex)
        }
    }
}