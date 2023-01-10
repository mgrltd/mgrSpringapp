package com.mgr.MgrSpringApp.mgrRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mgr.MgrSpringApp.entity.SalesOrders;
import com.mgr.MgrSpringApp.entity.Users;

@Repository
public interface SalesOrdersRepository extends JpaRepository<SalesOrders, Long>
{

	List<SalesOrders> findAllByUser(Users build);

}
