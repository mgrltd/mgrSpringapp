package com.mgr.MgrSpringApp.mgrController;



import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgr.MgrSpringApp.config.EmailConfig;
import com.mgr.MgrSpringApp.dto.EmailRequest;
import com.mgr.MgrSpringApp.dto.LoginRequest;
import com.mgr.MgrSpringApp.dto.LoginResponse;
import com.mgr.MgrSpringApp.mgrService.ApplicationService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
//@RequestMapping("/api/hello")
public class HelloController {

    @Autowired
    private EmailConfig emailConfig;
    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/welcome")
    public String hello()
    {
        return "hello wellcome to mgrSpringApp --swagger url: http://localhost:8008/swagger-ui.html#/";
    }



















    
    @PostMapping(value="/sendemail")
    public String sendEmai(@RequestBody EmailRequest emailRequest) throws MessagingException  
    {
    String file=emailRequest.getFilePath();
    if(file==null)
    {
        System.out.println("mail send");
   return emailConfig.sendEmailConfig(emailRequest.getToEmail(),emailRequest.getSubject(),emailRequest.getBody());
    }
     // mail send with file  
     else{ 
        System.out.println("mail send with file");
    return emailConfig.sendEmailWithFileConfig(emailRequest.getToEmail(),emailRequest.getSubject(),emailRequest.getBody(),emailRequest.getFilePath());
     }
    }
    


}
