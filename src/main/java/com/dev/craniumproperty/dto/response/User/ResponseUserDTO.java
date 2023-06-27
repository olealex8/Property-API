package com.dev.craniumproperty.dto.response.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserDTO<T> {
    private Integer code;
    private String info;
    private T data;
}
