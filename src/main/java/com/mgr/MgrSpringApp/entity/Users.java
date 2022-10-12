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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users
 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;
    @OneToOne
    private Photo photo;
    private String firstName;
    private String lastName;
    private String userName;
    private String phoneNumber;
    private String emailId;
    private String password;    
    @OneToOne
    private Roles role;
    // @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    // private Set<Roles> role;

    // public void role(Roles roles)
    // {
    //     role.add(roles);
    // }

    public Users(Long id, String userName, String phoneNumber, String emailId) {
        this.id = id;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.emailId = emailId;
    }
}
