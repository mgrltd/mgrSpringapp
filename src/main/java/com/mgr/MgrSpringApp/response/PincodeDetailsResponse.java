package com.mgr.MgrSpringApp.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PincodeDetailsResponse {

    public String pincode;
	public String areaName;
	public String districtName;
	public String stateName;
	public String countryName;
	
}
