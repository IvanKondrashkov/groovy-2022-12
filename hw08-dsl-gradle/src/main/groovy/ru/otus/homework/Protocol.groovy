package ru.otus.homework

import groovy.transform.ToString

@ToString(includeFields = true)
class Protocol {
    Integer port
    Boolean secure
}