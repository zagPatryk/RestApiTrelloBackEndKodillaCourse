package com.crud.tasks.client;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.response.CreatedTrelloCard;
import com.crud.tasks.domain.response.TrelloBadges;
import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TrelloClientTest {

    @InjectMocks
    private TrelloClient trelloClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TrelloConfig trelloConfig;

    @Before
    public void init() {
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloToken()).thenReturn("test");
    }

    @Test
    public void shouldFetchTrelloBoards() {
        // adnotacja @Before nie działa mi przed wywołaniem jej ręcznie tutaj <================================
        init();

        // Given
        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
        trelloBoards[0] = new TrelloBoardDto("test_id", "test_board", new ArrayList<>());

        // nie było sensu dodawać when dla key i tokena jeżeli wkleja się gotowy link
        // a skoro są dodane to funkcja z metody zadziała dobrze
        // ten test w formie z kursu cokolwiek sprawdzał? Czy wyrzucał po prostu 'zakończono' ze sztucznymi rezultatami?
        // https://kodilla.com/static/bootcamp-java/java-18_108.png
//        URI uri = new URI("http://test.com/members/5efdff5f0d223a4f42d58389/" +
//                "boards?key=test&token=test&fields=name,id&lists=all");

        when(restTemplate.getForObject(trelloClient.getUrlForTrelloBoards(), TrelloBoardDto[].class)).thenReturn(trelloBoards);

        // When
        List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();

        // Then
        assertEquals(1, fetchedTrelloBoards.size());
        assertEquals("test_id", fetchedTrelloBoards.get(0).getId());
        assertEquals("test_board", fetchedTrelloBoards.get(0).getName());
        assertEquals(new ArrayList<>(), fetchedTrelloBoards.get(0).getLists());
    }

    @Test
    public void shouldCreateCard() {
        // adnotacja @Before nie działa mi przed wywołaniem jej ręcznie tutaj <================================
        init();

        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Test task",
                "Test des",
                "top",
                "test_id");

        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards/")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc", trelloCardDto.getDes())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getListId()).build().encode().toUri();

        // nie uwzględnia zmian które były w zadaniu do poprzednich rozdziałów i trzeba dodać ręcznie
        CreatedTrelloCard createdTrelloCard = new CreatedTrelloCard(
                "Test Id",
                "Test task",
                "http://test.com",
                new TrelloBadges()
        );

        when(restTemplate.postForObject(url,null, CreatedTrelloCard.class)).thenReturn(createdTrelloCard);

        // When
        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);

        // Then
        assertEquals("Test Id", newCard.getId());
        assertEquals("Test task", newCard.getName());
        assertEquals("http://test.com", newCard.getShortUrl());
    }

    @Test
    public void shouldReturnEmptyList() {
        // adnotacja @Before nie działa mi przed wywołaniem jej ręcznie tutaj <================================
        init();

        // Given
        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[0];
        when(restTemplate.getForObject(trelloClient.getUrlForTrelloBoards(), TrelloBoardDto[].class)).thenReturn(null);

        // When
        List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();

        // Then
        assertEquals(0, fetchedTrelloBoards.size());
    }
}