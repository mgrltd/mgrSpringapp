package com.mgr.MgrSpringApp.mgrService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import com.mgr.MgrSpringApp.dto.SalesOrderRequest;
import com.mgr.MgrSpringApp.dto.UpdatePasswordRequest;
import com.mgr.MgrSpringApp.entity.Users;
import com.mgr.MgrSpringApp.response.ApiResponse;

public interface UserService 
{

    Users loadUserByUserMail(String mail);
    
	UserDetails loadUserByUsername(String userName);

    
    ApiResponse userDataById(long id);

	ApiResponse addItemToCart(Long userId, Long productId);

	ApiResponse getAllCartProducts(Long userId);

	ApiResponse deletCartItemById(Long id);

	ApiResponse addSalesOrder(SalesOrderRequest salesOrderRequest);

	ApiResponse getAllSalesOrder(Long userId);
    
    
}
