package ru.innopolis.shop.controller;

import io.ktor.http.HttpStatusCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ru.innopolis.shop.controllers.ParticipantController;
import ru.innopolis.shop.dto.ParticipantDto;
import ru.innopolis.shop.dto.ParticipantPage;
import ru.innopolis.shop.services.ParticipantsService;

import java.util.List;

import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class ParticipantControllerModuleTest {

    @Mock
    ParticipantsService participantsService;

    @InjectMocks
    ParticipantController participantController;

    @Test
    @DisplayName("GET /participants возвращает HTTP-ответ со статусом 200 ОК и списком участников")
    void getAllParticipantByPage_ReturnsResponseEntity() {
        //given

        List<ParticipantDto> participantDtoList = List.of(
                new ParticipantDto("John", "Johnson", 45, "test@ya.ru"),
                new ParticipantDto("Lara", "Johnson", 23, "test@ya.ru"),
                new ParticipantDto("Tom", "Johnson", 15, "test@ya.ru")
        );
        ParticipantPage participantPage = new ParticipantPage(
                participantDtoList,
                8
        );

        doReturn(participantPage)
                .when(this.participantsService)
                .getAllParticipantsByPage(1);

        //when

        ResponseEntity<ParticipantPage> participantPageResponseEntity =
                this.participantController.getAllParticipantByPage(1);

        //then

        Assertions.assertNotNull(participantPageResponseEntity);
        Assertions.assertEquals(HttpStatus.OK, participantPageResponseEntity.getStatusCode());
        Assertions.assertEquals(MediaType.APPLICATION_JSON, participantPageResponseEntity.getHeaders().getContentType());
        Assertions.assertEquals(participantPage, participantPageResponseEntity.getBody());

    }
}
