package ru.otus.homework

class ConfigProdDsl extends ConfigBaseDsl {

    @Override
    def profile(@DelegatesTo(value = ConfigProdDsl) Closure cl) {
        cl.setDelegate(this)
        cl.setResolveStrategy(Closure.DELEGATE_ONLY)
        cl.call()
        mappings(mappings)
    }
}