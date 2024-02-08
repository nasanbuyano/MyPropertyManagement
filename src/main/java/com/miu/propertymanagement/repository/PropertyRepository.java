package com.miu.propertymanagement.repository;

import com.miu.propertymanagement.domain.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {
    List<Property> findAllByUser_Id(Integer user_id);

    List<Property> findAllByUser_Active(Boolean user_active);
}
