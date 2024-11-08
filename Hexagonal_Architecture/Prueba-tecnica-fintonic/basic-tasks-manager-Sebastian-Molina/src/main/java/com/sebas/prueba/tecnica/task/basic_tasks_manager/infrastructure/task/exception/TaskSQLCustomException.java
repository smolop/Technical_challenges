package com.sebas.prueba.tecnica.task.basic_tasks_manager.infrastructure.task.exception;

public class TaskSQLCustomException extends Exception{

    public TaskSQLCustomException() {
        super();
    }

    public TaskSQLCustomException(String message) {
        super(message);
    }

    public TaskSQLCustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskSQLCustomException(Throwable cause) {
        super(cause);
    }

    protected TaskSQLCustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
