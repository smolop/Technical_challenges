package com.sebas.prueba.tecnica.task.basic_tasks_manager.application.task.service;

import com.sebas.prueba.tecnica.task.basic_tasks_manager.application.task.mapper.TaskDTOMapper;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.application.task.service.impl.TaskUpdateApplicationServiceImpl;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.dto.TaskDTO;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.entity.Task;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.repository.TaskRepository;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.service.TaskUpdateService;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.infrastructure.task.exception.TaskSQLCustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TaskUpdateApplicationServiceTest {

    @InjectMocks
    private TaskUpdateApplicationServiceImpl taskUpdateApplicationService;

    @InjectMocks
    private TaskUpdateService taskUpdateService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskDTOMapper taskDTOMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void updateTask() throws TaskSQLCustomException {

        TaskDTO expectedTaskDTO = getExpectedTaskDTO();
        Task expectedTask = getExpectedTask();

        when(taskDTOMapper.fromTaskDTOtoTask(any()))
                .thenReturn(expectedTask);
        when(taskUpdateService.updateTask(any()))
                .thenReturn(expectedTask);
        when(taskDTOMapper.fromTaskToTaskDTO(any()))
                .thenReturn(expectedTaskDTO);

        TaskDTO taskDTO = taskUpdateApplicationService.updateTask(expectedTaskDTO);

        assertUpdateTaskDTO(expectedTaskDTO, taskDTO);
    }

    private static void assertUpdateTaskDTO(TaskDTO expectedTaskDTO, TaskDTO taskDTO) {
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

    private static Task getExpectedTask() {
        Task expectedTask = new Task();
        expectedTask.setId(1L);
        expectedTask.setTitle("Testing Title");
        expectedTask.setDescription("Testing description");
        expectedTask.setStatus("Pending");
        LocalDateTime expirationDate = LocalDateTime.of(2099, 12, 31, 0, 0);
        expectedTask.setExpirationDate(expirationDate);
        List<String> tags = Arrays.asList("tag1", "tag2");
        expectedTask.setTags(tags);
        return expectedTask;
    }

    private static TaskDTO getExpectedTaskDTO() {
        TaskDTO expectedTaskDTO = new TaskDTO();
        expectedTaskDTO.setId(1L);
        expectedTaskDTO.setTitle("Testing Title");
        expectedTaskDTO.setDescription("Testing description");
        expectedTaskDTO.setStatus("Pending");
        expectedTaskDTO.setExpirationDate("31/12/2099 00:00:00");
        List<String> tags = Arrays.asList("tag1", "tag2");
        expectedTaskDTO.setTags(tags);
        return expectedTaskDTO;
    }


}