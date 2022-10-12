package com.mgr.MgrSpringApp.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AddressDTO {
	
	private long id;
	private String storeName;
	private String pincode;
	private String areaName;
	private String districtName;
	private String stateName;
	private String countryname;
	
}
