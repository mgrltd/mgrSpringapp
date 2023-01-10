package com.mgr.MgrSpringApp.dto;

import javax.persistence.ManyToOne;

import org.springframework.web.multipart.MultipartFile;

import com.mgr.MgrSpringApp.entity.Areas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	
	    private UserRequest userRequest;
	    private AddresRequest addresRequest;
			private Long photoId;
		
}
