package com.mgr.MgrSpringApp.mgrService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    
    @Override
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
