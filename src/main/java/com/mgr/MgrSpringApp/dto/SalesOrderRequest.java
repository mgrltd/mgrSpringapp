package com.mgr.MgrSpringApp.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesOrderRequest {
	
	private Long userid;
	private Long addressid;
	private List<SalesOrderItemsRequest> salesOrderItems; 

}
