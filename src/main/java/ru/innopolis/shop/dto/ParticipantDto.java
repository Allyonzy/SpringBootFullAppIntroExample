package ru.innopolis.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.shop.models.User;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Сущность пользователя сайта")
public class ParticipantDto {
    @Size(min=2)
    private String firstName;
    private String lastName;
    @Min(
            value=18,
            message = "Минимальный возраст использования программы - 18 лет",
            groups = {ParticipantSave.class, ParticipantUpdate.class}
    )
    private Integer age;
    @Email
    @NotNull
    private String email;

    public static ParticipantDto from(User user) {
        return ParticipantDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .email(user.getEmail())
                .build();
    }

    public static List<ParticipantDto> from(List<User> users) {
        return users
                .stream()
                .map(ParticipantDto::from)
                .toList();
    }
}
