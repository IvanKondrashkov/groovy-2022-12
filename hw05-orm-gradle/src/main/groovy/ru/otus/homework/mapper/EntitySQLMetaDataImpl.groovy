package ru.otus.homework.mapper

class EntitySQLMetaDataImpl<T> implements EntitySQLMetaData<T> {
    private final EntityClassMetaData<T> entityClassMetaData

    EntitySQLMetaDataImpl(entityClassMetaData) {
        this.entityClassMetaData = entityClassMetaData
    }

    @Override
    String getSelectAllSql() {
        def selectAllQuery = "SELECT * FROM ${entityClassMetaData.getName()}"
        return selectAllQuery
    }

    @Override
    String getSelectByIdSql(long id) {
        def selectByIdQuery = "SELECT * FROM ${entityClassMetaData.getName()} WHERE ${entityClassMetaData.getIdField().name} = ${id}"
        return selectByIdQuery
    }

    @Override
    String getInsertSql(Object object) {
        def names = []
        def values = []
        def properties = object.properties
        properties.each {entry ->
            if (entityClassMetaData.getFieldsWithoutId().collect {it.name}.contains(entry.key)) {
                names << entry.key
                values << entry.value
            }
        }
        def insertQuery = "INSERT INTO ${entityClassMetaData.getName()} (${names.join(",")}) VALUES(${values.collect {'?'}.join(",")})"
        return insertQuery
    }

    @Override
    String getUpdateSql(Object object) {
        def properties = object.properties
        def updateQuery = "UPDATE ${entityClassMetaData.getName()} SET ${getUpdateFields(properties)} WHERE ${entityClassMetaData.getIdField().name} = ${properties.get(entityClassMetaData.getIdField().name)}"
        return updateQuery
    }

    def getUpdateFields(properties) {
        return properties
                .findAll {entry -> entityClassMetaData.getFieldsWithoutId().collect {it.name}.contains(entry.key)}
                .collect {entry -> "${entry.key} = ?"}.join(",")
    }
}