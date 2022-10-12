package com.mgr.MgrSpringApp.entity;

import javax.persistence.Id;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UOM {
    @Id
    private Long id;
    private String UOM;
    
}
