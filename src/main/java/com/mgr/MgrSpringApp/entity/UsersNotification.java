package com.mgr.MgrSpringApp.entity;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder  
public class UsersNotification {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)  
        private Long id;
        @ManyToOne
       private Users users;
        @ManyToOne 
        private  Notifications notification;
        
        

}