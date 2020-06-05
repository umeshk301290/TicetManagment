package com.ticket.managment.TicketManagementSystem.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user",uniqueConstraints = @UniqueConstraint(columnNames = { "email" }))
@Getter
@Setter
@ToString
public class User {
 
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private Long id;
 
 @Column(name = "email")
 private String email;
 
 @Column(name = "firstname")
 private String firstname; 
 
 @Column(name = "lastname")
 private String lastname;
 
 @Column(name = "password")
 private String password;
 
 @OneToMany(fetch=FetchType.EAGER)
 @JoinColumn(name="userid")
 private List<ServiceTicket> ticket;

}