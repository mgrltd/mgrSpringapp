package com.mgr.MgrSpringApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AreasRequest {
	
	public String pincode;
	public String areaName;
	public String districtName;
	public String stateName;
	public String countryName;

}
