package com.pm.authservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  @Column(unique = true,nullable = false)
  private String auth0Id;
  @Column(unique = true, nullable = false)
  private String email;
  @Column
  private String name;
  @Column
  private String addressLine1;
  @Column
  private String city;
  @Column
  private String country;



}
