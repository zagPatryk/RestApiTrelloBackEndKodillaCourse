package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/task")
public class TaskController {

    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasks() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTask")
    public TaskDto getTask(Long taskId) {
        return new TaskDto(1L, "test title", "test content");
    }

    @RequestMapping(method = {RequestMethod.DELETE}, value = "deleteTask")
    public void deleteTask(Long taskId) {

    }

    @RequestMapping(method = {RequestMethod.PATCH}, value = "updateTask")
    public TaskDto updateTask(TaskDto taskDto) {
        TaskDto task = new TaskDto(1L, "test title", "test content");
        task.setTitle("title2");
        return task;
    }

    @RequestMapping(method = {RequestMethod.PUT}, value = "createTask")
    public void createTask(TaskDto taskDto) {

    }
}
