package ru.otus.homework

import groovy.transform.ToString

@ToString(includeFields = true)
class ConfigBaseDsl {
    String name
    String description
    Protocol http
    Protocol https
    List mappings = []

    def profile(@DelegatesTo(value = ConfigBaseDsl) Closure cl) {
        cl.setDelegate(this)
        cl.setResolveStrategy(Closure.DELEGATE_FIRST)
        cl.call()
        mappings(mappings)
    }

    def http(@DelegatesTo(value = Protocol) Closure cl) {
        def dsl = new Protocol()
        cl.setDelegate(dsl)
        cl.setResolveStrategy(Closure.DELEGATE_FIRST)
        cl.call()
        this.http = dsl
    }

    def https(@DelegatesTo(value = Protocol) Closure cl) {
        def dsl = new Protocol()
        cl.setDelegate(dsl)
        cl.setResolveStrategy(Closure.DELEGATE_FIRST)
        cl.call()
        this.https = dsl
    }

    def mappings(@DelegatesTo(value = Mappings) List<Closure> closures) {
        def list = []
        closures.each {cl ->
            def dsl = new Mappings()
            cl.setDelegate(dsl)
            cl.setResolveStrategy(Closure.DELEGATE_FIRST)
            cl.call()
            list << dsl
        }
        mappings = list
    }
}