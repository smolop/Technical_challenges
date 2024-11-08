package com.sebas.prueba.tecnica.task.basic_tasks_manager.domain.task.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskDTO {

    private Long id;

    private String title;

    private String description;

    private String expirationDate;

    private String status;

    private List<String> tags;

}
