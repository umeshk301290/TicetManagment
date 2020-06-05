package com.ticket.managment.TicketManagementSystem.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ticket")
@Getter
@Setter
@ToString
public class ServiceTicket {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes="ticket reference id")
	private Long id;

	@Column(name = "department")
	@NotBlank(message = "department cannot be blank")
	@ApiModelProperty(notes ="department value",required=true)
	private String department;

	@Column(name = "details")
	@NotBlank(message = "details cannot be blank")
	@ApiModelProperty(notes ="ticket details",required=true)
	private String details;


	@Column(name = "createDate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	@ApiModelProperty(notes="ticket creation date")
	private Date createDate = new Date();
	

	@Column(name = "userid")
	@NotNull(message = "userId cannot be blank")
	@ApiModelProperty(notes ="user id",required=true)
	private  Long userId;

}
