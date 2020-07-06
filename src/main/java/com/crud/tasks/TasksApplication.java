package com.crud.tasks;

import com.crud.tasks.domain.TaskDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TasksApplication {

    public static void main(String[] args) {
        TaskDto taskDto = new TaskDto(1L,"S1","D1");
        TaskDto taskDto2 = new TaskDto(2L,"S2","D2");


        SpringApplication.run(TasksApplication.class, args);
    }

}
