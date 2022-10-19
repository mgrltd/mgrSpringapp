package com.mgr.MgrSpringApp.mgrRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mgr.MgrSpringApp.entity.Districts;

@Repository
public interface DistrictsRepository extends JpaRepository<Districts, Long> {

}
