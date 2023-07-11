package ru.otus.homework.service

import groovy.util.logging.Slf4j
import ru.otus.homework.model.Client
import ru.otus.homework.repository.DataTemplate
import ru.otus.homework.sessionmanager.TransactionRunner

@Slf4j
class DbServiceClientImpl implements DBServiceClient {
    private final DataTemplate<Client> dataTemplate
    private final TransactionRunner transactionRunner

    DbServiceClientImpl(TransactionRunner transactionRunner, DataTemplate<Client> dataTemplate) {
        this.transactionRunner = transactionRunner
        this.dataTemplate = dataTemplate
    }

    @Override
    Client saveClient(Client client) {
        transactionRunner.doInTransaction(connection -> {
            if (client.getId() == null) {
                def clientId = dataTemplate.insert(connection, client)
                def createdClient = new Client(clientId, client.getName())
                log.info("created client: {}", createdClient)
                client = createdClient
            }
            dataTemplate.update(connection, client)
            log.info("updated client: {}", client)
            client
        })
    }

    @Override
    Client getClient(long id) {
        transactionRunner.doInTransaction(connection -> {
            def clientOptional = dataTemplate.findById(connection, id)
            log.info("client: {}", clientOptional)
            clientOptional.get().first()
        })
    }

    @Override
    List<Client> findAll() {
        transactionRunner.doInTransaction(connection -> {
            def clientList = dataTemplate.findAll(connection)
            log.info("clientList:{}", clientList)
            clientList.get()
        })
    }
}