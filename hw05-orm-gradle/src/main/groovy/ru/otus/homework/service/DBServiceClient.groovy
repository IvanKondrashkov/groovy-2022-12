package ru.otus.homework.service

import ru.otus.homework.model.Client

interface DBServiceClient {
    Client saveClient(Client client)

    Client getClient(long id)

    List<Client> findAll()
}