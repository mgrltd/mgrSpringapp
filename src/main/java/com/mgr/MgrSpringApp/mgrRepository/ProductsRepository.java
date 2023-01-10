package com.mgr.MgrSpringApp.mgrRepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mgr.MgrSpringApp.entity.Items;
import com.mgr.MgrSpringApp.entity.Products;
import com.mgr.MgrSpringApp.entity.UOM;
import com.mgr.MgrSpringApp.response.PageResponse;
import com.mgr.MgrSpringApp.response.ProductsResponse;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long>
{

	Products findByItemsAndUom(Items item, UOM uom);

	@Query("select  new com.mgr.MgrSpringApp.response.ProductsResponse(product.id,product.photoId,item.itemCode,item.itemName,product.mrp,product.sellingPrice,product.weight,uom.UOM) from Products product join product.items item join product.uom uom join item.category category where category.id=:categoryId")
	List<ProductsResponse> FindAllProductsByCategoryId(@Param("categoryId") Long categoryId);
	

	@Query("select  new com.mgr.MgrSpringApp.response.ProductsResponse(product.id,product.photoId,item.itemCode,item.itemName,product.mrp,product.sellingPrice,category.categoryName,product.weight,uom.UOM) from Products product join product.items item join item.category category join product.uom uom")
	List<ProductsResponse> FindAllProducts();

	@Query("select  new com.mgr.MgrSpringApp.response.ProductsResponse(product.id,product.photoId,item.itemCode,item.itemName,product.mrp,product.sellingPrice,product.costPrice,category.categoryName,product.weight,uom.UOM) from Products product join product.items item join item.category category join product.uom uom")
	Page<ProductsResponse> getallproductsWithPagenashan(PageRequest pageRequest,String string);


}
