package com.sebas.prueba.tecnica.task.basic_tasks_manager.application.task.service;

import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.dto.TaskDTO;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.infrastructure.task.exception.TaskSQLCustomException;

public interface TaskUpdateApplicationService {
    TaskDTO updateTask(TaskDTO taskDTO) throws TaskSQLCustomException;
}
