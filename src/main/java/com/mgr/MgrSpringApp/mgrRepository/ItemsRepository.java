package com.mgr.MgrSpringApp.mgrRepository;

import java.util.List;

import javax.persistence.Tuple;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mgr.MgrSpringApp.entity.Items;
import com.mgr.MgrSpringApp.response.ProductsResponse;

@Repository
public interface ItemsRepository extends JpaRepository<Items,Long>
{
    @Query("SELECT items FROM Items items")
    List<Items> getallItems(PageRequest withSort, String searchword, List<String> filterBy);
   
    
    
//    @Query(value ="SELECT new com.mgr.MgrSpringApp.response.ProductsResponse(item.id,item.photoId,item.itemCode,item.itemName,category.id) FROM Items item join item.category category where category.id=:categoryId")
//	List<ProductsResponse> FindAllProductsByCategoryId(@Param("categoryId") Long categoryId);
    
}
