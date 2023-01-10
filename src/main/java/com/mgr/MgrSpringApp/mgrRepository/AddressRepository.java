package com.mgr.MgrSpringApp.mgrRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mgr.MgrSpringApp.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>
{

}
