package com.sebas.prueba.tecnica.task.basic_tasks_manager.application.task.service;

import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.dto.TaskDTO;

import java.util.List;

public interface TaskFindApplicationService {
    List<TaskDTO> findAllTasks();
}
