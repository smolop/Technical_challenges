package com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.repository;

import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.entity.Task;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.infrastructure.task.exception.TaskSQLCustomException;

import java.util.List;

public interface TaskRepository {

    Task createTask(Task task) throws TaskSQLCustomException;

    Task updateTask(Task task) throws TaskSQLCustomException;

    List<Task> getAllTasks();

}
