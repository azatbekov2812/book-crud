package com.example.crudForJob.service.impl;

import com.example.crudForJob.model.Dto.PublisherDto;
import com.example.crudForJob.model.Entity.Publisher;
import com.example.crudForJob.repository.PublisherRepo;
import com.example.crudForJob.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {
    @Autowired
    private PublisherRepo repo;

    @Override
    public Publisher savePublisher(String publisherName) throws Exception {
        Publisher publisher = new Publisher();
        Publisher entity = repo.findByPublisherName(publisherName);
        if (entity != null){
            throw new Exception("издатель с таким именем уже существует");
        }
        publisher.setPublisherName(publisherName);
        return repo.save(publisher);
    }

    @Override
    public List<PublisherDto> getAll() {
        List<Publisher> publisherList = repo.findAll();
        List<PublisherDto> dtoList = new ArrayList<>();
        for (Publisher publisher : publisherList) {
            PublisherDto publisherDto = new PublisherDto();
            publisherDto.setId(publisher.getId());
            publisherDto.setPublisherName(publisher.getPublisherName());
            dtoList.add(publisherDto);
        }
        return dtoList;
    }

    @Override
    public Publisher findByName(String name) {
        return repo.findByPublisherName(name);
    }
}
