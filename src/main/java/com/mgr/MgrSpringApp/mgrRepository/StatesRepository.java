package com.mgr.MgrSpringApp.mgrRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mgr.MgrSpringApp.entity.Country;
import com.mgr.MgrSpringApp.entity.States;
@Repository
public interface StatesRepository extends JpaRepository<States, Long> 
{
	

	@Query("SELECT new com.mgr.MgrSpringApp.entity.States(states.id,states.stateName) FROM States states where states.country=?1")
	List<States> getAllStatesByCountryId(Country countryId);

}
