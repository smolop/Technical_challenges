package com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.service;

import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.entity.Task;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.repository.TaskRepository;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.infrastructure.task.exception.TaskSQLCustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TaskCreateServiceTest {

    @InjectMocks
    private TaskCreateService taskCreateService;

    @Mock
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTask() throws TaskSQLCustomException {

        Task expectedTask = getExpectedTask();

        when(taskRepository.createTask(expectedTask))
                .thenReturn(expectedTask);

        Task task = taskCreateService.createTask(expectedTask);

        assertCreateTask(expectedTask, task);

    }

    private static void assertCreateTask(Task expectedTask, Task task) {
        assertEquals(expectedTask.getId(), task.getId());
        assertEquals(expectedTask.getTitle(), task.getTitle());
        assertEquals(expectedTask.getDescription(), task.getDescription());
        assertEquals(expectedTask.getStatus(), task.getStatus());
        assertEquals(expectedTask.getExpirationDate(), task.getExpirationDate());

        List<String> expectedTags = expectedTask.getTags();
        List<String> responseTags = task.getTags();
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

}