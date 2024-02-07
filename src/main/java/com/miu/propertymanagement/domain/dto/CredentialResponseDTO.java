package com.miu.propertymanagement.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CredentialResponseDTO {
    private Integer id;

    private String email;

    private String password;
}
