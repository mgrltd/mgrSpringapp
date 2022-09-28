package com.mgr.MgrSpringApp.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    private String type;
    private String userName;
	private String userPassword;
}
