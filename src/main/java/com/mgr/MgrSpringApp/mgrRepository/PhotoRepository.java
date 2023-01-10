package com.mgr.MgrSpringApp.mgrRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mgr.MgrSpringApp.entity.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo,Long>
{
    
}
