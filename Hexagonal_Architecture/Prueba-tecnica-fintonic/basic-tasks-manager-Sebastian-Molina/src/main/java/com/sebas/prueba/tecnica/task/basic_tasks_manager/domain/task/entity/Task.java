package com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.entity;


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
public class Task {

    private Long id;

    private String title;

    private String description;

    private LocalDateTime expirationDate;

    private String status;

    private List<String> tags;

}
