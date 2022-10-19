package com.mgr.MgrSpringApp.mgrService;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mgr.MgrSpringApp.dto.AreasRequest;
import com.mgr.MgrSpringApp.entity.Areas;
import com.mgr.MgrSpringApp.entity.Country;
import com.mgr.MgrSpringApp.entity.Districts;
import com.mgr.MgrSpringApp.entity.States;
import com.mgr.MgrSpringApp.entity.Users;
import com.mgr.MgrSpringApp.mgrRepository.AreasRepository;
import com.mgr.MgrSpringApp.mgrRepository.CountryRepository;
import com.mgr.MgrSpringApp.mgrRepository.DistrictsRepository;
import com.mgr.MgrSpringApp.mgrRepository.StatesRepository;
import com.mgr.MgrSpringApp.mgrRepository.StoreRepository;
import com.mgr.MgrSpringApp.mgrRepository.UserRepository;
import com.mgr.MgrSpringApp.response.AddressDTO;
import org.springframework.data.domain.PageRequest;
import com.mgr.MgrSpringApp.dto.Pagedata;
import com.mgr.MgrSpringApp.mgrRepository.UserRepository;
import com.mgr.MgrSpringApp.response.ApiResponse;
import com.mgr.MgrSpringApp.response.UserResponse;
import org.springframework.data.domain.Sort;


@Service
public class AdminServiceImp implements AdminService
{

    @Autowired
    private UserRepository userRepository;
    
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
    
    @Override
	public List<Users> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public List<Country> getAllCountry() {
		return countryrepo.findAll();
	}

	@Override
	public List<States> getAllStates() {
		return staterepo.findAll();
	}

	@Override
	public List<Districts> getAllDistricts() {
		return districtrepo.findAll();
	}

	@Override
	public List<Areas> getAllAreas() {
		return arearepo.findAll();
	}

	@Override
	public List<AddressDTO> getAllAddress() {
		// TODO Auto-generated method stub
		
		return storerepo.findAllAddress();
	}

	@Override
	public AreasRequest getPincode(String pincode ) {
		 Tuple t = arearepo.findById(pincode);
		 System.out.println("5346524"+""+t.get(0)+""+t.get(1)+""+t.get(2)+""+t.get(3)+""+t.get(4));
		 
		 
		 return new AreasRequest(t.get(0).toString(),t.get(1).toString(),t.get(2).toString(),t.get(3).toString(),t.get(4).toString());
	}
	
	

//	@Override
//	public List<AddressDTO> getAllAddress() {
//		
//		System.out.println("===========");
//	List<AddressDTO> a=	 storerepo.findAllAddress();
//	
//		System.out.println("=====a======");
//		
//		 return null;
//		 
//	}
	public List<UserResponse> getAllUsers(Pagedata pagedata) {
        if(pagedata.getPageNo()<1)
        {
            pagedata.setPageNo(1);
        }
        if(pagedata.getFilterroles().isEmpty())
        {
            List<String> allroles=new ArrayList<>();
            allroles.add("USER");
            allroles.add("ADMIN");
            allroles.add("COSTMAR");
            allroles.add("EMP");
            pagedata.setFilterroles(allroles);
        }
        System.out.println("page No--"+pagedata.getPageNo()+" pageSize--"+pagedata.getPageSize()+" soryByfild--"+pagedata.getSortbyfild()+" search word--"+pagedata.getSearchword()+"filterRoles-"+pagedata.getFilterroles());
//return userRepository.getAllUsersWithPagenashan(PageRequest.of(pagedata.getPageNo(),pagedata.getPageSize()).withSort(Sort.by(pagedata.getSortbyfild())),pagedata.getSearchword(),pagedata.getFilterroles());
		return userRepository.getAllUsersWithPagenashan(PageRequest.of(pagedata.getPageNo()-1,pagedata.getPageSize()).withSort(Sort.by(pagedata.getSortbyfild())),pagedata.getSearchword(),pagedata.getFilterroles());
	}		

    @Override
    public ApiResponse deletUserById(Long id)
     {
            try {
               userRepository.deleteById(id);
                return new ApiResponse<>(200,"User"+id+" Delete sucessfully",id);
                
            } catch (Exception e) {
                return new ApiResponse<>(404,"User "+id+" notFound",id);
            }
         
    }


    
}
