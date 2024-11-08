package com.sebas.prueba.tecnica.task.basic_tasks_manager.application.task.mapper;

import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.dto.TaskDTO;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
@Component
public interface TaskDTOMapper {

    TaskDTO fromTaskToTaskDTO(Task task);

    @Mapping(target = "expirationDate", qualifiedByName = "fromStringToLocalDateTime")
    Task fromTaskDTOtoTask(TaskDTO taskDTO);

    @Named("fromLocalDateTimeToString")
    public static String fromLocalDateTimeToString(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return localDateTime.format(formatter);
    }

    @Named("fromStringToLocalDateTime")
    public static LocalDateTime fromStringToLocalDateTime(String localDateTimeString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return LocalDateTime.parse(localDateTimeString, formatter);
    }
}
