package com.crud.tasks.service;

import com.crud.tasks.client.TrelloClient;
import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.response.CreatedTrelloCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class TrelloService {

    @Autowired
    private TrelloClient trelloClient;
    @Autowired
    private SimpleEmailService simpleEmailService;
    @Autowired
    private AdminConfig adminConfig;

    private static final String SUBJECT = "Tasks: New Trello Card";

    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCard createdTrelloCard(final TrelloCardDto trelloCardDto) {
        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);
        ofNullable(newCard).ifPresent(cart -> simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(), SUBJECT,
                "New card: " + trelloCardDto.getName() + " has been created.",
                "zzzaroo@gmail.com")));
        return newCard;
    }
}
