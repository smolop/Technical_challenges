package com.sebas.prueba.tecnica.task.basic_tasks_manager.application.task.service;

import com.sebas.prueba.tecnica.task.basic_tasks_manager.application.task.mapper.TaskDTOMapper;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.application.task.service.impl.TaskFindApplicationServiceImpl;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.dto.TaskDTO;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.entity.Task;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.repository.TaskRepository;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.service.TaskFindService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class TaskFindApplicationServiceTest {

    @InjectMocks
    private TaskFindApplicationServiceImpl taskFindApplicationService;

    @InjectMocks
    private TaskFindService taskFindService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskDTOMapper taskDTOMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllTasks() {

        List<Task> expectedTasks = getAllExpectedTasks();
        List<TaskDTO> expectedTasksDTOs = getAllExpectedTasksDTOs();
        
        when(taskFindService.findAllTasks())
                .thenReturn(expectedTasks);

        for (int index = 0; index < expectedTasks.size(); index++) {
            Task expectedTask = expectedTasks.get(index);
            TaskDTO expectedTaskDTO = expectedTasksDTOs.get(index);
            when(taskDTOMapper.fromTaskToTaskDTO(expectedTask))
                    .thenReturn(expectedTaskDTO);
        }


        List<TaskDTO> tasksDTOs = taskFindApplicationService.findAllTasks();
        
        assertAllTasksDTOs(expectedTasksDTOs, tasksDTOs);

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

    private static List<Task> getAllExpectedTasks() {
        List<Task> expectedTasks = new ArrayList<>();
        Task expectedTask1 = getExpectedTask(1L);
        Task expectedTask2 = getExpectedTask(2L);
        Task expectedTask3 = getExpectedTask(3L);
        expectedTasks.add(expectedTask1);
        expectedTasks.add(expectedTask2);
        expectedTasks.add(expectedTask3);
        return expectedTasks;
    }

    private static Task getExpectedTask(Long id) {
        Task expectedTask = new Task();
        expectedTask.setId(id);
        expectedTask.setTitle("Testing Title");
        expectedTask.setDescription("Testing description");
        expectedTask.setStatus("Pending");
        LocalDateTime expirationDate = LocalDateTime.of(2099, 12, 31, 0, 0);
        expectedTask.setExpirationDate(expirationDate);
        List<String> tags = Arrays.asList("tag1", "tag2");
        expectedTask.setTags(tags);
        return expectedTask;
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