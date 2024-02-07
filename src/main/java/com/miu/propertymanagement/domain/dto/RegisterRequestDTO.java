package com.miu.propertymanagement.domain.dto;

import com.miu.propertymanagement.domain.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterRequestDTO {

    private String firstName;

    private String lastName;

    private UserType userType;

    private Boolean active;

//    private Credential credential;
    private String email;

    private String password;

}

