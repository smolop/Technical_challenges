package com.sebas.prueba.tecnica.task.basic_tasks_manager.infrastructure.task.mapper;


import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.entity.Task;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.infrastructure.task.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface TaskMapper {

    Task fromTaskEntityToTask(TaskEntity taskEntity);

    TaskEntity fromTaskToTaskEntity(Task task);

}
