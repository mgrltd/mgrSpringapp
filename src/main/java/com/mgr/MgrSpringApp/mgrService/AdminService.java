package com.mgr.MgrSpringApp.mgrService;

import java.util.List;

import com.mgr.MgrSpringApp.dto.Pagedata;
import com.mgr.MgrSpringApp.entity.Users;
import com.mgr.MgrSpringApp.response.ApiResponse;
import com.mgr.MgrSpringApp.response.UserResponse;

public interface AdminService {
    List<UserResponse> getAllUsers(Pagedata pagedata);

    ApiResponse deletUserById(Long id);
    
}
