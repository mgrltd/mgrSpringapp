package com.mgr.MgrSpringApp.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordRequest {
	
	private String forgetPasswordVerificationSucessKey;
	private String mail;
	private String password;
	

}
