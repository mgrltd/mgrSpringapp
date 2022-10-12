package com.mgr.MgrSpringApp.mgrController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgr.MgrSpringApp.dto.Pagedata;
import com.mgr.MgrSpringApp.entity.Users;
import com.mgr.MgrSpringApp.mgrService.AdminService;
import com.mgr.MgrSpringApp.response.ApiResponse;
import com.mgr.MgrSpringApp.response.UserResponse;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
//@PreAuthorize("hasRole('ADMIN')")
//@RequestMapping("/admin")
public class AdminCountroller 
{
    @Autowired
    private AdminService adminService;
    
    @PostMapping("/getallusers")
    public List<UserResponse> getAllUsers(@RequestBody Pagedata pagedata)
    {
      System.out.println("---------------");
      return adminService.getAllUsers(pagedata);
    }
    
    @DeleteMapping("/deleteuserbyid/{id}")
    public ApiResponse deletUser(@PathVariable Long id)
    {
      return adminService.deletUserById(id);
    }
}
