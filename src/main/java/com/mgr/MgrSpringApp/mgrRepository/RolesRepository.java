package com.mgr.MgrSpringApp.mgrRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mgr.MgrSpringApp.entity.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles,Long>
{
    
}
