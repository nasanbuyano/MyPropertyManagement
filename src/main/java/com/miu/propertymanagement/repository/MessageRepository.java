package com.miu.propertymanagement.repository;

import com.miu.propertymanagement.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query("SELECT m FROM Message m WHERE (m.from.id = :fromId AND m.to.id = :toId) OR (m.to.id = :fromId AND m.from.id = :toId) ORDER BY m.dateTime ASC")
    List<Message> findAllByFrom_IdAndTo_IdOrderByDateTimeAsc(@Param("fromId") Integer fromId,
                                                             @Param("toId") Integer toId);

}
