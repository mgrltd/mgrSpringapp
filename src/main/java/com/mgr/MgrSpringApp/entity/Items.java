package com.mgr.MgrSpringApp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
public class Items 
{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY) 
private Long id;
private Long photoId;
private String itemCode;
private String itemName;
@ManyToOne
private Category category;
public Items(Long id, Long photoId, String itemCode, String itemName) {
	super();
	this.id = id;
	this.photoId = photoId;
	this.itemCode = itemCode;
	this.itemName = itemName;
}
}
