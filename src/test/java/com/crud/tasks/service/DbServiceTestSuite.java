package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DbServiceTestSuite {
    @Autowired
    private DbService dbService;
    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void getAllTasks() {
        //Given
        Task task = new Task();
        taskRepository.save(task);

        //When
        Iterable<Task> optionalTasks = dbService.getAllTasks();
        List<Task> taskList = new ArrayList<>();
        optionalTasks.forEach(taskList::add);

        //Then
        assertFalse(taskList.isEmpty());

        //Cleanup
        taskRepository.deleteById(task.getId());
    }

    @Test
    public void getTaskById() throws TaskNotFoundException {
        //Given
        Task task = new Task();
        taskRepository.save(task);

        //When
        Task loadedTask = dbService.getTask(task.getId()).orElseThrow(TaskNotFoundException::new);

        //Then
        assertEquals(loadedTask, task);

        //Cleanup
        taskRepository.deleteById(task.getId());
    }

    @Test
    public void getTask() throws TaskNotFoundException {
        //Given
        Task task = new Task();
        taskRepository.save(task);

        //When
        Task loadedTask = dbService.getTask(task.getId()).orElseThrow(TaskNotFoundException::new);

        //Then
        assertEquals(loadedTask, task);

        //Cleanup
        taskRepository.deleteById(task.getId());
    }

    @Test
    public void saveTask() throws TaskNotFoundException  {
        //Given
        Task task = new Task();

        //When
        dbService.saveTask(task);

        //Then
        assertEquals(dbService.getTask(task.getId()).orElseThrow(TaskNotFoundException::new), task);

        //Cleanup
        taskRepository.deleteById(task.getId());
    }

    @Test
    public void deleteTaskById() {
        //Given
        Task task = new Task();
        taskRepository.save(task);

        //When
        dbService.deleteTaskById(task.getId());

        //Then
        assertFalse(taskRepository.findById(task.getId()).isPresent());
    }
}