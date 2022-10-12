package com.mgr.MgrSpringApp.mgrRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mgr.MgrSpringApp.entity.OtpBoox;

@Repository
public interface OtpBooxRepository extends JpaRepository<OtpBoox,Long>
{

    OtpBoox findByEmail(String mail);
    
}
