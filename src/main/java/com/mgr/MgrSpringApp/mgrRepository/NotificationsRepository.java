package com.mgr.MgrSpringApp.mgrRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mgr.MgrSpringApp.entity.Notifications;
@Repository
public interface NotificationsRepository extends JpaRepository<Notifications, Long> {

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mgr.MgrSpringApp.entity.Notifications;
import com.mgr.MgrSpringApp.entity.UsersNotification;
@Repository
public interface NotificationsRepository extends JpaRepository<Notifications,Long> 
{

    @Query(value = "SELECT n.id,n.message,n.date  FROM mgr.notifications n;",nativeQuery=true)
    List<Tuple> allNotifications();

    @Query(value = "SELECT n.from_user_id,u.photo_id,u.user_name,n.id,n.message,n.date FROM mgr.notifications n join mgr.users_notification un,mgr.users u where un.notification_id=n.id and u.id=n.from_user_id and un.users_id=?;",nativeQuery=true)
    List<Tuple> alluserNotifications(Long id);
    
}
