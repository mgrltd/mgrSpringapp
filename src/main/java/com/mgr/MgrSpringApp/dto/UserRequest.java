package com.mgr.MgrSpringApp.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
	
	    private String firstName;
	    private String lastName;
	    private String userName;
	    private String phoneNumber;
	    private String emailId;
	    private String password; 

}
