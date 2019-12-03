package com.example.crudForJob.repository;

import com.example.crudForJob.model.Entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepo extends JpaRepository<Publisher, Long> {
    Publisher findByPublisherName(String name);
}
