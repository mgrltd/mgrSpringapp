package com.mgr.MgrSpringApp.mgrController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgr.MgrSpringApp.dto.AreasRequest;
import com.mgr.MgrSpringApp.entity.Areas;
import com.mgr.MgrSpringApp.entity.Country;
import com.mgr.MgrSpringApp.entity.Districts;
import com.mgr.MgrSpringApp.entity.States;
import com.mgr.MgrSpringApp.entity.Users;
import com.mgr.MgrSpringApp.mgrService.AdminService;
import com.mgr.MgrSpringApp.response.AddressDTO;
import com.mgr.MgrSpringApp.response.ApiResponse;

@RestController
//@PreAuthorize("hasRole('ADMIN')")
//@RequestMapping("/admin")
public class AdminCountroller 
{
    @Autowired
    private AdminService adminService;
    
    @GetMapping("/getall")
    public List<Users> getAllUsers()
    {
      return adminService.getAllUsers();
    }
    
    
    @GetMapping("/getallcountry")
    public List<Country> getAllCountry(){
    	return adminService.getAllCountry();
    }
    
    @GetMapping("/getallstate")
    public List<States> getAllStates(){
    	return adminService.getAllStates();
    }
    
    
    @GetMapping("/getalldistricts")
    public List<Districts> getAllDistricts(){
    	return adminService.getAllDistricts();
    }
    
    
    @GetMapping("/getallareas")
    public List<Areas> getAllAreas(){
    	return adminService.getAllAreas();
    }
    
    
    @GetMapping("/getallStores")

    public <T> ApiResponse<T> getAllAddress(){
    	ApiResponse apiResponse=new ApiResponse();
    	try {
    			List<AddressDTO> listofStores=adminService.getAllAddress();
    			apiResponse.setResponseCode(200);
    			apiResponse.setResponseMessage("Storeslist");
    			apiResponse.setResponseBody(listofStores);
    			
    	    	return apiResponse;
    	}
catch(Exception e) {
	
	apiResponse.setResponseCode(500);
	apiResponse.setResponseMessage(" "+e);
	apiResponse.setResponseBody(null);
	return apiResponse;
}
    }
    
    
    @GetMapping("/getpincode/{pincode}")
   public AreasRequest getPincode(@PathVariable String pincode) {
		   return adminService.getPincode(pincode);
   }
    
    
    
    
}
