package com.mgr.MgrSpringApp.mgrService;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.mgr.MgrSpringApp.dto.SalesOrderRequest;
import com.mgr.MgrSpringApp.dto.UpdatePasswordRequest;
import com.mgr.MgrSpringApp.entity.Address;
import com.mgr.MgrSpringApp.entity.Cart;
import com.mgr.MgrSpringApp.entity.Items;
import com.mgr.MgrSpringApp.entity.OrderStatus;
import com.mgr.MgrSpringApp.entity.Products;
import com.mgr.MgrSpringApp.entity.Photo;
import com.mgr.MgrSpringApp.entity.SalesOrderItems;
import com.mgr.MgrSpringApp.entity.SalesOrders;
import com.mgr.MgrSpringApp.entity.UOM;
import com.mgr.MgrSpringApp.entity.Users;
import com.mgr.MgrSpringApp.mgrRepository.CartRepository;
import com.mgr.MgrSpringApp.mgrRepository.ProductsRepository;
import com.mgr.MgrSpringApp.mgrRepository.PhotoRepository;
import com.mgr.MgrSpringApp.mgrRepository.SalesOrderItemsRepository;
import com.mgr.MgrSpringApp.mgrRepository.SalesOrdersRepository;
import com.mgr.MgrSpringApp.mgrRepository.UserRepository;
import com.mgr.MgrSpringApp.response.ApiResponse;
import com.mgr.MgrSpringApp.response.ProductsResponse;
import com.mgr.MgrSpringApp.response.UserResponse;

@Service
public class UserServiceImp implements UserService
{
    @Autowired
   private UserRepository userRepository;

   @Autowired
   private PasswordEncoder passwordEncoder;
   
   @Autowired
   private CartRepository cartRepository;
   
   @Autowired
   private SalesOrdersRepository salesOrdersRepository;
   
   @Autowired
   private SalesOrderItemsRepository salesOrderItemsRepository;
   
   @Autowired
   private ProductsRepository productsRepository;

   public String getEncodePassword(String password)
   {
	return passwordEncoder.encode(password);
   }

@Override
	public Users loadUserByUserMail(String email) throws UsernameNotFoundException {
		return userRepository.findByEmailId(email);
//		System.out.println("user details by mail id"+user);
//		if (user != null) {
//			return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), getAuthorities(user));
//		} else {
//			throw new UsernameNotFoundException("Username is not valid");
//		}
	}

     @Override
    public UserDetails loadUserByUsername(String userName) {
	Users user = userRepository.findByUserName(userName);
	System.out.println("user details by mail id"+user);
	if (user != null) {
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), getAuthorities(user));
	} else {
		throw new UsernameNotFoundException("Username is not valid");
	}
}



	private Set<SimpleGrantedAuthority> getAuthorities(Users user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		//  user.getRole().forEach(role -> {
		//  	authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRole()));
		//  });
       authorities.add(new SimpleGrantedAuthority("ROLE_"+"USER"));
		return authorities;
	}


	@Override
	public ApiResponse userDataById(long id) {
		
		UserResponse user= userRepository.findUserDetailsById(id);
		System.out.println("--"+user);
	
		 return new ApiResponse(200,"UserDetailsForId:-"+id,user);
	}

	@Override
	public ApiResponse addItemToCart(Long userId, Long productId) 
	{
		Users user=new Users().builder().id(userId).build();
		Products product=new Products().builder().id(productId).build();
		System.out.println("--user--"+user+"-product-"+product);
		Cart cartitem=cartRepository.findByProductAndUsers(product,user);
		System.out.println("-----"+cartitem);
		if(cartitem==null)
		{
		Cart cart=new Cart();
		cart.setProduct(new Products().builder().id(productId).build());
		cart.setUsers(new Users().builder().id(userId).build());
		cartRepository.save(cart);
		return new ApiResponse(200,"Sucessfully Added");
		}
		else
		{
			return new ApiResponse(200,"Allrade added Sucessfully on ur cart");
		}
	}

	@Override
	public ApiResponse getAllCartProducts(Long userId) {
		
		List<ProductsResponse> AllCartProducts=cartRepository.getAllCartProductsByUserId(userId);
		
		return new ApiResponse(200,"AllCartItems",AllCartProducts);
	}

	@Override
	public ApiResponse deletCartItemById(Long id) {
		
		cartRepository.deleteById(id);		
		return new ApiResponse(200,"Remove sucessfully");
	}

	@Override
	public ApiResponse addSalesOrder(SalesOrderRequest salesOrderRequest) 
	{
		Integer orderQuantity=0;
		Double orderAmount=0.0;
		
		SalesOrders salesOrders=new SalesOrders();
		salesOrders.setUser(new Users().builder().id(salesOrderRequest.getUserid()).build());
		salesOrders.setOrderDate(new Date());
		salesOrders.setAddress(new Address().builder().id(salesOrderRequest.getAddressid()).build());
		SalesOrders newSalesOrders=salesOrdersRepository.save(salesOrders);
		List<SalesOrderItems> listOfSalesOrderItems=new ArrayList<>();
		for(int i=0;i<salesOrderRequest.getSalesOrderItems().size();i++)
		{
			Products product=new Products().builder().id(salesOrderRequest.getSalesOrderItems().get(i).getProductId()).build();
			
			Integer quantity=salesOrderRequest.getSalesOrderItems().get(i).getQuantity();
			SalesOrderItems salesOrderItems=new SalesOrderItems();
			salesOrderItems.setSoId(newSalesOrders);
			salesOrderItems.setProduct(product);
			salesOrderItems.setQuantity(quantity);
			Products getproduct=productsRepository.findById(salesOrderRequest.getSalesOrderItems().get(i).getProductId()).get();
			salesOrderItems.setMrp(getproduct.getMrp());
			salesOrderItems.setSellingPrice(getproduct.getSellingPrice());
			listOfSalesOrderItems.add(salesOrderItems);
			orderQuantity=orderQuantity+quantity;
			orderAmount=orderAmount+(getproduct.getSellingPrice()*quantity);	
		}
		
		newSalesOrders.setOrderAmount(orderAmount);
		newSalesOrders.setQuantity(orderQuantity);
		newSalesOrders.setOrderStatus(new OrderStatus().builder().id((long) 1).build());
		salesOrderItemsRepository.saveAll(listOfSalesOrderItems);
		salesOrdersRepository.save(newSalesOrders);
		
		return new ApiResponse(200,"orer place sucessfully");
	}

	
	@Override
	public ApiResponse getAllSalesOrder(Long userId) {
		
		List<SalesOrders> allsalesorders=salesOrdersRepository.findAllByUser(new Users().builder().id(userId).build());
		 return  new ApiResponse(200,"All User Sales Orders ",allsalesorders);
	}

	



	
	


	

}
