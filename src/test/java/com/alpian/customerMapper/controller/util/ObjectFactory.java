package com.alpian.customerMapper.controller.util;

import static java.time.LocalDate.of;
import static java.util.UUID.randomUUID;

import com.alpian.customerMapper.dto.request.AddCustomerRequestDto;
import com.alpian.customerMapper.dto.response.CustomerResponse;
import com.alpian.customerMapper.repository.entity.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;

public class ObjectFactory {

  public static final String CUSTOMERS = "/api/v1/customer";
  public static final String CUSTOMER = CUSTOMERS + "/{customerId}";
  public final static String EXTERNAL_ID = randomUUID().toString();
  public final static int CUSTOMER_ID = 1;
  public final static String CREATED = "2021-10-10";
  public final static String FUTURE = "3021-10-10";
  public static final String ERROR_CODE = "$.errors[0].code";
  public static final String ERROR_DESCRIPTION = "$.errors[0].description";

  private static final ObjectMapper mapper = new ObjectMapper();

  public static AddCustomerRequestDto BuildAddCustomerRequestDto() {

    return AddCustomerRequestDto.builder()
        .customerId(CUSTOMER_ID)
        .createdAt(CREATED)
        .build();
  }

  public static AddCustomerRequestDto BuildFutureDateAddCustomerRequestDto() {

    return AddCustomerRequestDto.builder()
        .customerId(CUSTOMER_ID)
        .createdAt(FUTURE)
        .build();
  }

  public static CustomerResponse BuildCustomerResponse() {

    return CustomerResponse.builder()
        .externalId(EXTERNAL_ID)
        .build();
  }

  public static Customer BuildCustomer() {

    return Customer.builder()
        .customerId(CUSTOMER_ID)
        .externalId(EXTERNAL_ID)
        .createdAt(of(2021, 10, 10))
        .build();
  }

  public static String ObjectToJson(Object jsonObject) {
    String json;
    if (jsonObject == null) {
      json = "Null Object";
    } else {
      try {
        json = mapper.writeValueAsString(jsonObject);
      } catch (Exception e) {
        json = "Object could not be converted to Json Format";
      }
    }
    return json;
  }

}
