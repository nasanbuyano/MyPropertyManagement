package com.miu.propertymanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Credential {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String email;

    private String password;

    @JsonManagedReference("credential")
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "credential")
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}
