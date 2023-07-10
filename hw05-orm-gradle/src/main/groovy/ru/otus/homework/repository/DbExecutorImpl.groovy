package ru.otus.homework.repository

import java.sql.Statement
import java.sql.Connection
import static java.sql.Types.VARCHAR
import ru.otus.homework.exception.DataBaseOperationException

class DbExecutorImpl implements DbExecutor {

    @Override
    long executeStatement(Connection connection, String sql, List<Object> params) {
        try (def pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for (def idx = 0; idx < params.size(); idx++) {
                pst.setObject(idx + 1, params.get(idx), VARCHAR)
            }
            pst.executeUpdate()
            try (def rs = pst.getGeneratedKeys()) {
                rs.next()
                return rs.getInt(1)
            }
        } catch (ex) {
            throw new DataBaseOperationException("executeInsert error", ex)
        }
    }

    @Override
    <T> Optional<T> executeSelect(Connection connection, String sql, Closure<T> rsHandler) {
        try (def pst = connection.prepareStatement(sql)) {
            try (def rs = pst.executeQuery()) {
                return Optional.ofNullable(rsHandler.call(rs))
            }
        } catch (ex) {
            throw new DataBaseOperationException("executeSelect error", ex)
        }
    }
}