package ru.innopolis.shop.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.innopolis.shop.dto.SignUpDto;
import ru.innopolis.shop.services.SignUpService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/signUp")
@Tag(name="Регистрация пользователя")
@Hidden
public class SignUpController {

    private final SignUpService signUpService;

    @GetMapping
    public String getSignUpPage() {
        return "signUp";
    }

    @PostMapping
    public String signUp(SignUpDto accountForm) {
        signUpService.signUp(accountForm);
        return "redirect:/signUp";
    }
}
