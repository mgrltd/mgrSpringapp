package com.mgr.MgrSpringApp.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pagedata {
    
    private int pageNo;
    private int pageSize;
    private String sortbyfild;
    private String searchword;
    private List<String> filterroles;
    
}
