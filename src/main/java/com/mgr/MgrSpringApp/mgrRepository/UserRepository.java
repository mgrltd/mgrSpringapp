package com.mgr.MgrSpringApp.mgrRepository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mgr.MgrSpringApp.entity.Users;
import com.mgr.MgrSpringApp.response.UserResponse;

@Repository
public interface UserRepository extends JpaRepository<Users,Long>
{

    Users findByUserName(String username);

    Users findByPhoneNumber(String name);

    Users findByEmailId(String name);

@Query("SELECT new com.mgr.MgrSpringApp.response.UserResponse(u.id,u.photoId,u.userName,u.phoneNumber,u.emailId,r.role) FROM Users u JOIN u.role r WHERE(u.userName like %?1% or u.emailId like %?1% or u.phoneNumber like %?1%) AND r.role In ?2")
Page<Users> getAllUsersWithPagenashanAndFilter(PageRequest withSort, String searchword, List<String> filterBy);

@Query("SELECT new com.mgr.MgrSpringApp.response.UserResponse(u.id,u.photoId,u.userName,u.phoneNumber,u.emailId,r.role) FROM Users u JOIN u.role r WHERE (u.userName like %?1% or u.emailId like %?1% or u.phoneNumber like %?1%)")
Page<Users> getAllUsersWithPagenashan(PageRequest withSort,String searchword);

@Query("SELECT new com.mgr.MgrSpringApp.response.UserResponse(u.id,u.photoId,u.userName,u.phoneNumber,u.emailId,r.role) FROM Users u JOIN u.role r WHERE u.id=:id")
UserResponse findUserDetailsById(@Param("id") long id);


    
  
}
