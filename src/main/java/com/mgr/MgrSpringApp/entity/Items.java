package com.mgr.MgrSpringApp.entity;

import javax.persistence.Entity;
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
public class Items 
{
@Id
private Long id;
@OneToOne
private Photo photo;
private String itemCode;
private String itemName;
private Double mrp;
private Double costPrice;
private Double sellingPrice;
@ManyToOne
private Category categoryId;
@ManyToOne
private UOM uomId;
    
}
