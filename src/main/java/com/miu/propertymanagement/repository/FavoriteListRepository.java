package com.miu.propertymanagement.repository;

import com.miu.propertymanagement.domain.FavoriteList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteListRepository extends JpaRepository<FavoriteList, Integer> {

    FavoriteList findByUser_Id(Integer user_id);

}
