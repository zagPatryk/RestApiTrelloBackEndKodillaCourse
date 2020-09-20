package com.crud.tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


//// heroku
//@SpringBootApplication/*(scanBasePackages={"com.crud.tasks.repository","com.crud.tasks.service"
//        ,"com.crud.tasks.controller","com.crud.tasks.domain","com.crud.tasks.mapper"})*/
//public class TasksApplication {
//    public static void main(String[] args) {
//        SpringApplication.run(TasksApplication.class, args);
//    }
//}

// local
@SpringBootApplication
public class TasksApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(TasksApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TasksApplication.class);
    }
}
