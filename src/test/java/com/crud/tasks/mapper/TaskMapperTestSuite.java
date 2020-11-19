package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TaskMapperTestSuite {
    @InjectMocks
    private TaskMapper taskMapper;
    @Test
    public void mapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(
                1L,
                "title",
                "content"
        );
        //When
        Task mappedTask = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(mappedTask.getId(), taskDto.getId());
        assertEquals(mappedTask.getTitle(), taskDto.getTitle());
        assertEquals(mappedTask.getContent(), taskDto.getContent());
    }

    @Test
    public void mapToTaskDto() {
        //Given
        Task task = new Task(
                1L,
                "title",
                "content"
        );
        //When
        TaskDto mappedTaskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals(mappedTaskDto.getId(), task.getId());
        assertEquals(mappedTaskDto.getTitle(), task.getTitle());
        assertEquals(mappedTaskDto.getContent(), task.getContent());
    }

    @Test
    public void mapTaskDtoList() {
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L,"title1","content1"));
        taskList.add(new Task(2L,"title2","content2"));

        //When
        List<TaskDto> mappedTaskDtoList = taskMapper.mapTaskDtoList(taskList);

        //Then
        assertEquals(mappedTaskDtoList.get(0).getId(), taskList.get(0).getId());
        assertEquals(mappedTaskDtoList.get(0).getTitle(), taskList.get(0).getTitle());
        assertEquals(mappedTaskDtoList.get(0).getContent(), taskList.get(0).getContent());

        assertEquals(mappedTaskDtoList.get(1).getId(), taskList.get(1).getId());
        assertEquals(mappedTaskDtoList.get(1).getTitle(), taskList.get(1).getTitle());
        assertEquals(mappedTaskDtoList.get(1).getContent(), taskList.get(1).getContent());
    }
}