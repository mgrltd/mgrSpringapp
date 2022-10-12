package com.mgr.MgrSpringApp.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpBoox {
    @Id
    private String email;
    private String otp;
    private LocalDateTime time;
    private String subject;
    
}
