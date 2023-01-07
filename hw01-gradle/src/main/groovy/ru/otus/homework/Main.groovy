package ru.otus.homework

static void main(String[] args) {
  println "Hello world!"

  HelloOtus otus = new HelloOtus()
  otus.setData(10, 30)

  println otus.start
  println otus.end
}
