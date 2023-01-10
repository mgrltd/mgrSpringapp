package com.mgr.MgrSpringApp.entity;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
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
public class UOM {
    @Id
    private Long id;
    @ManyToOne
    private Category category;
  
	private String UOM;
	  public UOM(Long id, String uOM) {
			super();
			this.id = id;
			UOM = uOM;
		}
}
