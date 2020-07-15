package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/task")
public class TaskController {
    @Autowired
    private DbService service;
    @Autowired
    private TaskMapper taskMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getTaskById")
    public TaskDto getTaskById(Long taskId) {
        return taskMapper.mapToTaskDto((service.getTaskById(taskId)));
    }

    @RequestMapping(method = {RequestMethod.PUT}, value = "createTask", consumes = {"text/plain", "application/*"})
    public void createTask(TaskDto taskDto) {
        service.saveTask(taskMapper.mapToTask(taskDto));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasks() {
        return taskMapper.mapTaskDtoList(service.getAllTasks());
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
}
