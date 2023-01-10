package com.mgr.MgrSpringApp.mgrService;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.authentication.AuthenticationManager;
import com.mgr.MgrSpringApp.config.EmailConfig;
import com.mgr.MgrSpringApp.config.JwtUtil;
import com.mgr.MgrSpringApp.dto.LoginRequest;
import com.mgr.MgrSpringApp.dto.RegisterRequest;
import com.mgr.MgrSpringApp.dto.UpdatePasswordRequest;
import com.mgr.MgrSpringApp.entity.Address;
import com.mgr.MgrSpringApp.entity.Areas;
import com.mgr.MgrSpringApp.entity.Country;
import com.mgr.MgrSpringApp.entity.Districts;
import com.mgr.MgrSpringApp.entity.OtpBoox;
import com.mgr.MgrSpringApp.entity.Photo;
import com.mgr.MgrSpringApp.entity.Roles;
import com.mgr.MgrSpringApp.entity.States;
import com.mgr.MgrSpringApp.entity.Users;
import com.mgr.MgrSpringApp.mgrRepository.AddressRepository;
import com.mgr.MgrSpringApp.mgrRepository.AreasRepository;
import com.mgr.MgrSpringApp.mgrRepository.CountryRepository;
import com.mgr.MgrSpringApp.mgrRepository.DistrictsRepository;
import com.mgr.MgrSpringApp.mgrRepository.OtpBooxRepository;
import com.mgr.MgrSpringApp.mgrRepository.PhotoRepository;
import com.mgr.MgrSpringApp.mgrRepository.RolesRepository;
import com.mgr.MgrSpringApp.mgrRepository.StatesRepository;
import com.mgr.MgrSpringApp.mgrRepository.StoreRepository;
import com.mgr.MgrSpringApp.mgrRepository.UserRepository;
import com.mgr.MgrSpringApp.response.ApiResponse;
import com.mgr.MgrSpringApp.response.PincodeDetailsResponse;
@Service
public class ApplicationService {

    @Autowired
	private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;

	@Autowired
    private RolesRepository rplesRepository;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
    private UserService userService;

	@Autowired
	private PhotoRepository photoRepository;
 
	@Autowired
	private OtpBooxRepository otpBooxRepository;

	@Autowired
	private EmailConfig emailConfig;
	
	    @Autowired
	    private CountryRepository countryrepo;
	    
	    @Autowired
	    private StatesRepository staterepo;
	    
	    @Autowired
	    private DistrictsRepository districtrepo;
	    
	    @Autowired
	    private AreasRepository arearepo;
	    
	    @Autowired
	    private StoreRepository storerepo;
 
	
	public String getEncodePassword(String password)
	{
	 return passwordEncoder.encode(password);
	}
	
	public String getGenerateOtp(String mail,String subject)
	{
		String newotp=new DecimalFormat("000000").format(new Random().nextInt(999999));
		 OtpBoox otp=new OtpBoox();
		 otp.setEmail(mail);
		 otp.setOtp(newotp);
		 otp.setSubject(subject);
		 LocalDateTime localDateTime = LocalDateTime.now();
		 otp.setTime(localDateTime);
		 otpBooxRepository.save(otp);
		return newotp;
	}
	
	public Boolean validateOtpByMail(String otp,String mail)
	{
		System.out.println("validate otp by mail:"+otp+" mail- "+mail);
		OtpBoox otpbox=otpBooxRepository.findByEmail(mail);
		if(otp.equals(otpbox.getOtp()))
		{
		return true;
		}
		else
		{
		 return false;
		}
	}
 
    public ApiResponse login(LoginRequest loginRequest) throws Exception
    {

        String type = loginRequest.getType();
        String  Name = loginRequest.getUserName();
		String Password = loginRequest.getUserPassword();
		 Users users=new Users();
		 
		 if(type.equals("byUsername"))
		 {
			System.out.println("========byUsername to login=======");
				users=userRepository.findByUserName(Name);		
		 }
		 if(type.equals("byEmail"))
		 {
			System.out.println("========byEmail to login=======");
			users = userRepository.findByEmailId(Name);
		 }
		 if(type.equals("byPhoneNumber"))
		 {		
			System.out.println("========byPhoneNumber to login=======");
			users = userRepository.findByPhoneNumber(Name);
		 }
	// authenticate(Name, userPassword);
		 // login authentication 
		 if(users==null)
		 {
			return new ApiResponse(404,"bad credentials User Notfound go to create new account");
		 }
		 if(!passwordEncoder.matches(Password,users.getPassword()))
		 {
			return new ApiResponse(401,"bad credentials password incorrect "+Name);
		 }		
		 String loginotp=getGenerateOtp(users.getEmailId(),"MGR Login Verification OTP");
		 
		 String s= emailConfig.sendEmailConfig(users.getEmailId(), "*MGR Login Verification OTP *", "MGR Verification OTP:"+loginotp);
		 
		 System.out.println("-Ok OTP send successfully to mail--"+loginotp);
		 return new ApiResponse(200,"Ok OTP send successfully to mail ",users.getEmailId());

    }

    
    
	// private void authenticate(String userName, String userPassword) throws Exception{
	//  	try {
	// 		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
	// 	} catch (DisabledException e) {
	// 		throw new Exception("User is disabled");
	// 	} catch(BadCredentialsException e) {
	// 		throw new Exception("Bad credentials from user");
	// 	}
	// }

// private UserDetails loadUserByUsername(String userName) {
//     Users user = userRepository.findByUserName(userName);
//     if (user != null) {
//         return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), getAuthorities(user));
//     } else {
//         throw new UsernameNotFoundException("Username is not valid");
//     }
//     }
    

public ApiResponse loginValidateMailByOtp(String mail, String otp) 
{
	System.out.println("--generateToken ofter otp validashan--"+mail+"OTP:"+otp);
	
		if(validateOtpByMail(otp,mail))
		{
			System.out.println("--otp valid sucessfully----");
			
	  	 Users user = userService.loadUserByUserMail(mail);
		 
	  	final UserDetails userDetails=userService.loadUserByUsername(user.getUserName());
	  	     
		 String newGeneratedToken = jwtUtil.generateToken(userDetails);
		 
		return new ApiResponse(200,"token generate sucessfully",newGeneratedToken);
		
		}
		else
		{
			return new ApiResponse(401,"otp not corret");
		}
	}


//forgetpassword

public ApiResponse forgetpasswordSendOtpByMail(String mail) 
    {
	String subject="*MGR ForgetPassword Verification OTP *";
	String otp=new DecimalFormat("000000").format(new Random().nextInt(999999));
	OtpBoox otpBox=new OtpBoox();
	otpBox.setEmail(mail);
	otpBox.setOtp(otp);
	otpBox.setSubject(subject);
	LocalDateTime localDateTime = LocalDateTime.now();
	otpBox.setTime(localDateTime);
    emailConfig.sendEmailConfig(mail, subject, "MGR FoegetPassword Verification OTP:"+otp +"dont share anay one its valid 10m ondly");
	otpBooxRepository.save(otpBox);	

    return new ApiResponse(200,"MGR FoegetPassword Verification OTP send sucessfully on ur mail:*"+mail+"*");
    }


public ApiResponse forgetpasswordValidateMailByOtp(String mail, String otp) {
	
	if(validateOtpByMail(otp,mail))
	{
		String ForgetPasswordVerificationSucessKey=getGenerateOtp(mail,"ForgetPasswordVerificationSucessKey");
	return new ApiResponse(200,"verifyOTp sucessfully",ForgetPasswordVerificationSucessKey);
	}
	else
	{
		return new ApiResponse(402,"invalid otp");
	}
}




public ApiResponse updatePasswordByMail(UpdatePasswordRequest updatePasswordRequest) 
{
	if(validateOtpByMail(updatePasswordRequest.getForgetPasswordVerificationSucessKey(),updatePasswordRequest.getMail()))
	{
	Users user=userRepository.findByEmailId(updatePasswordRequest.getMail());
	user.setPassword(getEncodePassword(updatePasswordRequest.getPassword()));
	 userRepository.save(user);
	 return new ApiResponse(200,"*Password Update sucessfully");
	}
	else
	{
		 return new ApiResponse(404,"*bade request, befour validate ur mail");
	}
}

	public ApiResponse userRegister(RegisterRequest registerRequest) 
      {

     ApiResponse response=new ApiResponse();
    try {
		Users newuser=new Users();
		
		newuser.setPhotoId(registerRequest.getPhotoId());
				newuser.setFirstName(registerRequest.getUserRequest().getFirstName());
		newuser.setLastName(registerRequest.getUserRequest().getLastName());
		newuser.setUserName(registerRequest.getUserRequest().getUserName());
		newuser.setEmailId(registerRequest.getUserRequest().getEmailId());
		newuser.setPhoneNumber(registerRequest.getUserRequest().getPhoneNumber());
		newuser.setPassword(getEncodePassword(registerRequest.getUserRequest().getPassword()));
		newuser.setRole(new Roles().builder().id((long)1).build());
        Users user=userRepository.save(newuser);
        
        Address newAddress=new Address();
        newAddress.setHousNo(registerRequest.getAddresRequest().getHousNo());
        newAddress.setLandMark(registerRequest.getAddresRequest().getLandMark());
        newAddress.setAreas(new Areas().builder().id(registerRequest.getAddresRequest().getAreasId()).build());
        newAddress.setUsers(user);
        addressRepository.save(newAddress);
        
        response.setResponseCode(200);
        response.setResponseMessage("user save sucessfully");
		response.setResponseBody(user);
                
    } catch (Exception e)
     {
        response.setResponseCode(403);
        response.setResponseMessage("user not save");
		response.setResponseBody(e);

    }
    return response;
}

	public Photo photoUpload(MultipartFile photo, String name) throws IOException {
		if(name.equals("null"))
			name=photo.getOriginalFilename();	
		Photo photos=new Photo();
		photos.setName(name);
		photos.setPhotoData(photo.getBytes());
		return photoRepository.save(photos);
	}

	public Photo UpdatephotoUpload(Long id, String name, MultipartFile photo) throws IOException 
	{
	
		if(name.equals("null"))
			name=photo.getOriginalFilename();
		Photo getphoto=photoRepository.findById(id).get();
		getphoto.setName(name);
		getphoto.setPhotoData(photo.getBytes());
		return photoRepository.save(getphoto);
	}

	
	   public List<Country> getAllCountry() {
	       return countryrepo.findAll();
	   }

	   public List<States> getAllStatesByCountryId(Country countryId){
	       return staterepo.getAllStatesByCountryId(countryId);
	   }

	   public List<Districts> getAllDistrictsByStateId(States stateId) {
	       return districtrepo.getAllDistrictsByStateId(stateId);
	   }

	   public List<Areas> getAllAreasByDistrictId(Districts districtId) {
	       return arearepo.getAllAreasByDistrictId(districtId);
	   }

	  

	   public PincodeDetailsResponse getAddresByPincode(String pincode)  {
	        Tuple t = arearepo.findById(pincode);
	        System.out.println("5346524"+""+t.get(0)+""+t.get(1)+""+t.get(2)+""+t.get(3)+""+t.get(4));
	        
	        
	        return new PincodeDetailsResponse(t.get(0).toString(),t.get(1).toString(),t.get(2).toString(),t.get(3).toString(),t.get(4).toString());
	   }

	
	
		

}
