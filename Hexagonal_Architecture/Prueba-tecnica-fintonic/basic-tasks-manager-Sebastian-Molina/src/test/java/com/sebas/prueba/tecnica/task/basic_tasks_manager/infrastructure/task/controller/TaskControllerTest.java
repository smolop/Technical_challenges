package com.sebas.prueba.tecnica.task.basic_tasks_manager.infrastructure.task.controller;

import com.sebas.prueba.tecnica.task.basic_tasks_manager.application.task.service.TaskCreateApplicationService;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.application.task.service.TaskFindApplicationService;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.application.task.service.TaskUpdateApplicationService;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.dto.TaskDTO;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.infrastructure.task.exception.TaskSQLCustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TaskControllerTest {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskCreateApplicationService taskCreateApplicationService;

    @Mock
    private TaskUpdateApplicationService taskUpdateApplicationService;

    @Mock
    private TaskFindApplicationService taskFindApplicationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTask() throws TaskSQLCustomException {

        TaskDTO expectedTaskDTO = getExpectedTaskDTO(1L);

        when(taskCreateApplicationService.createTask(expectedTaskDTO))
                .thenReturn(expectedTaskDTO);

        ResponseEntity<TaskDTO> taskDTOResponseEntity = taskController.createTask(expectedTaskDTO);

        HttpStatus expectedHttpStatus = HttpStatus.OK;
        HttpStatusCode httpStatus = taskDTOResponseEntity.getStatusCode();
        assertEquals(expectedHttpStatus, httpStatus);

        TaskDTO taskDTO = taskDTOResponseEntity.getBody();
        assert taskDTO != null;
        assertTaskDTO(expectedTaskDTO, taskDTO);

    }

    @Test
    void crateTask_ThrowTaskSQLCustomException() throws TaskSQLCustomException {
        TaskDTO expectedTaskDTO = getExpectedTaskDTO(1L);

        when(taskCreateApplicationService.createTask(expectedTaskDTO))
                .thenThrow(TaskSQLCustomException.class);

        assertThrows(TaskSQLCustomException.class, () -> taskController.createTask(expectedTaskDTO));
    }

    @Test
    void crateTask_ThrowGenericExceptionException() throws TaskSQLCustomException {
        TaskDTO expectedTaskDTO = getExpectedTaskDTO(1L);

        when(taskCreateApplicationService.createTask(expectedTaskDTO))
                .thenThrow(new RuntimeException());

        assertThrows(Exception.class, () -> taskController.createTask(expectedTaskDTO));
    }


    @Test
    void updateTask() throws TaskSQLCustomException {
        TaskDTO expectedTaskDTO = getExpectedTaskDTO(1L);

        when(taskUpdateApplicationService.updateTask(expectedTaskDTO))
                .thenReturn(expectedTaskDTO);

        ResponseEntity<TaskDTO> taskDTOResponseEntity = taskController.updateTask(expectedTaskDTO);

        HttpStatus expectedHttpStatus = HttpStatus.OK;
        HttpStatusCode httpStatus = taskDTOResponseEntity.getStatusCode();
        assertEquals(expectedHttpStatus, httpStatus);

        TaskDTO taskDTO = taskDTOResponseEntity.getBody();
        assert taskDTO != null;
        assertTaskDTO(expectedTaskDTO, taskDTO);
    }

    @Test
    void updateTask_ThrowTaskSQLCustomException() throws TaskSQLCustomException {
        TaskDTO expectedTaskDTO = getExpectedTaskDTO(1L);

        when(taskUpdateApplicationService.updateTask(expectedTaskDTO))
                .thenThrow(TaskSQLCustomException.class);

        assertThrows(TaskSQLCustomException.class, () -> taskController.updateTask(expectedTaskDTO));
    }

    @Test
    void updateTask_ThrowGenericExceptionException() throws TaskSQLCustomException {
        TaskDTO expectedTaskDTO = getExpectedTaskDTO(1L);

        when(taskUpdateApplicationService.updateTask(expectedTaskDTO))
                .thenThrow(new RuntimeException());

        assertThrows(Exception.class, () -> taskController.updateTask(expectedTaskDTO));
    }



    @Test
    void getAllTasks() {
        List<TaskDTO> expectedTasksDTOs = getAllExpectedTasksDTOs();

        when(taskFindApplicationService.findAllTasks())
                .thenReturn(expectedTasksDTOs);

        ResponseEntity<List<TaskDTO>> tasksDTOsResponseEntity = taskController.getAllTasks();

        HttpStatus expectedHttpStatus = HttpStatus.OK;
        HttpStatusCode httpStatus = tasksDTOsResponseEntity.getStatusCode();
        assertEquals(expectedHttpStatus, httpStatus);

        List<TaskDTO> tasksDTOs = tasksDTOsResponseEntity.getBody();
        assert tasksDTOs != null;
        assertAllTasksDTOs(expectedTasksDTOs, tasksDTOs);

    }

    @Test
    void getAllTasks_ReturnHttpStatusNoContent() {
        List<TaskDTO> expectedTasksDTOs = new ArrayList<>();

        when(taskFindApplicationService.findAllTasks())
                .thenReturn(expectedTasksDTOs);

        ResponseEntity<List<TaskDTO>> tasksDTOsResponseEntity = taskController.getAllTasks();

        HttpStatus expectedHttpStatus = HttpStatus.NO_CONTENT;
        HttpStatusCode httpStatus = tasksDTOsResponseEntity.getStatusCode();
        assertEquals(expectedHttpStatus, httpStatus);
    }

    @Test
    void getAllTasks_ThrowGenericException() {
        List<TaskDTO> expectedTasksDTOs = getAllExpectedTasksDTOs();

        when(taskFindApplicationService.findAllTasks())
                .thenThrow(new RuntimeException());

        assertThrows(Exception.class, () -> taskController.getAllTasks());
    }

    private static void assertAllTasksDTOs(List<TaskDTO> expectedTasksDTOs, List<TaskDTO> tasksDTOs) {
        assertEquals(expectedTasksDTOs.size(), tasksDTOs.size());
        for (int index = 0; index < tasksDTOs.size(); index++) {
            TaskDTO expectedTaskDTO = expectedTasksDTOs.get(index);
            TaskDTO taskDTO = tasksDTOs.get(index);
            assertTaskDTO(expectedTaskDTO, taskDTO);
        }
    }

    private static void assertTaskDTO(TaskDTO expectedTaskDTO, TaskDTO taskDTO) {
        assertEquals(expectedTaskDTO.getId(), taskDTO.getId());
        assertEquals(expectedTaskDTO.getTitle(), taskDTO.getTitle());
        assertEquals(expectedTaskDTO.getDescription(), taskDTO.getDescription());
        assertEquals(expectedTaskDTO.getStatus(), taskDTO.getStatus());
        assertEquals(expectedTaskDTO.getExpirationDate(), taskDTO.getExpirationDate());

        List<String> expectedTags = expectedTaskDTO.getTags();
        List<String> responseTags = taskDTO.getTags();
        assertEquals(expectedTags.size(), responseTags.size());

        for (int index = 0; index < responseTags.size(); index++) {
            assertEquals(expectedTags.get(index), responseTags.get(index));
        }
    }

    private static List<TaskDTO> getAllExpectedTasksDTOs() {
        List<TaskDTO> expectedTasks = new ArrayList<>();
        TaskDTO expectedTask1 = getExpectedTaskDTO(1L);
        TaskDTO expectedTask2 = getExpectedTaskDTO(2L);
        TaskDTO expectedTask3 = getExpectedTaskDTO(3L);
        expectedTasks.add(expectedTask1);
        expectedTasks.add(expectedTask2);
        expectedTasks.add(expectedTask3);
        return expectedTasks;
    }

    private static TaskDTO getExpectedTaskDTO(Long id) {
        TaskDTO expectedTaskDTO = new TaskDTO();
        expectedTaskDTO.setId(id);
        expectedTaskDTO.setTitle("Testing Title");
        expectedTaskDTO.setDescription("Testing description");
        expectedTaskDTO.setStatus("Pending");
        LocalDateTime expirationDate = LocalDateTime.of(2099, 12, 31, 0, 0);
        expectedTaskDTO.setExpirationDate("31/12/2099 00:00:00");
        List<String> tags = Arrays.asList("tag1", "tag2");
        expectedTaskDTO.setTags(tags);
        return expectedTaskDTO;
    }
}