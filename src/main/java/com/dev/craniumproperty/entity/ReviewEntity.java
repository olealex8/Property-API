package com.dev.craniumproperty.entity;

import java.sql.Date;

import lombok.Data;

@Data
public class ReviewEntity {
    private Integer id;
    private Integer agentId;   
    private Integer propertyId;   
    private Integer userId;   
    private Integer agencyId;   
    private String category;  
    private String description;  
    private Integer rating;  
    private Integer verifyAdminId;
    private Date createdAt;
    private Integer createdBy;
    private Date updatedAt;
    private Integer updatedBy;
    private Boolean deleted;
    private Date deletedAt;
    private Integer deletedBy;

}
