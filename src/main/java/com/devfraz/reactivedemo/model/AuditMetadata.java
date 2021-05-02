package com.devfraz.reactivedemo.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.relational.core.mapping.Column;

import java.time.Instant;

public class AuditMetadata {
  @CreatedDate
  @Column("created_on")
  private Instant createdOn;
}
