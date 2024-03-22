package ru.innopolis.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.innopolis.shop.dto.ParticipantDto;
import ru.innopolis.shop.dto.ParticipantPage;
import ru.innopolis.shop.models.User;
import ru.innopolis.shop.repositories.AccountsRepository;

import java.util.List;

import static ru.innopolis.shop.dto.ParticipantDto.from;

@RequiredArgsConstructor
@Service
public class ParticipantsServiceImpl implements ParticipantsService{
    private final AccountsRepository accountsRepository;

    @Value("${default.page-size}")
    private int defaultPageSize;

    @Override
    public List<ParticipantDto> getAllParticipants() {
        return from(accountsRepository.findAll());
    }

    @Override
    public ParticipantPage getAllParticipantsByPage(int page) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize);
        Page<User> userByPage = accountsRepository.findAll(pageRequest);

        return ParticipantPage.builder()
                .participants(from(userByPage.getContent()))
                .totalPagesCount(userByPage.getTotalPages())
                .build();
    }

    @Override
    public ParticipantDto getParticipant(Long id) {
        return from(accountsRepository.findById(id).orElseThrow(RuntimeException::new));
    }

    @Override
    public ParticipantDto saveParticipant(ParticipantDto participantDto) {
        User newParticipant = new User();
        newParticipant.setFirstName(participantDto.getFirstName());

        //TODO добавить параметры и проверку
        return from(accountsRepository.save(newParticipant));
    }

    @Override
    public ParticipantDto getParticipantByEmail(String email) {
        return from(accountsRepository.findByEmail(email).orElseThrow(RuntimeException::new));
    }
}
