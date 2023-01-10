package com.mgr.MgrSpringApp.entity;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category 
{

    @Id
    private Long id;
    private String categoryName;
    private Long photoId;
    
}
