package com.mgr.MgrSpringApp.mgrRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mgr.MgrSpringApp.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users,Long>
{

    Users findByUserName(String username);

    Users findByPhoneNumber(String name);

    Users findByEmailId(String name);
    
}
