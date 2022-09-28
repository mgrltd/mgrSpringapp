package com.mgr.MgrSpringApp.mgrService;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.authentication.AuthenticationManager;
import com.mgr.MgrSpringApp.config.EmailConfig;
import com.mgr.MgrSpringApp.config.JwtUtil;
import com.mgr.MgrSpringApp.dto.LoginRequest;
import com.mgr.MgrSpringApp.entity.Photo;
import com.mgr.MgrSpringApp.entity.Roles;
import com.mgr.MgrSpringApp.entity.Users;
import com.mgr.MgrSpringApp.mgrRepository.PhotoRepository;
import com.mgr.MgrSpringApp.mgrRepository.RolesRepository;
import com.mgr.MgrSpringApp.mgrRepository.UserRepository;
import com.mgr.MgrSpringApp.response.ApiResponse;
@Service
public class ApplicationService {

	private String loginotp="";
	private String Name="";

    @Autowired
	private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;

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
	private EmailConfig emailConfig;
 
	public String getEncodePassword(String password)
	{
	 return passwordEncoder.encode(password);
	}
 
    public ApiResponse createJwtToken(LoginRequest loginRequest) throws Exception
    {
		ApiResponse response=new ApiResponse();

        String type = loginRequest.getType();
                Name = loginRequest.getUserName();
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
			response.setResponseCode(404);
			response.setResponseMessage("bad credentials User Notfound");
			return response;
		 }
		 if(!passwordEncoder.matches(Password,users.getPassword()))
		 {
		    response.setResponseCode(401);
			response.setResponseMessage("Unauthorized password incorrect "+Name);
			return response;
		 }		
		 loginotp=new DecimalFormat("000000").format(new Random().nextInt(999999));
		String s= emailConfig.sendEmailConfig(users.getEmailId(), "*MGR Login Verification OTP *", "MGR Verification OTP:"+loginotp);
		 System.out.println("loginotp-->"+loginotp);
		 response.setResponseCode(200);
		 response.setResponseMessage("Ok OTP send successfully to mail "+users.getEmailId());
		 return response;

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
    

	public ApiResponse loginOtp(String otp) 
	{
		//System.out.println("loginotp-"+loginotp+" Name-"+Name);
		if(loginotp.equals(otp))
		{
			final UserDetails userDetails = userService.loadUserByUsername(Name);
		 String newGeneratedToken = jwtUtil.generateToken(userDetails);
		return new ApiResponse(200,newGeneratedToken);
		}
		else
		{
			return new ApiResponse(401,"otp not corret");
		}
	}

	public ApiResponse userRegister(Users users) 
      {

     ApiResponse response=new ApiResponse();
    try {
		System.out.println("============");
		Long id=(long) 1;
		Roles role=rplesRepository.findById(id).get();
		Set<Roles> roles = new HashSet<>();
		roles.add(role);
		users.setRole(roles);
		users.setPassword(getEncodePassword(users.getPassword()));
     Users user=userRepository.save(users);

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

	public Photo photoUpload(MultipartFile photo) throws IOException {
		Photo photos=new Photo();
		photos.setPhotoData(photo.getBytes());
		return photoRepository.save(photos);
	}

}
