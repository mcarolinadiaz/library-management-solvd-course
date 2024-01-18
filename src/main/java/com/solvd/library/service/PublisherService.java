package com.solvd.library.service;
import com.solvd.library.domain.Publisher;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PublisherService {
    Optional<Publisher> getPublisherById(Long id);
    Collection<Publisher> getAllPublishers();
    Publisher createPublisher(Publisher publisher);
    Publisher updatePublisher(Publisher publisher);
    void deletePublisher(Long id);
}