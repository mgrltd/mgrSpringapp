package com.mgr.MgrSpringApp.mgrService;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mgr.MgrSpringApp.entity.Category;
import com.mgr.MgrSpringApp.entity.Photo;
import com.mgr.MgrSpringApp.entity.UOM;
import com.mgr.MgrSpringApp.mgrRepository.ProductsRepository;
import com.mgr.MgrSpringApp.mgrRepository.PhotoRepository;
import com.mgr.MgrSpringApp.mgrRepository.UOMRepository;
import com.mgr.MgrSpringApp.mgrRepository.CategoryRepository;

import com.mgr.MgrSpringApp.response.ItemsResponse;
import com.mgr.MgrSpringApp.response.ProductsResponse;
import com.mgr.MgrSpringApp.response.ApiResponse;


@Service
public class HelloServiceImp implements HelloService
{


    @Autowired
     private ProductsRepository productsRepository;
    
    @Autowired
 	private PhotoRepository photoRepository;
    
    @Autowired
    private UOMRepository uOMRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Override
	public ApiResponse getAllCategorys() {
    List<Category> allcategorys=categoryRepository.findAll();
    
		return new ApiResponse(200,"All Categorys",allcategorys);
	}
    
     @Override
     public ApiResponse allProducts(Long categoryId) {

    	 List<ProductsResponse> allProducts=null;
    	 if(categoryId!=0)
    	 {
    		 allProducts=productsRepository.FindAllProductsByCategoryId(categoryId);

    	 }
    	 else
    	 {         
    		 allProducts=productsRepository.FindAllProducts();  

    	 }
     
         return new ApiResponse(200,"all products",allProducts);
     }
     
     
     
     @Override
 	public ResponseEntity<byte[]> getPhoto(Long id) {
 	
 		Photo photo=photoRepository.findById(id).get();
 		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(photo.getPhotoData());
 	}



	@Override
	public ApiResponse getAllUomsByCategoryId(Long categoryId) {
		
	List<UOM> allUoms=uOMRepository.findAllByCategory(new Category().builder().id(categoryId).build());
		
				return new ApiResponse(200,"allUOMs",allUoms);
	}



	
    

}
