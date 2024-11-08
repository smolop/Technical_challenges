package com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.service;

import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.entity.Task;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TaskFindServiceTest {

    @InjectMocks
    private TaskFindService taskFindService;

    @Mock
    private TaskRepository taskRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllTasks() {

        List<Task> expectedTasks = getAllExpectedTasks();

        when(taskRepository.getAllTasks())
                .thenReturn(expectedTasks);

        List<Task> tasks = taskFindService.findAllTasks();

        assertAllTasks(expectedTasks, tasks);

    }

    private static void assertAllTasks(List<Task> expectedTasks, List<Task> tasks) {
        assertEquals(expectedTasks.size(), tasks.size());
        for (int index = 0; index < tasks.size(); index++) {
            Task expectedTask = expectedTasks.get(index);
            Task task = tasks.get(index);
            assertTask(expectedTask, task);
        }
    }

    private static void assertTask(Task expectedTask, Task task) {
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
}