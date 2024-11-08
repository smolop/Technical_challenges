package com.sebas.prueba.tecnica.task.basic_tasks_manager.application.task.service;

import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.dto.TaskDTO;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.infrastructure.task.exception.TaskSQLCustomException;

public interface TaskCreateApplicationService {
    TaskDTO createTask(TaskDTO taskDTO) throws TaskSQLCustomException;
}
