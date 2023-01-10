package com.mgr.MgrSpringApp.entity;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Users
 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;
    private Long photoId;
    private String firstName;
    private String lastName;
    private String userName;
    private String phoneNumber;
    private String emailId;
    private String password;    
    @OneToOne
    private Roles role;

    public Users(Long id, String userName, String phoneNumber, String emailId) {
        this.id = id;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
    }
}
