package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
public class TaskController {

    @Autowired
    private DbService dbService;
    @Autowired
    private TaskMapper taskMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/tasks")
    public List<TaskDto> getAllTasks() {
        return taskMapper.mapTaskDtoList(dbService.getAllTasks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{taskId}")
    public TaskDto getTask(@PathVariable Long taskId) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto((dbService.getTask(taskId)).orElseThrow(TaskNotFoundException::new));
    }

    @RequestMapping(method = {RequestMethod.POST}, value = "/tasks", consumes = {"text/plain", "application/*"})
    public TaskDto createTask(@RequestBody TaskDto taskDto) {
        return taskMapper.mapToTaskDto(dbService.saveTask(taskMapper.mapToTask(taskDto)));
    }

    @RequestMapping(method = {RequestMethod.PUT}, value = "/tasks", consumes = {"text/plain", "application/*"})
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        return taskMapper.mapToTaskDto(dbService.saveTask(taskMapper.mapToTask(taskDto)));
    }

    @DeleteMapping(value = "/tasks/{taskId}", consumes = {"text/plain", "application/*"})
    public boolean deleteTask(@PathVariable Long taskId) {
        return dbService.deleteTaskById(taskId);
    }
}
