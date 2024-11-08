package com.sebas.prueba.tecnica.task.basic_tasks_manager.application.task.service.impl;

import com.sebas.prueba.tecnica.task.basic_tasks_manager.application.task.mapper.TaskDTOMapper;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.application.task.service.TaskCreateApplicationService;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.dto.TaskDTO;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.entity.Task;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.service.TaskCreateService;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.infrastructure.task.exception.TaskSQLCustomException;
import org.springframework.stereotype.Service;

@Service
public class TaskCreateApplicationServiceImpl implements TaskCreateApplicationService {

    private final TaskCreateService taskCreateService;

    private final TaskDTOMapper taskDTOMapper;

    public TaskCreateApplicationServiceImpl(TaskCreateService taskCreateService, TaskDTOMapper taskDTOMapper) {
        this.taskCreateService = taskCreateService;
        this.taskDTOMapper = taskDTOMapper;
    }

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) throws TaskSQLCustomException {
        Task task = taskDTOMapper.fromTaskDTOtoTask(taskDTO);
        Task createdTask = taskCreateService.createTask(task);
        return taskDTOMapper.fromTaskToTaskDTO(createdTask);
    }
}
