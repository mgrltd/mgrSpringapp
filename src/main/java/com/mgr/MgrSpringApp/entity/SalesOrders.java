package com.mgr.MgrSpringApp.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	@ManyToOne
	private Users user;
	private Date orderDate;
	private Double orderAmount;
	private Integer quantity;
	@ManyToOne
	private Address address;
	@OneToOne
	private OrderStatus orderStatus;
	

}
