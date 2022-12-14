package com.mgr.MgrSpringApp.mgrController;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.mgr.MgrSpringApp.dto.LoginRequest;
import com.mgr.MgrSpringApp.entity.Photo;
import com.mgr.MgrSpringApp.entity.Users;
import com.mgr.MgrSpringApp.mgrService.ApplicationService;
import com.mgr.MgrSpringApp.mgrService.UserService;
import com.mgr.MgrSpringApp.response.ApiResponse;
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
//@RequestMapping("/mgr")
public class ApplicationController
{
    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginRequest loginRequest) throws Exception
    {
        System.out.println("login-------------"+loginRequest);
        return applicationService.createJwtToken(loginRequest);
    }
     
    @GetMapping("forgetpassword/sendOtp/{mail}")
    public ApiResponse forgetpasswordSendOtp(@PathVariable String mail)
    {
        return applicationService.forgetpasswordSendOtp(mail);
    }



    
    @GetMapping("/validashanOtp/{mail}/{otp}")
    public ApiResponse validashanOtp(@PathVariable String mail,@PathVariable String otp)
    {
        return applicationService.validashanOtp(mail,otp);
    }

    @GetMapping("/photoUpload")
    public Photo photoUpload(@RequestBody MultipartFile photo) throws IOException
    {
        return applicationService.photoUpload(photo);
        
    }

    @PostMapping("/register")
    public ApiResponse userRegister(@RequestBody Users users)
    {
     return applicationService.userRegister(users);
    }
  
    
}
