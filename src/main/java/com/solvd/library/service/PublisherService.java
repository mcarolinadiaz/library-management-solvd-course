package com.solvd.library.service;
import com.solvd.library.domain.Publisher;

import java.util.List;

public interface PublisherService {
    Publisher getPublisherById(Long id);
    List<Publisher> getAllPublishers();
    Publisher createPublisher(Publisher publisher);
    Publisher updatePublisher(Publisher publisher);
    void deletePublisher(Long id);
}