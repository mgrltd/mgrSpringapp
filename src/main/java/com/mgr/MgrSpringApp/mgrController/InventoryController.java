package com.mgr.MgrSpringApp.mgrController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mgr.MgrSpringApp.dto.Pagedata;
import com.mgr.MgrSpringApp.entity.Items;
import com.mgr.MgrSpringApp.mgrService.InventoryService;
import com.mgr.MgrSpringApp.response.ApiResponse;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
public class InventoryController {
    @Autowired
   private  InventoryService inventoryService;


    @PostMapping("/getallItems")
    public ApiResponse getallItems(@RequestBody Pagedata pagedata)
    {
        ApiResponse apiResponse=new ApiResponse();

      List<Items> allItems=inventoryService.getallItems(pagedata);
      apiResponse.setResponseCode(200);
      apiResponse.setResponseMessage("all items");
      apiResponse.setResponseBody(allItems);


      return apiResponse;
    }
}
