package com.sebas.prueba.tecnica.task.basic_tasks_manager.application.task.service.impl;

import com.sebas.prueba.tecnica.task.basic_tasks_manager.application.task.mapper.TaskDTOMapper;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.application.task.service.TaskUpdateApplicationService;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.dto.TaskDTO;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.entity.Task;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.service.TaskUpdateService;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.infrastructure.task.exception.TaskSQLCustomException;
import org.springframework.stereotype.Service;

@Service
public class TaskUpdateApplicationServiceImpl implements TaskUpdateApplicationService {

    private final TaskUpdateService taskUpdateService;

    private final TaskDTOMapper taskDTOMapper;

    public TaskUpdateApplicationServiceImpl(TaskUpdateService taskUpdateService, TaskDTOMapper taskDTOMapper) {
        this.taskUpdateService = taskUpdateService;
        this.taskDTOMapper = taskDTOMapper;
    }

    @Override
    public TaskDTO updateTask(TaskDTO taskDTO) throws TaskSQLCustomException {
        Task task = taskDTOMapper.fromTaskDTOtoTask(taskDTO);
        Task updatedTask = taskUpdateService.updateTask(task);
        return taskDTOMapper.fromTaskToTaskDTO(updatedTask);
    }
}
