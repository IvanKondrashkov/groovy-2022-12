package ru.otus.homework.service.action

import java.time.LocalDate
import ru.otus.homework.model.Action

interface ActionService {
    Action createAction(Action action)
    void deleteAction(Long id)
    Action findAction(Long id)
    List<Action> findAllActionByDate(LocalDate date)
    int numbersOfActionByDate(LocalDate date)
}
