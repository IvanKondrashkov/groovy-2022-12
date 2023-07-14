package ru.otus.homework

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.codehaus.groovy.control.CompilerConfiguration

class ConfigBaseDslTest {
    File config
    ConfigBaseDsl dsl

    @BeforeEach
    void init() {
        CompilerConfiguration configuration = new CompilerConfiguration()
        configuration.setScriptBaseClass(DelegatingScript.class.getName())
        GroovyShell sh = new GroovyShell(this.class.getClassLoader(), new Binding(), configuration)

        ClassLoader classLoader = getClass().getClassLoader()
        config = new File(classLoader.getResource('config.dsl').getFile())

        DelegatingScript script = sh.parse(config) as DelegatingScript
        dsl = new ConfigBaseDsl()
        script.setDelegate(dsl)
        script.run()
    }

    @AfterEach
    void tearDown() {
        config.delete()
        dsl = null
    }

    @Test
    void  checkConfigBaseDsl() {
        assert dsl.name == 'open'
        assert dsl.description == 'Apache Tomcat'
        assert dsl.http.port == 80
        assert dsl.http.secure == false
        assert dsl.https.port == 90
        assert dsl.https.secure == true
        assert dsl.mappings.size() == 2
        assert dsl.mappings[0].url == '/'
        assert dsl.mappings[0].active == false
        assert dsl.mappings[1].url == '/login'
        assert dsl.mappings[1].active == false
    }
}