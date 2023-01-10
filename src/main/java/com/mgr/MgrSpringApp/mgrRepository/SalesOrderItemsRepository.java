package com.mgr.MgrSpringApp.mgrRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mgr.MgrSpringApp.entity.SalesOrderItems;

@Repository
public interface SalesOrderItemsRepository extends JpaRepository<SalesOrderItems, Long>
{
	

}
