package com.miu.propertymanagement.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User from;

    @ManyToOne(fetch = FetchType.LAZY)
    private User to;

    private String content;

    private LocalDateTime dateTime;
}
