package com.mgr.MgrSpringApp.mgrService;

import java.util.HashSet;
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

import com.mgr.MgrSpringApp.entity.Photo;
import com.mgr.MgrSpringApp.entity.Users;
import com.mgr.MgrSpringApp.mgrRepository.PhotoRepository;
import com.mgr.MgrSpringApp.mgrRepository.UserRepository;

@Service
public class UserServiceImp implements UserService
{
    @Autowired
   private UserRepository userRepository;

   @Autowired
   private PasswordEncoder passwordEncoder;
   
   @Autowired
	private PhotoRepository photoRepository;

   public String getEncodePassword(String password)
   {
	return passwordEncoder.encode(password);
   }

@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users user = userRepository.findByEmailId(email);
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
        		 	authorities.add(new SimpleGrantedAuthority("ROLE_"+"User"));
		return authorities;
	}


	@Override
	public Users userDataById(long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public ResponseEntity<byte[]> getPhoto(Long id) {
	
		Photo photo=photoRepository.findById(id).get();
		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(photo.getPhotoData());
	}


	

}
