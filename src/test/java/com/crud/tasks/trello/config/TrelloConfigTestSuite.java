package com.crud.tasks.trello.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloConfigTestSuite {
    @Autowired
    private TrelloConfig trelloConfig;

    @Test
    public void getTrelloApiEndpoint() {
        //Then
        assertEquals(trelloConfig.getTrelloApiEndpoint(), "https://api.trello.com/1");
    }

    @Test
    public void getTrelloAppKey() {
        //Then
        assertFalse(trelloConfig.getTrelloAppKey().isEmpty());
    }

    @Test
    public void getTrelloToken() {
        //Then
        assertFalse(trelloConfig.getTrelloToken().isEmpty());
    }

    @Test
    public void getTrelloUsername() {
        //Then
        assertFalse(trelloConfig.getTrelloUsername().isEmpty());
    }
}