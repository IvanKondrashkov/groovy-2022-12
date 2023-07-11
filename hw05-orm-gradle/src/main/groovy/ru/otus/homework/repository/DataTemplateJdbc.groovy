package ru.otus.homework.repository

import groovy.transform.Canonical
import ru.otus.homework.mapper.ResultListMapper
import ru.otus.homework.mapper.EntityClassMetaData
import ru.otus.homework.mapper.EntitySQLMetaData

@Canonical
class DataTemplateJdbc<T> implements DataTemplate<T> {
    private DbExecutor dbExecutor
    private EntitySQLMetaData entitySQLMetaData
    private EntityClassMetaData entityClassMetaData

    @Override
    def findById(connection, id) {
        return dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectByIdSql(id), {new ResultListMapper<>(entityClassMetaData).apply(it)})
    }

    @Override
    def findAll(connection) {
        return dbExecutor.executeSelect(connection, entitySQLMetaData.getSelectAllSql(), {new ResultListMapper<>(entityClassMetaData).apply(it)})
    }

    @Override
    int insert(connection, object) {
        return dbExecutor.executeStatement(connection, entitySQLMetaData.getInsertSql(object), getListParams(object, entityClassMetaData))
    }

    @Override
    void update(connection, object) {
        dbExecutor.executeStatement(connection, entitySQLMetaData.getUpdateSql(object), getListParams(object, entityClassMetaData))
    }

    static def getListParams(Object object, EntityClassMetaData entityClassMetaData) {
        def values = []
        object.properties.each {entry ->
            if (entityClassMetaData.getFieldsWithoutId().collect {it.name}.contains(entry.key)) {
                values << entry.value
            }
        }
        return values
    }
}