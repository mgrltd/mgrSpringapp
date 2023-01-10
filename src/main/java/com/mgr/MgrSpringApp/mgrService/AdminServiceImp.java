package com.mgr.MgrSpringApp.mgrService;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import com.mgr.MgrSpringApp.dto.Pagedata;
import com.mgr.MgrSpringApp.entity.Areas;
import com.mgr.MgrSpringApp.entity.Country;
import com.mgr.MgrSpringApp.entity.Districts;
import com.mgr.MgrSpringApp.entity.States;
import com.mgr.MgrSpringApp.entity.Users;
import com.mgr.MgrSpringApp.mgrRepository.AreasRepository;
import com.mgr.MgrSpringApp.mgrRepository.CountryRepository;
import com.mgr.MgrSpringApp.mgrRepository.DistrictsRepository;
import com.mgr.MgrSpringApp.mgrRepository.ProductsRepository;
import com.mgr.MgrSpringApp.mgrRepository.StatesRepository;
import com.mgr.MgrSpringApp.mgrRepository.StoreRepository;
import com.mgr.MgrSpringApp.mgrRepository.UserRepository;
import com.mgr.MgrSpringApp.response.ApiResponse;
import com.mgr.MgrSpringApp.response.PageResponse;
import com.mgr.MgrSpringApp.response.PincodeDetailsResponse;
import com.mgr.MgrSpringApp.response.ProductsResponse;
import com.mgr.MgrSpringApp.response.UserResponse;
import org.springframework.data.domain.Sort;


@Service
public class AdminServiceImp implements AdminService
{

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ProductsRepository productsRepository;
    
    @Override
	public PageResponse getAllUsers(Pagedata pagedata) {
    	
    	
    	 Page<Users> getAllUsers=null;
    	
        if(pagedata.getPageNo()<1)
        {
            pagedata.setPageNo(1);
        }
        if(pagedata.getFilterBy().size()!=0)
        {

        System.out.println("page No--"+pagedata.getPageNo()+" pageSize--"+pagedata.getPageSize()+" soryByfild--"+pagedata.getSortbyfild()+" search word--"+pagedata.getSearchword()+"filterRoles-"+pagedata.getFilterBy());
		 
         getAllUsers=userRepository.getAllUsersWithPagenashanAndFilter(PageRequest.of(pagedata.getPageNo()-1,pagedata.getPageSize()).withSort(Sort.by(pagedata.getSortbyfild())),pagedata.getSearchword(),pagedata.getFilterBy());
        }
        else
        {
        	getAllUsers=userRepository.getAllUsersWithPagenashan(PageRequest.of(pagedata.getPageNo()-1,pagedata.getPageSize()).withSort(Sort.by(pagedata.getSortbyfild())),pagedata.getSearchword());
        }
				
				return new PageResponse(200,"UserDetails",getAllUsers.getContent(),getAllUsers.getSize(),getAllUsers.getNumber(),getAllUsers.getTotalPages());
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

	@Override
	public PageResponse getallproducts(Pagedata pagedata) {
		
		Page<ProductsResponse> allproducts=productsRepository.getallproductsWithPagenashan(PageRequest.of(pagedata.getPageNo()-1,pagedata.getPageSize()).withSort(Sort.by(pagedata.getSortbyfild())),pagedata.getSearchword());
		
		return new PageResponse(200,"UserDetails",allproducts.getContent(),allproducts.getSize(),allproducts.getNumber(),allproducts.getTotalPages());
	}

   
    
}
