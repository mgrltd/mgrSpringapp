package com.mgr.MgrSpringApp.mgrController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mgr.MgrSpringApp.dto.NotificationsRequest;
import com.mgr.MgrSpringApp.entity.Notifications;
import com.mgr.MgrSpringApp.entity.UserNotifications;
import com.mgr.MgrSpringApp.mgrService.NotificationsService;


@RestController
public class NotificationsController {
	
	@Autowired
	private NotificationsService service;
	
	@GetMapping("/getallnotify/{id}")
	public List<NotificationsRequest> getallnotifications(@PathVariable Long id){
		return service.getallnotifications(id);
	}
	
	@GetMapping("/getallusernotifdetails/{id}")
	public List<UserNotifications> getallusernotificationsdetails(@PathVariable Long id){
		return service.getallusernotificationsdetails(id);
	}

}
