package ru.otus.homework

import com.google.common.base.MoreObjects

class HelloOtus {
    String name
    String surname
    String email

    HelloOtus(String name, String surname) {
        this.name = name
        this.surname = surname
    }

    void setEmail(String name, String surname, EMAIL mask) {
        this.email = "${name.toLowerCase()}" +
                     "${surname.toLowerCase()}" +
                     "${createEmail(mask)}"
    }

    static final String createEmail(EMAIL mask) {
        def result
        switch (mask) {
            case EMAIL.MAIL: {
                result = '@' + EMAIL.MAIL.name().toLowerCase() + '.ru'
                break
            }
            case EMAIL.YANDEX: {
                result = '@' + EMAIL.YANDEX.name().toLowerCase() + '.ru'
                break
            }
            case EMAIL.GOOGLE: {
                result = '@' + EMAIL.GOOGLE.name().toLowerCase() + '.com'
                break
            }
        }
        return result
    }

    @Override
    String toString() {
        return MoreObjects.toStringHelper(HelloOtus.class)
                .add("name", name)
                .add("surname", surname)
                .add("email", email)
                .toString()
    }
}
