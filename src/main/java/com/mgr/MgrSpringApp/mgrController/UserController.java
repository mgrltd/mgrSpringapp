package com.mgr.MgrSpringApp.mgrController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mgr.MgrSpringApp.entity.Users;
import com.mgr.MgrSpringApp.mgrService.UserService;
@RestController
//@PreAuthorize("hasRole('USER')")
//@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getById/{id}")
    public Users userDataById(@PathVariable long id)
    {
      return userService.userDataById(id);
    }
    @GetMapping("/photo/{id}")
    private ResponseEntity<byte[]> getPhoto(@PathVariable Long id)
    {
      return userService.getPhoto(id);
    }
  
    
    

}
