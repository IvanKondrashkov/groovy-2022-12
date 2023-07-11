package ru.otus.homework.service

import groovy.util.logging.Slf4j
import ru.otus.homework.model.Manager
import ru.otus.homework.repository.DataTemplate
import ru.otus.homework.sessionmanager.TransactionRunner

@Slf4j
class DbServiceManagerImpl implements DBServiceManager {
    private final DataTemplate<Manager> managerDataTemplate
    private final TransactionRunner transactionRunner

    DbServiceManagerImpl(TransactionRunner transactionRunner, DataTemplate<Manager> managerDataTemplate) {
        this.transactionRunner = transactionRunner
        this.managerDataTemplate = managerDataTemplate
    }

    @Override
    Manager saveManager(Manager manager) {
        transactionRunner.doInTransaction(connection -> {
            if (manager.getNo() == null) {
                def managerNo = managerDataTemplate.insert(connection, manager)
                def createdManager = new Manager(managerNo, manager.getLabel(), manager.getParam1())
                log.info("created manager: {}", createdManager)
                manager = createdManager
            }
            managerDataTemplate.update(connection, manager)
            log.info("updated manager: {}", manager)
            manager
        })
    }

    @Override
    Manager getManager(long no) {
        transactionRunner.doInTransaction(connection -> {
            def managerOptional = managerDataTemplate.findById(connection, no)
            log.info("manager: {}", managerOptional)
            managerOptional.get().first()
        })
    }

    @Override
    List<Manager> findAll() {
        transactionRunner.doInTransaction(connection -> {
            def managerList = managerDataTemplate.findAll(connection)
            log.info("managerList:{}", managerList)
            managerList.get()
        })
    }
}