package ru.otus.homework

class ConfigTestDsl extends ConfigBaseDsl {

    @Override
    def profile(@DelegatesTo(value = ConfigTestDsl) Closure cl) {
        cl.setDelegate(this)
        cl.setResolveStrategy(Closure.DELEGATE_ONLY)
        cl.call()
        mappings(mappings)
    }
}