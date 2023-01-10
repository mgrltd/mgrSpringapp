package com.mgr.MgrSpringApp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;
    @Lob
    private byte[] photoData;
	private String name;

    public Photo(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
    

}
