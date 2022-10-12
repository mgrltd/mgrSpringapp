package com.mgr.MgrSpringApp.mgrRepository;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mgr.MgrSpringApp.entity.Store;
import com.mgr.MgrSpringApp.response.AddressDTO;
@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
//@Query( "SELECT new com.mgr.MgrSpringApp.response.AddressDTO(s.id, s.storeName, a.areaName,a.pincode,d.districtName,st.stateName,c.countryName) FROM Store s Join s.area a Join a.districts d Join d.states st Join st.country")
	
//Hibernate: select laptop0_.id as id1_1_, laptop0_.brand_id as brand_id4_1_, laptop0_.name as name2_1_, laptop0_.price as price3_1_ from tbl_laptops laptop0_ cross join tbl_brand brand1_ where laptop0_.brand_id=brand1_.id and brand1_.brand=?

	@Query( "SELECT new com.mgr.MgrSpringApp.response.AddressDTO(s.id, s.storeName,a.areaName,a.pincode,d.districtName,st.stateName,c.countryName) FROM Store s JOIN Areas a, Districts d, States st, Country c where s.area=a.id and a.districts=d.id and d.states=st.id and st.country=c.id")
	List<AddressDTO> findAllAddress();

}
