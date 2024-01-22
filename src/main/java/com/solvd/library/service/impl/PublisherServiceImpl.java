package com.solvd.library.service.impl;


import com.solvd.library.domain.Book;
import com.solvd.library.domain.Publisher;
import com.solvd.library.persistence.PublisherRepository;
import com.solvd.library.persistence.impl.PublisherJDBCImpl;
import com.solvd.library.persistence.impl.PublisherMybatisImpl;
import com.solvd.library.service.BookService;
import com.solvd.library.service.PublisherService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;
    private final BookService bookService = new BookServiceImpl();

    public PublisherServiceImpl() {
        this.publisherRepository = new PublisherJDBCImpl();
        //this.publisherRepository = new PublisherMybatisImpl();
    }

    @Override
    public Optional<Publisher> getPublisherById(Long id) {
        return publisherRepository.findById(id);
    }

    @Override
    public Collection<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher createPublisher(Publisher publisher) {
        publisher.setId(null);
        publisherRepository.create(publisher);
        if (publisher.getBooks() != null) {
            List<Book> books = publisher.getBooks().stream()
                    .map(book -> bookService.createBook(book))
                    .collect(Collectors.toList());
            publisher.setBooks(books);
        }
        return publisher;
    }

    @Override
    public Publisher updatePublisher(Publisher publisher) {
        publisherRepository.update(publisher);
        return publisher;
    }

    @Override
    public void deletePublisher(Long id) {
        publisherRepository.delete(id);
    }
}