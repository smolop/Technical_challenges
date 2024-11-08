package com.sebas.prueba.tecnica.task.basic_tasks_manager.infrastructure.task.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tasks", schema = "basic_tasks_db")
public class TaskEntity {

    @Id
    private Long id;

    private String title;

    private String description;

    private LocalDateTime expirationDate;

    private String status;

    private List<String> tags;

}
