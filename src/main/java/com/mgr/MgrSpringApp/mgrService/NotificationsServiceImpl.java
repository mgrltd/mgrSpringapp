package com.mgr.MgrSpringApp.mgrService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mgr.MgrSpringApp.dto.NotificationsRequest;
import com.mgr.MgrSpringApp.entity.Notifications;
import com.mgr.MgrSpringApp.entity.UserNotifications;
import com.mgr.MgrSpringApp.mgrRepository.NotificationsRepository;
import com.mgr.MgrSpringApp.mgrRepository.UserNotificationsRepository;

@Service
public class NotificationsServiceImpl implements NotificationsService{
	
	@Autowired
	private NotificationsRepository notificationrepo;
	
	
	@Autowired
	private UserNotificationsRepository usernotificationrepo;


	@Override
	public List<NotificationsRequest> getallnotifications(Long id) {
		List<Tuple> t = usernotificationrepo.findByuserId(id);
		System.out.println("52536u53"+t.size());
		for(int i=0;i<t.size();i++) {
			
			List<NotificationsRequest>n = new ArrayList<>();

		System.out.println(t.get(i).get(0));
			System.out.println(t.get(i).get(1));
			System.out.println(t.get(i).get(2));

		}
	
		return null;
		 
	}


	@Override
	public List<UserNotifications> getallusernotificationsdetails(Long id) {
		List<Tuple> t = usernotificationrepo.findByUserDetails(id);
		System.out.println("5356536u"+t.size());
		for(int i=0;i<t.size();i++) {
			
			System.out.println(t.get(i).get(0));
			System.out.println(t.get(i).get(1));
			System.out.println(t.get(i).get(2));
			System.out.println(t.get(i).get(3));
			System.out.println(t.get(i).get(4));
			System.out.println(t.get(i).get(5));
			
		}
		 return null;
		 
	}
	
	
	

}
