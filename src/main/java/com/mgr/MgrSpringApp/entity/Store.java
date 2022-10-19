package com.mgr.MgrSpringApp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String storeName;
	@ManyToOne
	private Areas area;

}
