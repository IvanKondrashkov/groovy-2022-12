package ru.otus.homework

import ru.otus.homework.model.Atm
import ru.otus.homework.model.Currency
import ru.otus.homework.service.AtmService
import ru.otus.homework.service.AtmServiceImpl

static void main(String[] args) {
  Atm atm = new Atm(Currency.RUB)
  AtmService service = new AtmServiceImpl(atm)
  Atm atm1 = new Atm(100, Currency.RUB, atm.score)
  atm = service + atm1
  println service.balance()
  Atm atm2 = new Atm(300, Currency.RUB, atm.score)
  atm = service + atm2
  println service.balance()
  Atm atm3 = new Atm(300, Currency.RUB, atm.score)
  atm = service - atm3
  println service.balance()
  Atm atm4 = new Atm(100, Currency.RUB, atm.score)
  atm = service - atm4
  println service.balance()
  Atm atm5 = new Atm(1000, Currency.RUB, atm.score)
  atm = service + atm5
  println service.balance()
}
