package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DbService {

    @Autowired
    private TaskRepository taskRepository;

    public Iterable<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(long taskId) {
        return taskRepository.findById(taskId).orElseThrow(NullPointerException::new);
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
