package com.mgr.MgrSpringApp.mgrController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgr.MgrSpringApp.entity.Users;
import com.mgr.MgrSpringApp.mgrService.AdminService;

@RestController
//@PreAuthorize("hasRole('ADMIN')")
//@RequestMapping("/admin")
public class AdminCountroller 
{
    @Autowired
    private AdminService adminService;
    
    @GetMapping("/getall")
    public List<Users> getAllUsers()
    {
      return adminService.getAllUsers();
    }
}
