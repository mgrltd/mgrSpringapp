package com.mgr.MgrSpringApp.mgrController;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mgr.MgrSpringApp.dto.SalesOrderRequest;
import com.mgr.MgrSpringApp.entity.Users;
import com.mgr.MgrSpringApp.mgrService.UserService;
import com.mgr.MgrSpringApp.response.ApiResponse;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
//@PreAuthorize("hasRole('USER')")
@RequestMapping("/mgr/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getById/{id}")
    public ApiResponse userDataById(@PathVariable long id)
    {
      return userService.userDataById(id);
    }
//cart apis   
   @PostMapping("/cart/addItem") 
   public ApiResponse addItemToCart(@RequestParam Long userId,@RequestParam Long productId)
   {
	   return userService.addItemToCart(userId,productId);
   }
   
   @GetMapping("/cart/getAllProducts")
   public ApiResponse getAllCartProducts(@RequestParam Long userId)
   {
	   return userService.getAllCartProducts(userId);
   }
   @DeleteMapping("/cart/detetItem")
   public ApiResponse deletCartItemById(@RequestParam Long id)
   {
	   return userService.deletCartItemById(id);
   }
   
//salesorder apis 
   
   @PostMapping("/salesorder/addorder")
   public ApiResponse addSalesOrder(@RequestBody SalesOrderRequest salesOrderRequest)
   {
	   return userService.addSalesOrder(salesOrderRequest);
   }
   
   @GetMapping("/salesorder/getall")
   public ApiResponse getAllSalesOrder(@RequestParam Long userId)
   {
	   return userService.getAllSalesOrder(userId);
   }
   
   
   
}
