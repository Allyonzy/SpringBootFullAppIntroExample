package ru.innopolis.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.shop.dto.SignUpDto;
import ru.innopolis.shop.models.User;
import ru.innopolis.shop.repositories.AccountsRepository;
import ru.innopolis.shop.util.EmailUtil;

import java.util.Collections;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SignUpServiceImpl implements SignUpService {

    private final AccountsRepository accountsRepository;

    private final EmailUtil emailUtil;

    @Transactional
    @Override
    public void signUp(SignUpDto accountForm) {
        User user = User.builder()
                .firstName(accountForm.getFirstName())
                .lastName(accountForm.getLastName())
                .email(accountForm.getEmail())
                .state(User.State.NOT_CONFIRMED)
                .confirmCode(UUID.randomUUID().toString())
                .password(accountForm.getPassword())
                .build();

        accountsRepository.save(user);

        emailUtil.sendMail(user.getEmail(), "confirm", "confirm_mail",
                Collections.singletonMap("confirm_code", user.getConfirmCode()));

    }
}
