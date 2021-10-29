package com.alpian.customerMapper.dto.request;

import com.alpian.customerMapper.validator.ValidCreatedAtDate;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddCustomerRequestDto {

  @NotNull
  @Min(value = 0, message = "CustomerId must be positive")
  private int customerId;

  @NotNull
  @NotEmpty
  @ValidCreatedAtDate
  private String createdAt;
}
