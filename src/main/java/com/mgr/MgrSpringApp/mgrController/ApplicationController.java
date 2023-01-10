 package com.mgr.MgrSpringApp.mgrController;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.mgr.MgrSpringApp.dto.LoginRequest;
import com.mgr.MgrSpringApp.dto.RegisterRequest;
import com.mgr.MgrSpringApp.dto.UpdatePasswordRequest;
import com.mgr.MgrSpringApp.entity.Areas;
import com.mgr.MgrSpringApp.entity.Country;
import com.mgr.MgrSpringApp.entity.Districts;
import com.mgr.MgrSpringApp.entity.Photo;
import com.mgr.MgrSpringApp.entity.States;
import com.mgr.MgrSpringApp.entity.Users;
import com.mgr.MgrSpringApp.mgrService.ApplicationService;
import com.mgr.MgrSpringApp.response.ApiResponse;
import com.mgr.MgrSpringApp.response.PincodeDetailsResponse;
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/mgr")
public class ApplicationController
{
    @Autowired
    private ApplicationService applicationService;
   
    
//login
    
    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginRequest loginRequest) throws Exception
    {
        System.out.println("--loginByUsername,password---"+loginRequest);
        return applicationService.login(loginRequest);
    }
    
    @GetMapping("/login/validateMailByOtp/{mail}/{otp}")
    public ApiResponse loginValidateMailByOtp(@PathVariable String mail,@PathVariable String otp)
    {
        return applicationService.loginValidateMailByOtp(mail,otp);
    }
    
    
  // forgetpassword 
    
    @GetMapping("/forgetpassword/sendOtpByMail/{mail}")
    public ApiResponse forgetpasswordSendOtpByMail(@PathVariable String mail)
    {
        return applicationService.forgetpasswordSendOtpByMail(mail);
    }

    @GetMapping("/forgetpassword/validateMailByOtp/{mail}/{otp}")
    public ApiResponse forgetpasswordValidateMailByOtp(@PathVariable String mail,@PathVariable String otp)
    {
    	 return applicationService.forgetpasswordValidateMailByOtp(mail,otp);
    }
   @PostMapping("/forgetpassword/updatePasswordByMail")
   public ApiResponse updatePasswordByMail(@RequestBody UpdatePasswordRequest updatePasswordRequest )
   {
	   return applicationService.updatePasswordByMail(updatePasswordRequest);
   }

   
//register 
   
    @PostMapping("/photoUpload")
    public Photo photoUpload(@RequestBody MultipartFile photo,@RequestParam(defaultValue = "null") String name) throws IOException
    {
//    	System.out.println("photoUpload function"+photo);
        return applicationService.photoUpload(photo,name);  
    }
    @PostMapping("/update/photoUpload")
    public Photo updatephotoupload(@RequestBody MultipartFile photo,@RequestParam Long id,@RequestParam(defaultValue = "null") String name) throws IOException
    {
    	System.out.println("UPdatephotoUpload function"+photo+"-id-"+id+"--name"+name);
        return applicationService.UpdatephotoUpload(id,name,photo);  
    }

    @PostMapping("/register")
    public ApiResponse userRegister(@RequestBody RegisterRequest registerRequest)
    {
    	System.out.println("---"+registerRequest);
     return applicationService.userRegister(registerRequest);
    }
    
    // general apis
        
    //Address apis
    @GetMapping("/address/getAllCountrys")
    public  ApiResponse getAllCountry()
    {
      List<Country> allcountrys=applicationService.getAllCountry();
      return new ApiResponse<>(200,"ok allcountrys",allcountrys);
    }
    
    @GetMapping("/address/getAllStatesByCountryId/{countryId}")
    public ApiResponse getAllStatesByCountryId(@PathVariable Long countryId) 
    {
    	System.out.println("---------------------");
    	try {
    		
        	List<States> allStates= applicationService.getAllStatesByCountryId(new Country().builder().id(countryId).build());
            return new ApiResponse<>(200,"ok allStates",allStates);
			
		} catch (Exception e) 
    	{
            return new ApiResponse<>(403," invalid countryId",null);
		}

    }
    
    @GetMapping("/address/getAllDistrictsByStateId/{stateId}")
    public ApiResponse getAllDistrictsByStateId(@PathVariable Long stateId){
    	try {
    		List<Districts> allDistricts= applicationService.getAllDistrictsByStateId(new States().builder().id(stateId).build());
    	      return new ApiResponse<>(200,"ok allDistricts",allDistricts);
			
		} catch (Exception e) 
    	{
            return new ApiResponse<>(403," invalid stateId",null);
		}
    	
    	

    }
    
    @GetMapping("/address/getAllAreasByDistrictId/{districtId}")
    public ApiResponse getAllAreasByDistrictId(@PathVariable Long districtId){
    	try {
    	 	List<Areas> allAreas= applicationService.getAllAreasByDistrictId(new Districts().builder().id(districtId).build());
    	      return new ApiResponse<>(200,"ok allAreas",allAreas);
		} catch (Exception e) 
    	{
            return new ApiResponse<>(403," invalid districtId",null);
		}
   
    }

    @GetMapping("/address/getAddresByPincode/{pincode}")
   public ApiResponse getAddresByPincode(@PathVariable String pincode)
    {
    	
    	try {
    		PincodeDetailsResponse pincodeDetailsResponse= applicationService.getAddresByPincode(pincode);
        	return new ApiResponse(200,"ok AddresByPincode",pincodeDetailsResponse);
			
		} catch (Exception e) 
    	{
            return new ApiResponse<>(403," invalid pincode",null);
		}
    	
   }
    
    
    
  
    
}
