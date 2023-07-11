package ru.otus.homework.model

import groovy.transform.ToString
import ru.otus.homework.annotation.Id

@ToString(includeFields = true)
class Manager {
    @Id
    private Integer no
    private String label
    private String param1

    Manager() {
    }

    Manager(String label, String param1) {
        this.label = label
        this.param1 = param1
    }

    Manager(Integer no, String label, String param1) {
        this.no = no
        this.label = label
        this.param1 = param1
    }

    Long getNo() {
        return no
    }

    void setNo(Integer no) {
        this.no = no
    }

    String getLabel() {
        return label
    }

    void setLabel(String label) {
        this.label = label
    }

    String getParam1() {
        return param1
    }

    void setParam1(String param1) {
        this.param1 = param1
    }
}