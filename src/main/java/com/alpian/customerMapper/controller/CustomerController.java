package com.alpian.customerMapper.controller;

import static com.alpian.customerMapper.constants.RestApiUrls.CUSTOMER;
import static com.alpian.customerMapper.constants.RestApiUrls.CUSTOMERS;
import static org.springframework.http.ResponseEntity.ok;

import com.alpian.customerMapper.dto.request.AddCustomerRequestDto;
import com.alpian.customerMapper.dto.response.CustomerResponse;
import com.alpian.customerMapper.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(CUSTOMERS)
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @ApiOperation(value = "add new customer", nickname = "addCustomer", response = ResponseEntity.class)
  @PostMapping
  public ResponseEntity<String> addCustomer(
      @Valid @RequestBody AddCustomerRequestDto addCustomerRequestDto) {

    customerService.addCustomer(addCustomerRequestDto);
    return ok().build();
  }

  @ApiOperation(value = "get customer", nickname = "getCustomer", response = ResponseEntity.class)
  @GetMapping(CUSTOMER)
  public ResponseEntity<CustomerResponse> getCustomer(
      @Valid
      @PathVariable("customerId")
      @Min(value = 0, message = "CustomerId must be positive") int customerId) {

    CustomerResponse customer = customerService.getCustomer(customerId);
    return ok().body(customer);
  }
}
