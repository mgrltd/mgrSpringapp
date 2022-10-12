package com.mgr.MgrSpringApp.mgrRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mgr.MgrSpringApp.entity.UsersNotification;
@Repository
public interface UsersNotificationRepository extends JpaRepository<UsersNotification,Long>
{
    
}
