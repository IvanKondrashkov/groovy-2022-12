package ru.otus.homework

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import ru.otus.homework.model.Client
import ru.otus.homework.service.DBServiceClient
import ru.otus.homework.repository.DbExecutorImpl
import ru.otus.homework.repository.DataTemplateJdbc
import ru.otus.homework.mapper.EntitySQLMetaDataImpl
import ru.otus.homework.mapper.EntityClassMetaDataImpl
import ru.otus.homework.service.DbServiceClientImpl
import ru.otus.homework.datasource.DriverManagerDataSource
import ru.otus.homework.sessionmanager.TransactionRunnerJdbc

class DbServiceClientImplTest {

    Client client
    DBServiceClient serviceClient

    @BeforeEach
    void init() {
        Properties properties = new Properties()
        File file = new File("src/main/resources/application.properties")
        file.withDataInputStream {
            properties.load(it)
        }

        final String URL = properties."datasource.url"
        final String USER = properties."datasource.username"
        final String PASSWORD = properties."datasource.password"
        final String DRIVER = properties."datasource.driver-class-name"

        def dataSource = new DriverManagerDataSource(URL, USER, PASSWORD, DRIVER)
        def entityClassMetaData = new EntityClassMetaDataImpl<>(Client.class)
        def entitySQLMetaData = new EntitySQLMetaDataImpl(entityClassMetaData)
        def dbExecutor = new DbExecutorImpl()
        def runnerJdbc = new TransactionRunnerJdbc(dataSource)
        def templateJdbc = new DataTemplateJdbc(
                dbExecutor: dbExecutor,
                entitySQLMetaData: entitySQLMetaData,
                entityClassMetaData: entityClassMetaData
        )

        client = new Client('Djon')
        serviceClient = new DbServiceClientImpl(runnerJdbc, templateJdbc)
    }

    @AfterEach
    void tearDown() {
        client = null
        serviceClient = null
    }

    @Test
    void saveClient() {
        def saveClient = serviceClient.saveClient(client)

        assert  saveClient.id != null
        assert  saveClient.name == 'Djon'
    }

    @Test
    void getClient() {
        def saveClient = serviceClient.saveClient(client)

        assert  saveClient.id != null
        assert  saveClient.name == 'Djon'

        saveClient = serviceClient.getClient(saveClient.id)

        assert  saveClient.id != null
        assert  saveClient.name == 'Djon'
    }
}