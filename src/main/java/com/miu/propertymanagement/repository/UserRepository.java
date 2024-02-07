package com.miu.propertymanagement.repository;

import com.miu.propertymanagement.domain.User;
import com.miu.propertymanagement.domain.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u join fetch u.favoriteList join fetch u.credential join fetch u.propertyList join fetch u.offersFrom join fetch u.offersTo where u.id = :userId")
    List<User> findUserByIdRewritten(@Param("userId") Integer userId);
    Optional<User> findUserByCredential_Email(String credential_email);

    List<User> findAllByUserTypeAndActiveIsFalse(UserType userType);
}
