package ru.otus.homework.model

import groovy.transform.ToString
import ru.otus.homework.annotation.Id

@ToString(includeFields = true)
class Client {
    @Id
    private Integer id
    private String name

    Client() {
    }

    Client(String name) {
        this.id = null
        this.name = name
    }

    Client(Integer id, String name) {
        this.id = id
        this.name = name
    }

    Long getId() {
        return id
    }

    String getName() {
        return name
    }

    void setId(Integer id) {
        this.id = id
    }

    void setName(String name) {
        this.name = name
    }
}