package com.mgr.MgrSpringApp.dto;

import com.mgr.MgrSpringApp.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse 
{
    private Users user;
	private String jwtToken;
}
