import ru.otus.homework.model.Action

import java.time.LocalDate
import java.time.LocalDateTime
import ru.otus.homework.model.Task
import ru.otus.homework.service.ManagerServiceImpl

static void main(String[] args) {
  ManagerServiceImpl service = new ManagerServiceImpl()
  Task task = new Task("Cook dinner!", "Cook soup and make tea!", LocalDateTime.now(), LocalDateTime.now().plusMinutes(30))
  task = service.createTask(task)
  Action action = new Action("Cook soup!", "10 minutes", LocalDateTime.now(), LocalDateTime.now().plusMinutes(10), task.id)
  action = service.createAction(action)
  Action action1 = new Action("Make tea!", "15 minutes", LocalDateTime.now().plusMinutes(15), LocalDateTime.now().plusMinutes(15), task.id)
  action1 = service.createAction(action1)

  println '---------------------------------------------'
  service.tasks.each {entry ->
    println "$entry.key = $entry.value"
  }
  println '---------------------------------------------'
  service.actions.each {entry ->
    println "$entry.key = $entry.value"
  }
  println '---------------------------------------------'
  service.deleteAction(action.id)
  println '---------------------------------------------'
  service.tasks.each {entry ->
    println "$entry.key = $entry.value"
  }
  println '---------------------------------------------'
  service.actions.each {entry ->
    println "$entry.key = $entry.value"
  }
  println '---------------------------------------------'
  List<Task> tasks = service.findAllTaskByDate(LocalDate.now())
  def countTask = service.numbersOfTaskByDate(LocalDate.now())
  println tasks
  println countTask
  println '---------------------------------------------'
  List<Action> actions = service.findAllActionByDate(LocalDate.now())
  def countAction = service.numbersOfActionByDate(LocalDate.now())
  println actions
  println countAction
  println '---------------------------------------------'
  actions.each {
    it.execute()
  }
}