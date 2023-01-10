package com.mgr.MgrSpringApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddresRequest {
	   private String landMark;
			private String housNo;
			private Long areasId;
}
