package com.mgr.MgrSpringApp.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {
	
	private int responseCode;
    private String  responseMessage;
    private T responseBody;
    private int pageSize;
    private int currentPage;
    private int totalPages;
    
	

}
