package com.mgr.MgrSpringApp.response;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse 
{
    private Long id;
    private Long photoId;
    private String userName;
    private String phoneNumber;
    private String emailId;
    private String role;
  
}
