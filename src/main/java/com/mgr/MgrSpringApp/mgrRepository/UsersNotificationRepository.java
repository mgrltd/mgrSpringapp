package com.mgr.MgrSpringApp.mgrRepository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mgr.MgrSpringApp.entity.Notifications;
import com.mgr.MgrSpringApp.entity.Users;
import com.mgr.MgrSpringApp.entity.UsersNotification;
@Repository
public interface UsersNotificationRepository extends JpaRepository<UsersNotification,Long>
{
    @Modifying
    @Transactional
    @Query("delete from UsersNotification un where un.notification=:notifications and un.users =:users")
    void deleteByIdAndUsers(Users users, Notifications notifications);

    UsersNotification findByUsersAndNotification(Users build, Notifications build2);
    
}
