package com.alpian.customerMapper.mapper;

import static java.time.LocalDate.parse;
import static org.springframework.util.ObjectUtils.isEmpty;

import com.alpian.customerMapper.dto.request.AddCustomerRequestDto;
import com.alpian.customerMapper.dto.response.CustomerResponse;
import com.alpian.customerMapper.repository.entity.Customer;
import com.alpian.customerMapper.validator.WithTimezoneToLocalDate;
import java.time.LocalDate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {

  @Mapping(target = "createdAt", source = "createdAt", qualifiedBy = WithTimezoneToLocalDate.class)
  Customer toCustomer(AddCustomerRequestDto addCustomerRequestDto);

  CustomerResponse toCustomerResponse(Customer customer);

  @WithTimezoneToLocalDate
  default LocalDate map(String date) {
    return isEmpty(date) ? null : parse(date);
  }
}
