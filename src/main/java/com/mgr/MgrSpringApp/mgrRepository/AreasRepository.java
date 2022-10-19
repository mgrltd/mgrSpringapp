package com.mgr.MgrSpringApp.mgrRepository;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mgr.MgrSpringApp.entity.Areas;
@Repository
public interface AreasRepository extends JpaRepository<Areas, Long> {

	@Query(value="SELECT a.pincode,a.area_name ,d.district_name,s.state_name,c.country_name FROM mgr.areas a join mgr.districts d,mgr.states s,mgr.country c where d.id=a.districts_id and s.id=d.states_id and c.id=s.country_id and a.pincode=?;",nativeQuery=true)
	Tuple findById(String pincode);

}
