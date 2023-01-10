package com.mgr.MgrSpringApp.mgrRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mgr.MgrSpringApp.entity.Districts;
import com.mgr.MgrSpringApp.entity.States;

@Repository
public interface DistrictsRepository extends JpaRepository<Districts, Long> 
{

	@Query("select new com.mgr.MgrSpringApp.entity.Districts(districts.id,districts.districtName) from Districts districts where districts.states=?1 ")
	List<Districts> getAllDistrictsByStateId(States stateId);

}
