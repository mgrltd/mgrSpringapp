package com.mgr.MgrSpringApp.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNotifications {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;
    @ManyToOne
    @JoinColumn(name="to_user")
    private Users users;
    @ManyToOne 
    @JoinColumn(name="notifications_id")
    private  Notifications notification;
	public UserNotifications(Users users, Notifications notification) {
		super();
		this.users = users;
		this.notification = notification;
	}
	
    
    

}
