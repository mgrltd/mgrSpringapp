package com.mgr.MgrSpringApp.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemsResponse {
    private Long id;
private Long photoId;
private String itemCode;
private String itemName;
private Long categoryId;
}
