package com.mskim.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * User
 */
@Entity
@Getter
@Setter
@ToString
public class User {

  @Id()
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(columnDefinition = "mediumint(8) unsigned")
  private int idx;
  
  @Column(length = 50, nullable = false)
  @ColumnDefault("''")
  private String name;

  @Column(columnDefinition = "tinyint(4) unsigned", nullable = false)
  @ColumnDefault("0")
  private byte age;
  
}