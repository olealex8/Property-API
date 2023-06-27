package com.dev.craniumproperty.dto.response.User;

import java.util.Date;

import jakarta.persistence.Column;

// import java.sql.Date;

import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    private String email;

    private Date createdAt;
    
    private Integer createdBy;

    private Date updatedAt;

    private Integer updatedBy;

    private Boolean deleted;

    private Date deletedAt;

    private Integer deletedBy;
}
