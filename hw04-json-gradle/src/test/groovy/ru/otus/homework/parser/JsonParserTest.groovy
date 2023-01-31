package ru.otus.homework.parser

import groovy.xml.XmlSlurper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

class JsonParserTest {
    private File html
    private File xml

    @BeforeEach
    void init() {
        html = new File('result.html')
        xml = new File('result.xml')
        JsonParser.jsonToHtml(
                'https://raw.githubusercontent.com/IvanKondrashkov/groovy-2022-12/main/test.json',
                html)
        JsonParser.jsonToXml(
                'https://raw.githubusercontent.com/IvanKondrashkov/groovy-2022-12/main/test.json',
                xml)
    }

    @AfterEach
    void tearDown() {
        html.delete()
        xml.delete()
    }

    @Test
    void jsonToHtml() {
        def text = html.text
        def slurper = new XmlSlurper().parseText(text)

        assert slurper.name() == 'div'
        assert slurper.div.@id == 'employee'
        assert slurper.div.p[0] == 'name'
        assert slurper.div.p[1] == 'age'
        assert slurper.div.p[2] == 'secretIdentity'
        assert slurper.div.ul.@id == 'powers'
        assert slurper.div.ul.li[0] == 'power'
    }

    @Test
    void jsonToXml() {
        def text = xml.text
        def slurper = new XmlSlurper().parseText(text)

        assert slurper.name() == 'div'
        assert slurper.div.@id == 'employee'
        assert slurper.div.p[0] == 'name'
        assert slurper.div.p[1] == 'age'
        assert slurper.div.p[2] == 'secretIdentity'
        assert slurper.div.ul.@id == 'powers'
        assert slurper.div.ul.li[0] == 'power'
    }
}
