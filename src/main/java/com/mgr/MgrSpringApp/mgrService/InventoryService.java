package com.mgr.MgrSpringApp.mgrService;

import java.util.List;

import com.mgr.MgrSpringApp.dto.Pagedata;
import com.mgr.MgrSpringApp.entity.Items;
import com.mgr.MgrSpringApp.response.ApiResponse;

public interface InventoryService {

    List<Items> getallItems(Pagedata pagedata);
    
}
