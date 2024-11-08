package com.sebas.prueba.tecnica.task.basic_tasks_manager.infrastructure.task.repository.impl;

import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.repository.TaskRepository;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.entity.Task;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.infrastructure.task.entity.TaskEntity;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.infrastructure.task.exception.TaskSQLCustomException;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.infrastructure.task.mapper.TaskMapper;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.infrastructure.task.repository.JpaTaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class MySQLTaskRepository implements TaskRepository {

    @Autowired
    private JpaTaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public Task createTask(Task task) throws TaskSQLCustomException {
        Long taskId = task.getId();
        boolean taskExists = taskRepository.existsById(taskId);
        if (!taskExists) {
            TaskEntity taskEntity = taskMapper.fromTaskToTaskEntity(task);
            TaskEntity taskEntitySaved = taskRepository.save(taskEntity);
            return taskMapper.fromTaskEntityToTask(taskEntitySaved);
        } else
            throw new TaskSQLCustomException(String.format("The task with id %s already exists", taskId));
    }

    @Override
    public Task updateTask(Task task) throws TaskSQLCustomException {
        Long taskId = task.getId();
        boolean taskExists = taskRepository.existsById(taskId);
        if (taskExists) {
            TaskEntity taskEntity = taskMapper.fromTaskToTaskEntity(task);
            TaskEntity taskEntityUpdated = taskRepository.save(taskEntity);
            return taskMapper.fromTaskEntityToTask(taskEntityUpdated);
        } else
            throw new TaskSQLCustomException(String.format("The task with id %s does not exists", taskId));
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::fromTaskEntityToTask)
                .toList();
    }
}
