package com.crud.tasks;

import com.crud.tasks.controller.TaskController;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.DbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TasksApplicationTests {
    @Autowired
    DbService dbService;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    TaskController taskController;

    @Test
    void test() {

//        Task task = new Task(12345l, "dsf", "fsdfsd");
//
//        taskRepository.save(task);

        System.out.println(dbService.getAllTasks());
        System.out.println(taskController.getAllTasks());

    }

}
