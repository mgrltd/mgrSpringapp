package com.mgr.MgrSpringApp.mgrService;

import com.mgr.MgrSpringApp.dto.NotificationRequest;
import com.mgr.MgrSpringApp.response.ApiResponse;

public interface NotificationsService {

    ApiResponse postNotificationByUser(Long fromUser, NotificationRequest notificationRequest);
    
}
