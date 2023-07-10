package ru.otus.homework.sessionmanager;

interface TransactionRunner {
    <T> T doInTransaction(Closure<T> action)
}