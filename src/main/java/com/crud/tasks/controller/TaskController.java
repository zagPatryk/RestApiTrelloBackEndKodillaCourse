package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/task")
public class TaskController {

    @Autowired
    private DbService dbService;
    @Autowired
    private TaskMapper taskMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getTaskById")
    public TaskDto getTaskById(@RequestParam Long taskId) {
        return taskMapper.mapToTaskDto((dbService.getTaskById(taskId)));
    }

    @RequestMapping(method = {RequestMethod.POST}, value = "createTask", consumes = {"text/plain", "application/*"})
    public TaskDto createTask(@RequestBody TaskDto taskDto) {
        return taskMapper.mapToTaskDto(dbService.saveTask(taskMapper.mapToTask(taskDto)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getAllTasks() {
        List<Task> list = new ArrayList<>();
        for (Task task : dbService.getAllTasks()) {
            list.add(task);
        }
        return taskMapper.mapTaskDtoList(list);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTask")
    public TaskDto getTask(@RequestParam Long taskId) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto((dbService.getTask(taskId)).orElseThrow(TaskNotFoundException::new));
    }

    @DeleteMapping(value = "deleteTask", consumes = {"text/plain", "application/*"})
    public boolean deleteTask(@RequestParam Long taskId) {
        return dbService.deleteTaskById(taskId);
    }

    @RequestMapping(method = {RequestMethod.PUT}, value = "updateTask", consumes = {"text/plain", "application/*"})
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        return taskMapper.mapToTaskDto(dbService.saveTask(taskMapper.mapToTask(taskDto)));
    }
}
