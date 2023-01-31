package ru.otus.homework

import ru.otus.homework.parser.JsonParser

JsonParser.jsonToHtml(
        'https://raw.githubusercontent.com/IvanKondrashkov/groovy-2022-12/main/test.json',
        new File('result.html'))
JsonParser.jsonToXml(
        'https://raw.githubusercontent.com/IvanKondrashkov/groovy-2022-12/main/test.json',
        new File('result.xml'))
