package com.sebas.prueba.tecnica.task.basic_tasks_manager.application.task.service.impl;

import com.sebas.prueba.tecnica.task.basic_tasks_manager.application.task.mapper.TaskDTOMapper;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.application.task.service.TaskFindApplicationService;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.dto.TaskDTO;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.service.TaskFindService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskFindApplicationServiceImpl implements TaskFindApplicationService {

    private final TaskFindService taskFindService;

    private final TaskDTOMapper taskDTOMapper;

    public TaskFindApplicationServiceImpl(TaskFindService taskFindService, TaskDTOMapper taskDTOMapper) {
        this.taskFindService = taskFindService;
        this.taskDTOMapper = taskDTOMapper;
    }

    @Override
    public List<TaskDTO> findAllTasks() {
        return taskFindService.findAllTasks()
                .stream()
                .map(taskDTOMapper::fromTaskToTaskDTO)
                .toList();
    }

}
