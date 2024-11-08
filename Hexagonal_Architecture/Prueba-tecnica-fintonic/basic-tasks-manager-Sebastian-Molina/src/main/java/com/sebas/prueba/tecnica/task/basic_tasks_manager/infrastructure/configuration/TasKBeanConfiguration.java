package com.sebas.prueba.tecnica.task.basic_tasks_manager.infrastructure.configuration;

import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.repository.TaskRepository;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.service.TaskCreateService;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.service.TaskFindService;
import com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.service.TaskUpdateService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TasKBeanConfiguration {

    @Bean
    public TaskCreateService taskCreateService(TaskRepository taskRepository) {
        return new TaskCreateService(taskRepository);
    }

    @Bean
    public TaskFindService taskFindService(TaskRepository taskRepository) {
        return new TaskFindService(taskRepository);
    }

    @Bean
    public TaskUpdateService taskUpdateService(TaskRepository taskRepository) {
        return new TaskUpdateService(taskRepository);
    }

}
