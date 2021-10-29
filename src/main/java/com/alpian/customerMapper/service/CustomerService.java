package com.alpian.customerMapper.service;

import static com.alpian.customerMapper.exception.ErrorType.NO_CUSTOMER_FOUND_ERROR;
import static java.util.UUID.randomUUID;

import com.alpian.customerMapper.dto.request.AddCustomerRequestDto;
import com.alpian.customerMapper.dto.response.CustomerResponse;
import com.alpian.customerMapper.exception.CustomerMapperException;
import com.alpian.customerMapper.mapper.CustomerMapper;
import com.alpian.customerMapper.repository.CustomerRepository;
import com.alpian.customerMapper.repository.entity.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private CustomerMapper customerMapper;

  public void addCustomer(AddCustomerRequestDto addCustomerRequestDto) {

    Customer customer = customerMapper.toCustomer(addCustomerRequestDto);
    customer.setExternalId(randomUUID().toString());
    customerRepository.save(customer);
  }

  public CustomerResponse getCustomer(int customerId) {

    Customer customer = customerRepository.findByCustomerId(customerId)
        .orElseThrow(() -> new CustomerMapperException(NO_CUSTOMER_FOUND_ERROR));
    return customerMapper.toCustomerResponse(customer);
  }
}
