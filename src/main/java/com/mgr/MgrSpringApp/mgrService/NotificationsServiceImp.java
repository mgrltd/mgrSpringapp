package com.mgr.MgrSpringApp.mgrService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mgr.MgrSpringApp.dto.NotificationRequest;
import com.mgr.MgrSpringApp.entity.Notifications;
import com.mgr.MgrSpringApp.entity.Users;
import com.mgr.MgrSpringApp.entity.UsersNotification;
import com.mgr.MgrSpringApp.mgrRepository.NotificationsRepository;
import com.mgr.MgrSpringApp.mgrRepository.UsersNotificationRepository;
import com.mgr.MgrSpringApp.response.ApiResponse;

@Service
public class NotificationsServiceImp implements NotificationsService
{
    @Autowired
    private NotificationsRepository notificationsRepository;
    @Autowired
    private UsersNotificationRepository usersNotificationRepository;


   @Transactional
    @Override
    public ApiResponse postNotificationByUser(Long fromUser, NotificationRequest notificationRequest) {
        System.out.println("fromUser"+fromUser+" notificationRequest "+notificationRequest);

        Notifications notifications=new Notifications();
        notifications.setFromUser(new Users().builder().id(fromUser).build());
        notifications.setMessage(notificationRequest.getMsg());
        notifications.setDate(new Date());
        Notifications savenotifications =notificationsRepository.save(notifications);
        System.out.println("notification save suessfully"+savenotifications);
        List<UsersNotification> listOfusersNotification=new ArrayList();
        for(int i=0;i<notificationRequest.getToUserIds().size();i++)
        {
          UsersNotification usersNotification=new UsersNotification();
          usersNotification.setNotification(savenotifications);
          usersNotification.setUsers(new Users().builder().id(notificationRequest.getToUserIds().get(i)).build());
          listOfusersNotification.add(usersNotification);  
        }
        usersNotificationRepository.saveAll(listOfusersNotification);
    return  new ApiResponse(200,"sucessfullysend");
    }
   
}
