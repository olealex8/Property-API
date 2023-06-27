package com.dev.craniumproperty.dto.response;

import org.hibernate.mapping.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {
    private Integer code;
    private String info;
    private Map data;
}
