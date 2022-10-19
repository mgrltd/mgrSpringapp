package com.mgr.MgrSpringApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationsRequest {
	
	private Long id;
	private String message;
	private String date;

}
