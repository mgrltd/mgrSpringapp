package com.mgr.MgrSpringApp.mgrService;

import java.util.List;

import com.mgr.MgrSpringApp.dto.AreasRequest;
import com.mgr.MgrSpringApp.entity.Areas;
import com.mgr.MgrSpringApp.entity.Country;
import com.mgr.MgrSpringApp.entity.Districts;
import com.mgr.MgrSpringApp.entity.States;
import com.mgr.MgrSpringApp.entity.Users;
import com.mgr.MgrSpringApp.response.AddressDTO;
import com.mgr.MgrSpringApp.dto.Pagedata;
import com.mgr.MgrSpringApp.entity.Users;
import com.mgr.MgrSpringApp.response.ApiResponse;
import com.mgr.MgrSpringApp.response.UserResponse;

public interface AdminService {
    List<UserResponse> getAllUsers(Pagedata pagedata);

    List<Users> getAllUsers();

	List<Country> getAllCountry();

	List<States> getAllStates();

	List<Districts> getAllDistricts();

	List<Areas> getAllAreas();

	List<AddressDTO> getAllAddress();


	AreasRequest getPincode(String pincode);

//	List<AddressDTO> getAllAddress();
    ApiResponse deletUserById(Long id);
    
}
