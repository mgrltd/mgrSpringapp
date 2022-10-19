package com.mgr.MgrSpringApp.mgrController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgr.MgrSpringApp.dto.Pagedata;
import com.mgr.MgrSpringApp.entity.Users;
import com.mgr.MgrSpringApp.mgrService.AdminService;
import com.mgr.MgrSpringApp.response.ApiResponse;
import com.mgr.MgrSpringApp.response.UserResponse;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
//@PreAuthorize("hasRole('ADMIN')")
//@RequestMapping("/admin")
public class AdminCountroller 
{
    @Autowired
    private AdminService adminService;
    
    
    @GetMapping("/getallcountrys")
    public  ApiResponse getAllCountry(){
    	 
      List<Country> allcountrys=adminService.getAllCountry();
      return new ApiResponse<>(200,"sucess",allcountrys);
    }
    
    @GetMapping("/getallstates")
    public ApiResponse getAllStates(){
    	List<States> allStates= adminService.getAllStates();
      return new ApiResponse<>(200,"sucess",allStates);

    @PostMapping("/getallusers")
    public List<UserResponse> getAllUsers(@RequestBody Pagedata pagedata)
    {
      System.out.println("---------------");
      return adminService.getAllUsers(pagedata);
    }
    
    @DeleteMapping("/deleteuserbyid/{id}")
    public ApiResponse deletUser(@PathVariable Long id)
    {
      return adminService.deletUserById(id);
    }
    
    @GetMapping("/getalldistricts")
    public ApiResponse getAllDistricts(){
    	List<Districts> allDistricts= adminService.getAllDistricts();
      return new ApiResponse<>(200,"sucess",allDistricts);

    }
    
    
    @GetMapping("/getallareas")
    public ApiResponse getAllAreas(){
    	List<Areas> allAreas= adminService.getAllAreas();
      return new ApiResponse<>(200,"sucess",allAreas);
    }

    @GetMapping("/getpincode/{pincode}")
   public AreasRequest getPincode(@PathVariable String pincode) {
		   return adminService.getPincode(pincode);
   }
    

   //-----------------------------------------------------------------------------------------------------------
    
    @GetMapping("/getallStores")

    public  ApiResponse getAllAddress(){
    	ApiResponse apiResponse=new ApiResponse();
    	try {
    			List<AddressDTO> listofStores=adminService.getAllAddress();
    			apiResponse.setResponseCode(200);
    			apiResponse.setResponseMessage("Storeslist");
    			apiResponse.setResponseBody(listofStores);
    	    	return apiResponse;
       	  }
        catch(Exception e) 
        {
	
	      apiResponse.setResponseCode(500);
	      apiResponse.setResponseMessage(" "+e);
	      apiResponse.setResponseBody(null);
	        return apiResponse;
       }
    }
    
  
    
    
    
    
}
