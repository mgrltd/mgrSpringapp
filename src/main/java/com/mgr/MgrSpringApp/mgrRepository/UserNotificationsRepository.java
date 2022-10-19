package com.mgr.MgrSpringApp.mgrRepository;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mgr.MgrSpringApp.entity.Notifications;
import com.mgr.MgrSpringApp.entity.UserNotifications;
@Repository
public interface UserNotificationsRepository extends JpaRepository<UserNotifications, Long> {

	@Query(value="SELECT n.id,n.message,n.date FROM mgr.user_notifications un join mgr.notifications n where n.id=un.notifications_id and n.from_user_id=?;",nativeQuery=true)
	List<Tuple> findByuserId(Long id);

	@Query(value="SELECT n.id,n.from_user_id,n.message,n.date,u.user_name,u.photo_id FROM mgr.user_notifications un join mgr.notifications n,mgr.users u where un.notifications_id=n.id and n.from_user_id=u.id and un.to_user=?;",nativeQuery = true)
	List<Tuple> findByUserDetails(Long id);

}
