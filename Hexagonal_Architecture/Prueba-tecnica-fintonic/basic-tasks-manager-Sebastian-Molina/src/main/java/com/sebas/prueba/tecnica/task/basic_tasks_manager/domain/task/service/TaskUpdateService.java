package com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.service;

import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.entity.Task;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.repository.TaskRepository;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.infrastructure.task.exception.TaskSQLCustomException;

public class TaskUpdateService {

    private final TaskRepository taskRepository;

    public TaskUpdateService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task updateTask(Task task) throws TaskSQLCustomException {
        return taskRepository.updateTask(task);
    }
}
