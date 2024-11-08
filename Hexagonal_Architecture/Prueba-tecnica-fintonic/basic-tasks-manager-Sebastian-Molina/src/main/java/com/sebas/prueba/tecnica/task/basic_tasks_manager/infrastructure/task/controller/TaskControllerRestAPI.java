package com.sebas.prueba.tecnica.task.basic_tasks_manager.infrastructure.task.controller;

import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.dto.TaskDTO;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.infrastructure.task.exception.TaskSQLCustomException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/tasks")
public interface TaskControllerRestAPI {

    @Operation(summary = "Create a new task", description = "Task must not exists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = TaskDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "The task already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping()
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) throws TaskSQLCustomException;

    @Operation(summary = "Update a new task", description = "Task must exists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = TaskDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "The task does not exists, therefore can be updated"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping()
    public  ResponseEntity<TaskDTO> updateTask(@RequestBody TaskDTO taskDTO) throws TaskSQLCustomException;

    @Operation(summary = "Get all tasks", description = "Tasks must exists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    { @Content(mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = TaskDTO.class))
                    ) }),
            @ApiResponse(responseCode = "204", description = "There are not tasks"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping()
    public ResponseEntity<List<TaskDTO>> getAllTasks();

}
