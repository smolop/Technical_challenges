package com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.service;

import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.entity.Task;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.repository.TaskRepository;

import java.util.List;

public class TaskFindService {

    private final TaskRepository taskRepository;

    public TaskFindService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAllTasks(){
        return taskRepository.getAllTasks();
    }
}

