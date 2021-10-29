package com.alpian.customerMapper.repository.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer implements Serializable {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;

  @Column(name = "customer_id", nullable = false)
  private int customerId;

  @Column(name = "external_id", nullable = false)
  private String externalId;

  @Column(name = "created_at")
  private LocalDate createdAt;
}
