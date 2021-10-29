package com.alpian.customerMapper.service;

import static com.alpian.customerMapper.controller.util.ObjectFactory.BuildAddCustomerRequestDto;
import static com.alpian.customerMapper.controller.util.ObjectFactory.BuildCustomer;
import static java.util.Optional.of;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.quality.Strictness.LENIENT;

import com.alpian.customerMapper.mapper.CustomerMapper;
import com.alpian.customerMapper.repository.CustomerRepository;
import com.alpian.customerMapper.repository.entity.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.context.annotation.ComponentScan;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = LENIENT)
@ComponentScan({"com.alpian.*"})
class CustomerServiceTest {

  @Mock
  private CustomerMapper customerMapper;

  @Mock
  private CustomerRepository customerRepository;

  @InjectMocks
  private CustomerService unit;

  @Test
  public void should_AddCustomer() {

    Customer cus = BuildCustomer();
    when(customerMapper.toCustomer(BuildAddCustomerRequestDto()))
        .thenReturn(cus);

    unit.addCustomer(BuildAddCustomerRequestDto());

    verify(customerRepository, times(1)).save(cus);
  }

  @Test
  public void should_GetCustomer() {

    Customer cus = BuildCustomer();
    when(customerRepository.findByCustomerId(1))
        .thenReturn(of(cus));

    unit.getCustomer(1);

    verify(customerRepository).findByCustomerId(1);
  }
}