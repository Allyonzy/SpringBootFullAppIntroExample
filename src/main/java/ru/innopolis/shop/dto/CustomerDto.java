package ru.innopolis.shop.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.shop.models.Customer;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Сущность покупателя")
public class CustomerDto {
    @Schema(description = "Идентификатор покупателя")
    @NotNull
    private Long id;

    @NotNull
    @Size(min=2)
    private String firstName;

    private String lastName;

    public static CustomerDto from(Customer customer) {
        return CustomerDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .build();
    }

    public static List<CustomerDto> from(List<Customer> customers) {
        return customers
                .stream()
                .map(CustomerDto::from)
                .collect(Collectors.toList());
    }
}
