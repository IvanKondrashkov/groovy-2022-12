package ru.otus.homework

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import ru.otus.homework.model.Client
import ru.otus.homework.mapper.EntityClassMetaDataImpl

class EntityClassMetaDataImplTest {

    EntityClassMetaDataImpl<Client> entityClassMetaData

    @BeforeEach
    void init() {
        entityClassMetaData = new EntityClassMetaDataImpl<>(Client.class)
    }

    @AfterEach
    void tearDown() {
        entityClassMetaData = null
    }

    @Test
    void getIdField() {
        def id = entityClassMetaData.getIdField()

        assert id.name == 'id'
        assert id.getType() == Integer
    }

    @Test
    void getName() {
        def name = entityClassMetaData.getName()

        assert name == 'Client'
    }

    @Test
    void getAllFields() {
        def fields = entityClassMetaData.getAllFields()

        assert fields.size() == 2
        assert fields.get(0).name == 'id'
        assert fields.get(1).name == 'name'

    }

    @Test
    void getFieldsWithoutId() {
        def fields = entityClassMetaData.getFieldsWithoutId()

        assert fields.size() == 1
        assert fields.get(0).name == 'name'

    }
}