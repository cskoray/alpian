package com.alpian.customerMapper.controller;

import static com.alpian.customerMapper.controller.util.ObjectFactory.BuildAddCustomerRequestDto;
import static com.alpian.customerMapper.controller.util.ObjectFactory.BuildCustomerResponse;
import static com.alpian.customerMapper.controller.util.ObjectFactory.BuildFutureDateAddCustomerRequestDto;
import static com.alpian.customerMapper.controller.util.ObjectFactory.CUSTOMER;
import static com.alpian.customerMapper.controller.util.ObjectFactory.CUSTOMERS;
import static com.alpian.customerMapper.controller.util.ObjectFactory.ERROR_CODE;
import static com.alpian.customerMapper.controller.util.ObjectFactory.ERROR_DESCRIPTION;
import static com.alpian.customerMapper.controller.util.ObjectFactory.ObjectToJson;
import static com.alpian.customerMapper.exception.ErrorType.INVALID_PARAMETER_ERROR;
import static com.alpian.customerMapper.exception.ErrorType.NO_CUSTOMER_FOUND_ERROR;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alpian.customerMapper.exception.CustomerMapperException;
import com.alpian.customerMapper.exception.GlobalExceptionHandler;
import com.alpian.customerMapper.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
@ComponentScan({"com.alpian.*"})
class CustomerControllerTest {

  private MockMvc mockMvc;

  @Mock
  private CustomerService customerService;

  @InjectMocks
  private CustomerController unit;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders
        .standaloneSetup(unit)
        .setControllerAdvice(new GlobalExceptionHandler())
        .build();
  }

  @Test
  public void should_AddCustomer() throws Exception {

    doNothing()
        .when(customerService).addCustomer(BuildAddCustomerRequestDto());
    this.mockMvc
        .perform(
            post(CUSTOMERS)
                .content(ObjectToJson(BuildAddCustomerRequestDto()))
                .contentType(APPLICATION_JSON_VALUE))
        .andExpect(status().isOk());
  }

  @Test
  public void should_ThrowException_AddCustomer() throws Exception {

    this.mockMvc
        .perform(
            post(CUSTOMERS)
                .content(ObjectToJson(BuildFutureDateAddCustomerRequestDto()))
                .contentType(APPLICATION_JSON_VALUE))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath(ERROR_CODE).value(INVALID_PARAMETER_ERROR.getCode()))
        .andExpect(jsonPath(ERROR_DESCRIPTION).value(INVALID_PARAMETER_ERROR.getMessage()));
  }

  @Test
  public void should_GetCustomer() throws Exception {

    when(customerService.getCustomer(1)).thenReturn(BuildCustomerResponse());

    this.mockMvc
        .perform(get(CUSTOMER, "1"))
        .andExpect(status().isOk());
  }

  @Test
  public void should_ThrowException_GetCustomer() throws Exception {

    doThrow(new CustomerMapperException(NO_CUSTOMER_FOUND_ERROR))
        .when(customerService)
        .getCustomer(1);

    this.mockMvc
        .perform(get(CUSTOMER, "1"))
        .andExpect(status().isNotFound());
  }
}