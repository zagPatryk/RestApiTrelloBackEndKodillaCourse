package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DbService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        List<Task> list = new ArrayList<>();
        for (Task task : taskRepository.findAll()) {
            list.add(task);
        }
        return list;
    }

    public Optional<Task> getTask(long taskId) {
        return taskRepository.findById(taskId);
    }

    public Task saveTask(final Task task) {
        return taskRepository.save(task);
    }

    public boolean deleteTaskById(Long taskId) {
        try{
            taskRepository.deleteById(taskId);
        } catch (IllegalArgumentException exception) {
            return false;
        } return true;
    }
}
