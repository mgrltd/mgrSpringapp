package com.mgr.MgrSpringApp.mgrRepository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import com.mgr.MgrSpringApp.entity.Users;
import com.mgr.MgrSpringApp.response.UserResponse;

@Repository
public interface UserRepository extends JpaRepository<Users,Long>
{

    Users findByUserName(String username);

    Users findByPhoneNumber(String name);

    Users findByEmailId(String name);

  @Query("SELECT new com.mgr.MgrSpringApp.response.UserResponse(u.id,p.id,u.userName,u.phoneNumber,u.emailId,r.role) FROM Users u JOIN u.photo p JOIN u.role r WHERE(u.userName like %?1% or u.emailId like %?1% or u.phoneNumber like %?1%) AND r.role In ?2")
  List<UserResponse> getAllUsersWithPagenashan(PageRequest pageRequest, String searchword, List<String> list);
 
    
  // @Query("SELECT new com.CropWiseGrower.response.UserDetails(u.id,u.userName,u.emailId,u.phoneNumber,u.role) FROM Users u WHERE (u.userName like %:searchword% or u.emailId like %:searchword% or u.phoneNumber like %:searchword% or u.role like %:searchword%)  and  u.role IN :listroles")
   //List<UserDetails> pagegetallusers(PageRequest of, String searchword, List<String> listroles);
    
}
