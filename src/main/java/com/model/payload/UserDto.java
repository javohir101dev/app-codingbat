package com.model.payload;

import com.model.enums.Badge;
import lombok.Data;


@Data
public class UserDto {


    private String email;

    private String password;

    private  Badge badge;


}
