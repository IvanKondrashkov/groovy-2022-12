package ru.otus.homework

static void main(String[] args) {
  HelloOtus otus = new HelloOtus("Djon", "Doe")
  otus.setEmail(otus.getName(), otus.getSurname(), EMAIL.MAIL)

  println "Hello world!"
  println otus.toString()
}
