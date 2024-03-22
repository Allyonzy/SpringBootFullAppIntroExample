package ru.innopolis.shop.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import ru.innopolis.shop.controllers.CustomersController;
import ru.innopolis.shop.repositories.CustomersRepository;
import ru.innopolis.shop.services.CustomersService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName(value = "Customer Controller API is working when")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@WebMvcTest(controllers = CustomersController.class)
public class CustomersControllerAPITest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomersRepository customersRepository;

    @MockBean
    private CustomersService customerService;

    @Test
    public void should_return_title() throws Exception {
        mockMvc.perform(get("/customers/getPageName"))
                .andExpect(content().string("Страница покупателей"))
                .andExpect(status().is2xxSuccessful()
        );
    }

    /**
     *     @PostMapping
     *     public ResponseEntity<CustomerDto> addCustomer(@Valid @RequestBody CustomerDto customer) {
     *         return ResponseEntity
     *                 .status(HttpStatus.CREATED)
     *                 .body(customersService.addCustomer(customer));
     *     }
     */
    void handleAddCustomer_PayloadIsValid_ReturnsValidResponseEntity() throws Exception {
        //given
        String testObjStr = """
                    {
                     "id": 345,
                     "first_name": "Customer 1", 
                     "last_name": "Customer 1"
                    }
                """;
        var requestBuilder = post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(testObjStr);

        //when
        this.mockMvc.perform(requestBuilder)
                //then
                .andExpectAll(
                        status().isCreated(),
//                        header().exists(HttpHeaders.LOCATION),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json(testObjStr)
                );


    }


}
