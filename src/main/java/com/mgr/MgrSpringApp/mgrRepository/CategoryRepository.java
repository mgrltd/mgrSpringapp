package com.mgr.MgrSpringApp.mgrRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mgr.MgrSpringApp.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>
{
    
}
