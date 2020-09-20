package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.response.CreatedTrelloCard;
import com.crud.tasks.service.TrelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloService trelloService;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloService.fetchTrelloBoards();
    }

    @PostMapping(value = "createTrelloCard", consumes = {"text/plain", "application/*"})
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloService.createdTrelloCard(trelloCardDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoardsOld")
    public List<TrelloBoardDto> getTrelloBoardsOld() {
        List<TrelloBoardDto> trelloBoards = trelloService.fetchTrelloBoards();

        // Challange
        trelloBoards.stream()
                .filter(t -> !t.getId().isEmpty() && !t.getName().isEmpty())
                .filter(t -> t.getName().contains("Kodilla"))
                .forEach(trelloBoardDto -> System.out.println(trelloBoardDto.getId() + " "
                        + trelloBoardDto.getName()));

        // Rezbudowanie klienta
        trelloBoards.forEach(trelloBoardDto -> {
            System.out.println(trelloBoardDto.getName() + " - " + trelloBoardDto.getId());
            System.out.println("This board contains lists: ");
            trelloBoardDto.getLists().forEach(trelloList ->
                    System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed()));

        });
        return trelloBoards;
    }
}