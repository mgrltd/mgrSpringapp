package com.mgr.MgrSpringApp.mgrService;

import java.util.List;

import com.mgr.MgrSpringApp.dto.NotificationsRequest;
import com.mgr.MgrSpringApp.entity.Notifications;
import com.mgr.MgrSpringApp.entity.UserNotifications;

public interface NotificationsService {

	List<NotificationsRequest> getallnotifications(Long id);

	List<UserNotifications> getallusernotificationsdetails(Long id);

}
