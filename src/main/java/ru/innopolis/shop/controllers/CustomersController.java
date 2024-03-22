package ru.innopolis.shop.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.shop.dto.CustomerDto;
import ru.innopolis.shop.exceptions.CustomerHasNoLastnameException;
import ru.innopolis.shop.services.CustomersService;

import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@Controller
@RequestMapping("/customers")
@Tag(name="Контроллер покупателей", description = "Контроллер CRUD с покупателями на сайте")
public class CustomersController {

    private final CustomersService customersService;

    private final MessageSource messageSource;

    @GetMapping
    public String getCustomersPage(Model model) {
        model.addAttribute("customers", customersService.getAll());
        return "customers";
    }

    @GetMapping("/getPageName")
    public ResponseEntity<String> pageTitleName() {
        return ResponseEntity.ok("Страница покупателей");
    }

    @GetMapping("/{customer-id}/*")
    @ResponseBody
    @Operation(
            summary = "Получение пользователя",
            description="Задать идентификатор для получения пользователя"
    )
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customer-id") @Parameter(description = "Идентификатор покупателя") Long customerId) {
        return ResponseEntity.ok(customersService.getById(customerId));
    }

    @GetMapping("/byParams")
    @ResponseBody
    public ResponseEntity<CustomerDto> getCustomerByParamId(@RequestParam(name="id") Long customerId) {
        return ResponseEntity.ok(customersService.getById(customerId));
    }


    @PostMapping
    public ResponseEntity<?> addCustomer(@RequestBody @Valid CustomerDto customer,
                                                   Locale locale) {
        if(customer.getLastName() == null || customer.getLastName().isBlank()) {
            final var message = this.messageSource
                    .getMessage("${model.create.details.errors.not_set}", new Object[0], locale);
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new CustomerHasNoLastnameException(message));
        }
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customersService.addCustomer(customer));
    }

    @PutMapping("/test/{customer-id}/{new-name}")
    public ResponseEntity<String> updateCustomerByName(@PathVariable("customer-id") Long customerId,
                                                    @PathVariable("new-name") String newName) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customerId);
        customerDto.setFirstName(newName);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(customersService.updateCustomer(customerId, customerDto, true).getFirstName());
    }

    @PutMapping("/{customer-id}")
    public ResponseEntity<CustomerDto> updateAuthor(@PathVariable("customer-id") Long customerId,
                                                  @Valid @RequestBody CustomerDto newData) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(customersService.updateCustomer(customerId, newData, false));
    }

    @DeleteMapping("/{author-id}/favorites/{post-id}")
    public ResponseEntity<?> deletePostFromFavorites(@PathVariable("customer-id") Long customerId) {
        customersService.deleteById(customerId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED).build();
    }
}
