package com.dev.craniumproperty.entity;

import java.sql.Date;

import lombok.Data;

@Data
public class AgentEntity {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String description;
    private String experience;
    private Integer agencyId;
    private String bannerName;
    private String bannerLink;
    private String profileName;
    private String profileLink;
    private String email;
    private String phoneNumber;
    private Date createdAt;
    private Integer createdBy;
    private Date updatedAt;
    private Integer updatedBy;
    private Boolean deleted;
    private Date deletedAt;
    private Integer deletedBy;
   
}
