package com.mgr.MgrSpringApp.mgrService;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mgr.MgrSpringApp.response.ApiResponse;
import com.mgr.MgrSpringApp.response.ProductsResponse;

public interface HelloService 
{

	ApiResponse allProducts(Long categoryId);

	ResponseEntity<byte[]> getPhoto(Long id);

	ApiResponse getAllUomsByCategoryId(Long categoryId);

	ApiResponse getAllCategorys();

}
