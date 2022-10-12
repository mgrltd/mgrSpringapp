package com.mgr.MgrSpringApp.mgrRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mgr.MgrSpringApp.entity.Items;

@Repository
public interface ItemsRepository extends JpaRepository<Items,Long>
{
    
}
