package com.miu.propertymanagement.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.miu.propertymanagement.domain.enums.OfferStatus;
import com.miu.propertymanagement.domain.enums.OfferType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double price;

    private LocalDateTime localDateTime;

    @Enumerated(EnumType.STRING)
    private OfferStatus offerStatusFrom;

    @Enumerated(EnumType.STRING)
    private OfferStatus offerStatusTo;

    @Enumerated(EnumType.STRING)
    private OfferType offerType;

//    @ManyToOne
//    @JoinColumn(name = "offer_from", referencedColumnName = "id")
//    private User offerFrom;
//
//    @ManyToOne
//    @JoinColumn(name = "offer_to", referencedColumnName = "id")
//    private User offerTo;
//
//    @ManyToOne
//    @JoinColumn(name = "property_id", referencedColumnName = "id")
//    private Property property;

    @ManyToOne
    @JoinColumn(name = "offer_from", referencedColumnName = "id")
    @JsonManagedReference("offer-from")
    private User offerFrom;

    @ManyToOne
    @JoinColumn(name = "offer_to", referencedColumnName = "id")
    @JsonManagedReference("offer-to")
    private User offerTo;

    @ManyToOne
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    @JsonManagedReference("offer-property")
    private Property property;


}
