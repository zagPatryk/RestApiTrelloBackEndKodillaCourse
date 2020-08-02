package com.crud.tasks.domain;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String title;
    private String content;
}
