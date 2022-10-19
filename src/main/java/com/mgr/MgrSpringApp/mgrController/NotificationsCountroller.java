package com.mgr.MgrSpringApp.mgrController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Tuple;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mgr.MgrSpringApp.dto.NotificationRequest;
import com.mgr.MgrSpringApp.entity.Notifications;
import com.mgr.MgrSpringApp.entity.Users;
import com.mgr.MgrSpringApp.entity.UsersNotification;
import com.mgr.MgrSpringApp.mgrRepository.NotificationsRepository;
import com.mgr.MgrSpringApp.mgrRepository.UsersNotificationRepository;
import com.mgr.MgrSpringApp.response.ApiResponse;
import com.mgr.MgrSpringApp.response.NotificationResponse;
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class NotificationsCountroller 
{

    @Autowired
   private NotificationsRepository notificationsRepository;
   @Autowired
   private UsersNotificationRepository usersNotificationRepository;

   @GetMapping("/allNotifications")
   private List<Notifications> allNotifications()
   {
       
    List<Tuple> t= notificationsRepository.allNotifications();
    System.out.println("id "+t.get(0).get(0)+" msg "+t.get(0).get(1)+" date "+t.get(0).get(2));
      return null;
   }

   
   @GetMapping("/alluserNotifications/{id}")
   private List<NotificationResponse> alluserNotifications(@PathVariable Long id)
   {
    List<NotificationResponse>  allNotificationResponse=new ArrayList<>();
    List<Tuple> t= notificationsRepository.alluserNotifications(id);
    for(int i=0;i<t.size();i++)
    {
      //n.from_user_id,u.photo_id,u.user_name,n.id,n.message,n.date
      System.out.println("userid "+t.get(i).get(0)+" userPhoto "+t.get(i).get(1)+"  user name"+t.get(i).get(2)+" Notif id "+t.get(i).get(2)+"notif msg"+t.get(i).get(3)+" notif time "+t.get(i).get(4));

      NotificationResponse notificationResponse=new NotificationResponse((long) t.get(i).get(0).hashCode(),(long) t.get(i).get(1).hashCode(),t.get(i).get(2).toString(),(long) t.get(i).get(3).hashCode(),t.get(i).get(4).toString(),(Date) t.get(i).get(5));
      allNotificationResponse.add(notificationResponse);
    }
      return allNotificationResponse;
   }
   @Transactional
   @PostMapping("/postNotificationByUser/{id}")
   public ApiResponse postNotificationByUser(@PathVariable("id") Long fromUser,@RequestBody NotificationRequest notificationRequest)
   {
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
