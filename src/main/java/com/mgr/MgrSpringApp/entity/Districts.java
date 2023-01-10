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
public class Districts {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String districtName;
	@ManyToOne
	private States states; 
	
	public Districts(Long id, String districtName) {
		super();
		this.id = id;
		this.districtName = districtName;
	}

}
