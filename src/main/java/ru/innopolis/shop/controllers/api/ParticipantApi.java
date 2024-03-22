package ru.innopolis.shop.controllers.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tags;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.shop.dto.ExceptionDto;
import ru.innopolis.shop.dto.ParticipantDto;
import ru.innopolis.shop.dto.ParticipantPage;
import ru.innopolis.shop.dto.ParticipantSave;

@Tags(value = {
        @Tag(name= "Участники сайта"),
        @Tag(name= "Будущие покупатели")
})
@RequestMapping("/participants")
public interface ParticipantApi {

    @Operation(summary = "Получение списка участников")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница с участниками",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ParticipantPage.class))
                    })
    })
    @GetMapping
    ResponseEntity<ParticipantPage> getAllParticipantByPage(
            @Parameter(description = "Номер страницы") @RequestParam("page") int page
    );

    @Operation(summary = "Получение участника")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информация об участнике",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ParticipantDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    @GetMapping("/{participant-id}")
    ResponseEntity<ParticipantDto> getParticipant(
            @Parameter(description = "Идентификатор участника", example = "1")
            @PathVariable("participant-id") Long participantId);

    @GetMapping("/getByEmail/{participant-email}")
    public ResponseEntity<ParticipantDto> getParticipantByEmail(
            @Parameter(description = "Электронный адрес участника", example = "1")
            @Email @PathVariable("participant-email") String email
    );

    @PostMapping("/create")
    ResponseEntity<ParticipantDto> saveParticipant(
            @RequestBody @Validated(ParticipantSave.class) ParticipantDto participantDto);

    //TODO остальные методы

}
