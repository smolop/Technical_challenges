package com.sebas.prueba.tecnica.task.basic_tasks_manager.infrastructure.task.controller;

import com.sebas.prueba.tecnica.task.basic_tasks_manager.application.task.service.TaskCreateApplicationService;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.application.task.service.TaskFindApplicationService;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.application.task.service.TaskUpdateApplicationService;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.dto.TaskDTO;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.infrastructure.task.exception.TaskSQLCustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class TaskController implements TaskControllerRestAPI {

    private final TaskCreateApplicationService taskCreateApplicationService;

    private final TaskUpdateApplicationService taskUpdateApplicationService;

    private final TaskFindApplicationService taskFindApplicationService;

    public TaskController(TaskCreateApplicationService taskCreateApplicationService, TaskUpdateApplicationService taskUpdateApplicationService, TaskFindApplicationService taskFindApplicationService) {
        this.taskCreateApplicationService = taskCreateApplicationService;
        this.taskUpdateApplicationService = taskUpdateApplicationService;
        this.taskFindApplicationService = taskFindApplicationService;
    }


    @Override
    public ResponseEntity<TaskDTO> createTask(TaskDTO taskDTO) throws TaskSQLCustomException {
        TaskDTO taskDTOResponse = taskCreateApplicationService.createTask(taskDTO);
        return ResponseEntity
                .ok(taskDTOResponse);
    }

    @Override
    public ResponseEntity<TaskDTO> updateTask(TaskDTO taskDTO) throws TaskSQLCustomException {
        TaskDTO taskDTOResponse = taskUpdateApplicationService.updateTask(taskDTO);
        return ResponseEntity
                .ok(taskDTOResponse);
    }

    @Override
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        List<TaskDTO> taskDTOS = taskFindApplicationService.findAllTasks();
        if (!taskDTOS.isEmpty())
            return ResponseEntity
                    .ok(taskDTOS);
        else
            return ResponseEntity
                    .noContent()
                    .build();
    }

}

