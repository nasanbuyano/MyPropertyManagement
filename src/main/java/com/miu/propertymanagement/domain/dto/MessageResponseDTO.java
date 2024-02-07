package com.miu.propertymanagement.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageResponseDTO {
    private Integer id;

    private Integer from;

    private Integer to;

    private String content;

    private LocalDateTime dateTime;
}
