package com.mgr.MgrSpringApp.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ProductsResponse {

 
private Long id;
private Long photoId;
private String itemCode;
private String itemName;
private Double mrp;
private Double sellingPrice;
private Double costPrice;
private String categoryName;
private Double weight;
private String uomName;

public ProductsResponse(Long id, Long photoId, String itemCode, String itemName, Double mrp, Double sellingPrice,Double weight, String uomName) {
	super();
	this.id = id;
	this.photoId = photoId;
	this.itemCode = itemCode;
	this.itemName = itemName;
	this.mrp = mrp;
	this.sellingPrice = sellingPrice;
	this.weight=weight;
	this.uomName = uomName;
}

public ProductsResponse(Long id, Long photoId, String itemCode, String itemName, Double mrp, Double sellingPrice,
		String categoryName,Double weight, String uomName) {
	super();
	this.id = id;
	this.photoId = photoId;
	this.itemCode = itemCode;
	this.itemName = itemName;
	this.mrp = mrp;
	this.sellingPrice = sellingPrice;
	this.categoryName = categoryName;
	this.weight=weight;
	this.uomName = uomName;
}

}
