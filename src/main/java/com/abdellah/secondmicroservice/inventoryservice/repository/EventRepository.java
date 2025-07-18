package com.abdellah.secondmicroservice.inventoryservice.repository;


import com.abdellah.secondmicroservice.inventoryservice.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
