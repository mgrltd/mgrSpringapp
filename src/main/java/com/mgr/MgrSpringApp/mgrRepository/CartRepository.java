package com.mgr.MgrSpringApp.mgrRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mgr.MgrSpringApp.entity.Cart;
import com.mgr.MgrSpringApp.entity.Products;
import com.mgr.MgrSpringApp.entity.Users;
import com.mgr.MgrSpringApp.response.ProductsResponse;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long>
{


	@Query("SELECT new com.mgr.MgrSpringApp.response.ProductsResponse(product.id,product.photoId,items.itemCode,items.itemName,product.mrp,product.sellingPrice,product.weight,uom.UOM) FROM Cart cart join cart.product product join product.items items join product.uom uom join cart.users user where user.id=:userId")
	List<ProductsResponse> getAllCartProductsByUserId(@Param("userId") Long userId);

	Cart findByProductAndUsers(Products build, Users build2);


}
