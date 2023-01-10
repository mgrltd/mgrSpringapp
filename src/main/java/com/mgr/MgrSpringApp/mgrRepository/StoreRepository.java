package com.mgr.MgrSpringApp.mgrRepository;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mgr.MgrSpringApp.entity.Store;
@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

}
