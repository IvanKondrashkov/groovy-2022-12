package ru.otus.homework.mapper

import java.lang.reflect.Field
import java.lang.reflect.Modifier
import java.lang.reflect.Constructor
import ru.otus.homework.annotation.Id
import ru.otus.homework.exception.EntityMetaDataException

class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {
    Class<T> clazz
    Field idField
    List<Field> allFields
    List<Field> withoutIdFields
    Constructor<T> constructor

    EntityClassMetaDataImpl(Class<T> clazz) {
        this.clazz = clazz
        this.idField = findIdField()
        this.allFields = findAllFields()
        this.withoutIdFields = findWithoutIdFields()
        this.constructor = findFirstConstructorWithoutParameters()
    }

    def findIdField() {
        if (checkFieldId()) {
            def fields = findAllFields()
                    .findAll {it.isAnnotationPresent(Id.class)}
                    .collect()
            return fields.first()
        }
    }

    def findAllFields() {
        def fields = clazz.getDeclaredFields()
                .findAll { Modifier.isPrivate(it.getModifiers())}
                .findAll {!Modifier.isStatic(it.getModifiers())}
                .findAll {!Modifier.isTransient(it.getModifiers())}
                .collect()
        return fields
    }

    def findWithoutIdFields() {
        if (checkFieldId()) {
            def fields = findAllFields()
                    .findAll {!it.isAnnotationPresent(Id.class)}
                    .collect()
            return fields
        }
    }

    def findFirstConstructorWithoutParameters() {
        return clazz.getConstructor()
    }

    def checkFieldId() {
        for (def field in clazz.getDeclaredFields()) {
            if(field.isAnnotationPresent(Id.class)) {
                return true
            }
        }
        throw new EntityMetaDataException("@id not found for class ${getName()}")
    }

    @Override
    Field getIdField() {
        return idField
    }


    @Override
    String getName() {
        return clazz.getSimpleName()
    }

    @Override
    List<Field> getAllFields() {
        return allFields
    }

    @Override
    List<Field> getFieldsWithoutId() {
        return withoutIdFields
    }
}