package com.mgr.MgrSpringApp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Areas {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String areaName;
	private String pincode;
	@ManyToOne
	private Districts districts; 
	
	public Areas(Long id, String areaName, String pincode) {
		super();
		this.id = id;
		this.areaName = areaName;
		this.pincode = pincode;
	}

}
