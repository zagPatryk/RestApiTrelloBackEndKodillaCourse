package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTestSuite {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> trelloListsDto = new ArrayList<>();
        TrelloListDto trelloListDto1 = new TrelloListDto("id1","name1", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("id2","name2", true);
        TrelloListDto trelloListDto3 = new TrelloListDto("id3","name3", false);
        trelloListsDto.add(trelloListDto1);
        trelloListsDto.add(trelloListDto2);
        trelloListsDto.add(trelloListDto3);
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToLists(trelloListsDto);
        //Then
        assertEquals(trelloLists.get(0).getId(),trelloListDto1.getId());
        assertEquals(trelloLists.get(1).getId(),trelloListDto2.getId());
        assertEquals(trelloLists.get(2).getId(),trelloListDto3.getId());
        assertEquals(trelloLists.get(0).getName(),trelloListDto1.getName());
        assertEquals(trelloLists.get(1).getName(),trelloListDto2.getName());
        assertEquals(trelloLists.get(2).getName(),trelloListDto3.getName());
        assertEquals(trelloLists.get(0).isClosed(),trelloListDto1.isClosed());
        assertEquals(trelloLists.get(1).isClosed(),trelloListDto2.isClosed());
        assertEquals(trelloLists.get(2).isClosed(),trelloListDto3.isClosed());
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        TrelloList trelloList1 = new TrelloList("id1","name1", true);
        TrelloList trelloList2 = new TrelloList("id2","name2", true);
        TrelloList trelloList3 = new TrelloList("id3","name3", false);
        trelloLists.add(trelloList1);
        trelloLists.add(trelloList2);
        trelloLists.add(trelloList3);
        //When
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListsDto(trelloLists);
        //Then
        assertEquals(trelloListsDto.get(0).getId(),trelloList1.getId());
        assertEquals(trelloListsDto.get(1).getId(),trelloList2.getId());
        assertEquals(trelloListsDto.get(2).getId(),trelloList3.getId());
        assertEquals(trelloListsDto.get(0).getName(),trelloList1.getName());
        assertEquals(trelloListsDto.get(1).getName(),trelloList2.getName());
        assertEquals(trelloListsDto.get(2).getName(),trelloList3.getName());
        assertEquals(trelloListsDto.get(0).isClosed(),trelloList1.isClosed());
        assertEquals(trelloListsDto.get(1).isClosed(),trelloList2.isClosed());
        assertEquals(trelloListsDto.get(2).isClosed(),trelloList3.isClosed());
    }

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloListDto> trelloListsDto1 = new ArrayList<>();
        TrelloListDto trelloListDto1 = new TrelloListDto("id1","name1", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("id2","name2", false);
        trelloListsDto1.add(trelloListDto1);
        trelloListsDto1.add(trelloListDto2);
        List<TrelloListDto> trelloListsDto2 = new ArrayList<>();
        TrelloListDto trelloListDto3 = new TrelloListDto("id3","name3", true);
        TrelloListDto trelloListDto4 = new TrelloListDto("id4","name4", false);
        trelloListsDto2.add(trelloListDto3);
        trelloListsDto2.add(trelloListDto4);

        List<TrelloBoardDto> trelloBoardsDto = new ArrayList<>();
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("idb1", "nameb1", trelloListsDto1);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("idb2", "nameb2", trelloListsDto2);
        trelloBoardsDto.add(trelloBoardDto1);
        trelloBoardsDto.add(trelloBoardDto2);
        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);
        //Then
        assertEquals(trelloBoards.get(0).getId(),trelloBoardDto1.getId());
        assertEquals(trelloBoards.get(1).getId(),trelloBoardDto2.getId());
        assertEquals(trelloBoards.get(0).getName(),trelloBoardDto1.getName());
        assertEquals(trelloBoards.get(1).getName(),trelloBoardDto2.getName());
        assertEquals(trelloBoards.get(0).getLists().get(0).getId(),trelloBoardDto1.getLists().get(0).getId());
        assertEquals(trelloBoards.get(1).getLists().get(0).getId(),trelloBoardDto2.getLists().get(0).getId());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloList> trelloLists1 = new ArrayList<>();
        TrelloList trelloList1 = new TrelloList("id1","name1", true);
        TrelloList trelloList2 = new TrelloList("id2","name2", false);
        trelloLists1.add(trelloList1);
        trelloLists1.add(trelloList2);
        List<TrelloList> trelloLists2 = new ArrayList<>();
        TrelloList trelloList3 = new TrelloList("id3","name3", true);
        TrelloList trelloList4 = new TrelloList("id4","name4", false);
        trelloLists2.add(trelloList3);
        trelloLists2.add(trelloList4);

        List<TrelloBoard> trelloBoards = new ArrayList<>();
        TrelloBoard trelloBoard1 = new TrelloBoard("idb1", "nameb1", trelloLists1);
        TrelloBoard trelloBoard2 = new TrelloBoard("idb2", "nameb2", trelloLists2);
        trelloBoards.add(trelloBoard1);
        trelloBoards.add(trelloBoard2);
        //When
        List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        assertEquals(trelloBoardsDto.get(0).getId(),trelloBoard1.getId());
        assertEquals(trelloBoardsDto.get(1).getId(),trelloBoard2.getId());
        assertEquals(trelloBoardsDto.get(0).getName(),trelloBoard1.getName());
        assertEquals(trelloBoardsDto.get(1).getName(),trelloBoard2.getName());
        assertEquals(trelloBoardsDto.get(0).getLists().get(0).getId(),trelloBoard1.getLists().get(0).getId());
        assertEquals(trelloBoardsDto.get(1).getLists().get(0).getId(),trelloBoard2.getLists().get(0).getId());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("n1", "d1", "p1", "id1");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals(trelloCardDto.getName(),trelloCard.getName());
        assertEquals(trelloCardDto.getDes(),trelloCard.getDescription());
        assertEquals(trelloCardDto.getPos(),trelloCard.getPos());
        assertEquals(trelloCardDto.getListId(),trelloCard.getListId());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("n1", "d1", "p1", "id1");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals(trelloCardDto.getName(),trelloCard.getName());
        assertEquals(trelloCardDto.getDes(),trelloCard.getDescription());
        assertEquals(trelloCardDto.getPos(),trelloCard.getPos());
        assertEquals(trelloCardDto.getListId(),trelloCard.getListId());
    }
}