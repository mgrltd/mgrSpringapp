package com.mgr.MgrSpringApp.mgrService;

import java.util.List;

import com.mgr.MgrSpringApp.dto.Pagedata;
import com.mgr.MgrSpringApp.entity.Areas;
import com.mgr.MgrSpringApp.entity.Country;
import com.mgr.MgrSpringApp.entity.Districts;
import com.mgr.MgrSpringApp.entity.States;
import com.mgr.MgrSpringApp.entity.Users;
import com.mgr.MgrSpringApp.response.ApiResponse;
import com.mgr.MgrSpringApp.response.PageResponse;
import com.mgr.MgrSpringApp.response.PincodeDetailsResponse;
import com.mgr.MgrSpringApp.response.UserResponse;

public interface AdminService {
	

    ApiResponse deletUserById(Long id);

	PageResponse getAllUsers(Pagedata pagedata);

	PageResponse getallproducts(Pagedata pagedata);

       
}
