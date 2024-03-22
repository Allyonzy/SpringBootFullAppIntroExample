package ru.innopolis.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Страница с участниками")
public class ParticipantPage {
    @Schema(description = "список участников")
    private List<ParticipantDto> participants;

    @Schema(description = "общее количество страниц", example = "5")
    private Integer totalPagesCount;
}
