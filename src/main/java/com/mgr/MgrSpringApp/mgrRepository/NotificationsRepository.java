package com.mgr.MgrSpringApp.mgrRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mgr.MgrSpringApp.entity.Notifications;
@Repository
public interface NotificationsRepository extends JpaRepository<Notifications, Long> {

}
