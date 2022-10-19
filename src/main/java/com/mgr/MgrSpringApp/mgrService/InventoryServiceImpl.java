package com.mgr.MgrSpringApp.mgrService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mgr.MgrSpringApp.dto.Pagedata;
import com.mgr.MgrSpringApp.entity.Items;
import com.mgr.MgrSpringApp.mgrRepository.ItemsRepository;
import com.mgr.MgrSpringApp.response.ApiResponse;

@Service
public class InventoryServiceImpl implements InventoryService
{

    @Autowired
   private ItemsRepository itemsRepository;
    @Override
    public List<Items> getallItems(Pagedata pagedata) 
    {
        return itemsRepository.findAll();
    }
    
}
