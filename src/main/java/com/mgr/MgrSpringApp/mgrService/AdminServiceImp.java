package com.mgr.MgrSpringApp.mgrService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mgr.MgrSpringApp.entity.Users;
import com.mgr.MgrSpringApp.mgrRepository.UserRepository;

@Service
public class AdminServiceImp implements AdminService
{

    @Autowired
    private UserRepository userRepository;
    
    @Override
	public List<Users> getAllUsers() {
		return userRepository.findAll();
	}
    
}
