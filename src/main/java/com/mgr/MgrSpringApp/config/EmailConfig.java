package com.mgr.MgrSpringApp.config;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
@Configuration
public class EmailConfig {

    @Autowired
    private  JavaMailSender javaMailSender;

    public String sendEmailConfig(String toMail,String subject,String body)
    {
        System.out.println("---sendEmailConfig--");
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("mgr.private.ltd@gmail.com");
        message.setTo(toMail);
        message.setText(body);
        message.setSubject(subject);
        javaMailSender.send(message);
        return "mail send sucessfully for "+subject;
    }
    public String sendEmailWithFileConfig(String toMail,String subject,String body,String filePath) throws MessagingException
    {
        try{
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("mgr.private.ltd@gmail.com");
        mimeMessageHelper.setTo(toMail);
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject(subject);
        FileSystemResource fileSystemResource=new FileSystemResource(new File(filePath));
        System.out.println(fileSystemResource);
        mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),fileSystemResource);
        javaMailSender.send(mimeMessage);
        return "mail send sucessfully for "+subject;
        }
        catch(Exception e){
            return "file not send"+e;
        }
        
    }
    
}
