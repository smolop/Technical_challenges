package com.sebas.prueba.tecnica.task.basic_tasks_manager.infrastructure.task.advice;

import com.sebas.prueba.tecnica.task.basic_tasks_manager.infrastructure.task.exception.TaskSQLCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TaskControllerAdvice {

    @ExceptionHandler(TaskSQLCustomException.class)
    public ResponseEntity<String> taskSQLCustomExceptionHandler(TaskSQLCustomException taskSQLCustomException){
        return new ResponseEntity<>(taskSQLCustomException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> genericException(Exception exception){
        return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
