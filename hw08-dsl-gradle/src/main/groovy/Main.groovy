import org.codehaus.groovy.control.CompilerConfiguration
import ru.otus.homework.ConfigBaseDsl

static void main(String[] args) {
    CompilerConfiguration configuration = new CompilerConfiguration()
    configuration.setScriptBaseClass(DelegatingScript.class.getName())
    GroovyShell sh = new GroovyShell(this.class.getClassLoader(), new Binding(), configuration)

    ClassLoader classLoader = getClass().getClassLoader()
    File config = new File(classLoader.getResource('config.dsl').getFile())

    DelegatingScript script = sh.parse(config) as DelegatingScript
    ConfigBaseDsl dsl = new ConfigBaseDsl()
    script.setDelegate(dsl)
    script.run()

    println(dsl)
}