package com.sebas.prueba.tecnica.task.basic_tasks_manager.infrastructure.task.repository;

import com.sebas.prueba.tecnica.task.basic_tasks_manager.infrastructure.task.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaTaskRepository extends JpaRepository<TaskEntity, Long> {

}
