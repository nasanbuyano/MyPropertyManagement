package com.miu.propertymanagement.repository;

import com.miu.propertymanagement.domain.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {

    List<Offer> findAllByOfferFrom_Id(Integer offerFrom_id);

    List<Offer> findAllByOfferTo_Id(Integer offerTo_id);

    @Query("SELECT o FROM Offer o join fetch o.property join fetch o.offerFrom join fetch o.offerTo WHERE o.offerFrom.id = :offerFromId AND " +
            "((o.offerStatusFrom = 'Rejected' OR o.offerStatusTo = 'Rejected') OR " +
            "(o.offerStatusFrom = 'Accepted' AND o.offerStatusTo = 'Accepted'))")
    List<Offer> findHistoryOffersByCustomer(@Param("offerFromId") Integer offerFromId);

    @Query("SELECT o FROM Offer o join fetch o.property join fetch o.offerFrom join fetch o.offerTo WHERE o.offerFrom.id = :offerFromId AND " +
            "((o.offerStatusFrom != 'Rejected' AND o.offerStatusTo != 'Rejected') AND " +
            "(o.offerStatusFrom != 'Accepted' OR o.offerStatusTo != 'Accepted'))")
    List<Offer> findLiveOffersByCustomer(@Param("offerFromId") Integer offerFromId);

    @Query("SELECT o FROM Offer o join fetch o.property join fetch o.offerFrom join fetch o.offerTo WHERE o.offerTo.id = :offerToId AND " +
            "((o.offerStatusFrom = 'Rejected' OR o.offerStatusTo = 'Rejected') OR " +
            "(o.offerStatusFrom = 'Accepted' AND o.offerStatusTo = 'Accepted'))")
    List<Offer> findHistoryOffersByOwner(@Param("offerToId")Integer offerToId);

    @Query("SELECT o FROM Offer o join fetch o.property join fetch o.offerFrom join fetch o.offerTo WHERE o.offerTo.id = :offerToId AND " +
            "((o.offerStatusFrom != 'Rejected' AND o.offerStatusTo != 'Rejected') AND " +
            "(o.offerStatusFrom != 'Accepted' OR o.offerStatusTo != 'Accepted'))")
    List<Offer> findLiveOffersByOwner(@Param("offerToId") Integer offerToId);
}