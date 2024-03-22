package ru.innopolis.shop.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.innopolis.shop.controllers.api.ParticipantApi;
import ru.innopolis.shop.dto.ParticipantDto;
import ru.innopolis.shop.dto.ParticipantPage;
import ru.innopolis.shop.services.ParticipantsService;

@RestController
@RequiredArgsConstructor
public class ParticipantController implements ParticipantApi {

    private final ParticipantsService participantService;

    @Override
    public ResponseEntity<ParticipantPage> getAllParticipantByPage(int page) {
        return ResponseEntity.ok(participantService.getAllParticipantsByPage(page));
    }

    @Override
    public ResponseEntity<ParticipantDto> getParticipant(Long participantId) {
        return ResponseEntity.ok(participantService.getParticipant(participantId));
    }

    @Override
    public ResponseEntity<ParticipantDto> getParticipantByEmail(String email) {
        return ResponseEntity.ok(participantService.getParticipantByEmail(email));
    }

    @Override
    public ResponseEntity<ParticipantDto> saveParticipant(ParticipantDto participantDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(participantService.saveParticipant(participantDto));
    }
}
