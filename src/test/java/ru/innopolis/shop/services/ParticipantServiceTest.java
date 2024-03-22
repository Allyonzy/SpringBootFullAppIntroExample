package ru.innopolis.shop.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.innopolis.shop.dto.ParticipantDto;
import ru.innopolis.shop.dto.ParticipantPage;
import ru.innopolis.shop.models.User;
import ru.innopolis.shop.repositories.AccountsRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ParticipantServiceTest {
    @MockBean
    private AccountsRepository accountsRepositoryMock;

    @Autowired
    private ParticipantsService participantsService = new ParticipantsServiceImpl(accountsRepositoryMock);

    private final List<ParticipantDto> participantDtos = new ArrayList<>();

    private ParticipantDto firstParticipant;
    private ParticipantDto secondParticipant;

    @BeforeEach
    void setUp() {
        //given
        firstParticipant = new ParticipantDto();
        firstParticipant.setFirstName("wt4etert");
        firstParticipant.setLastName("jghkhjklo;");
        firstParticipant.setAge(34);
        firstParticipant.setEmail("dfgdgfdhd@ya.ru");

        secondParticipant = new ParticipantDto();
        secondParticipant.setFirstName("35345345");
        secondParticipant.setLastName("ryr67tiutit;");
        secondParticipant.setAge(18);
        secondParticipant.setEmail("hjghjgy@ya.ru");

        participantDtos.add(firstParticipant);
        participantDtos.add(secondParticipant);
    }

    @Test
    void getAllParticipants_ReturnsParticipantDTOList() {
        Mockito.when(
                ParticipantDto.from(accountsRepositoryMock.findAll())
        ).thenReturn(
                participantDtos
        );

        List<ParticipantDto> foundList = participantsService.getAllParticipants();

        Assertions.assertFalse(foundList.isEmpty());
        Assertions.assertEquals(foundList.get(0), firstParticipant);
        Assertions.assertEquals(foundList.get(1), secondParticipant);
    }

    @Test
    void getAllParticipants_ReturnsParticipantDTOPage() {

    }

    @Test
    void getParticipantById_ReturnsParticipantDTO() {

    }

    @Test
    void saveParticipant_ReturnsParticipantDTO() {
        participantsService.saveParticipant(secondParticipant);

        User newParticipant = new User();
        newParticipant.setFirstName(secondParticipant.getFirstName());
        Mockito.verify(accountsRepositoryMock, Mockito.times(1)).save(newParticipant);
        Mockito.verifyNoMoreInteractions(this.accountsRepositoryMock);
    }

    @AfterEach
    void tearDown() {

    }
}
