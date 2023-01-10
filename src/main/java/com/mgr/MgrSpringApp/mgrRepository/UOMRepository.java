package com.mgr.MgrSpringApp.mgrRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mgr.MgrSpringApp.entity.Category;
import com.mgr.MgrSpringApp.entity.UOM;

@Repository
public interface UOMRepository extends JpaRepository<UOM, Long>
{

	@Query("SELECT new com.mgr.MgrSpringApp.entity.UOM(uom.id,uom.UOM) from UOM uom where uom.category=:categoryId")
	List<UOM> findAllByCategory(@Param("categoryId") Category build);

}
