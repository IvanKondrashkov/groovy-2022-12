package ru.otus.homework.parser

import groovy.json.JsonSlurper
import groovy.xml.MarkupBuilder

class JsonParser {
    static void jsonToHtml(String url, File file) {
        def slurper = new JsonSlurper().parse(new URL(url))
        def builder = new MarkupBuilder(new FileWriter(file))

        builder.div {
            div(id: slurper.div.div.id) {
                slurper.div.div.p.each {
                    p(it)
                    mkp.yieldUnescaped('<br/>')
                }
                ul(id: slurper.div.div.ul.id) {
                    slurper.div.div.ul.li.each {
                        li(it)
                    }
                }
            }
        }
    }

    static void jsonToXml(String url, File file) {
        def slurper = new JsonSlurper().parse(new URL(url))
        def builder = new MarkupBuilder(new FileWriter(file))

        builder.div {
            div(id: slurper.div.div.id) {
                slurper.div.div.p.each {
                    p(it)
                    mkp.yieldUnescaped('<br/>')
                }
                ul(id: slurper.div.div.ul.id) {
                    slurper.div.div.ul.li.each {
                        li(it)
                    }
                }
            }
        }
    }
}
