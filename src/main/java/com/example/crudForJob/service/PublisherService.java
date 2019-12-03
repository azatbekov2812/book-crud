package com.example.crudForJob.service;

import com.example.crudForJob.model.Dto.PublisherDto;
import com.example.crudForJob.model.Entity.Publisher;

import java.util.List;

public interface PublisherService {
    Publisher savePublisher(String publisherName) throws Exception;
    List<PublisherDto> getAll();
    Publisher findByName(String name);
}
