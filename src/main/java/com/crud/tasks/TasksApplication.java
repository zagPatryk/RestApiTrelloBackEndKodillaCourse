package com.crud.tasks;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// heroku
@SpringBootApplication
public class TasksApplication {
    public static void main(String[] args) {
        SpringApplication.run(TasksApplication.class, args);
    }
}

//// local
//@SpringBootApplication
//public class TasksApplication extends SpringBootServletInitializer {
//    public static void main(String[] args) {
//        SpringApplication.run(TasksApplication.class, args);
//    }
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(TasksApplication.class);
//    }
//}