package com.devfraz.reactivedemo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("users")
public class User {
  @Id private Integer id;

  @Column("first_name")
  private String firstName;

  @Column("last_name")
  private String lastName;

  @Column("email")
  private String email;

  private AuditMetadata auditMetadata;
}
