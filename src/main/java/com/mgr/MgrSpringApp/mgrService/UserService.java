package com.mgr.MgrSpringApp.mgrService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import com.mgr.MgrSpringApp.entity.Users;

public interface UserService 
{

    UserDetails loadUserByUsername(String mail);
    Users userDataById(long id);
    ResponseEntity<byte[]> getPhoto(Long id);

    
}
