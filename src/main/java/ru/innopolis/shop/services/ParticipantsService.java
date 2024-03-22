package ru.innopolis.shop.services;

import ru.innopolis.shop.dto.CustomerDto;
import ru.innopolis.shop.dto.ParticipantDto;
import ru.innopolis.shop.dto.ParticipantPage;

import java.util.List;

public interface ParticipantsService {
    List<ParticipantDto> getAllParticipants();
    ParticipantPage getAllParticipantsByPage(int page);
    ParticipantDto getParticipant(Long id);

    ParticipantDto saveParticipant(ParticipantDto participantDto);

    ParticipantDto getParticipantByEmail(String email);
}
