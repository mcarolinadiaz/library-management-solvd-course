package com.solvd.library.service.impl;


import com.solvd.library.domain.Book;
import com.solvd.library.domain.Comment;
import com.solvd.library.domain.Inventory;
import com.solvd.library.domain.Loan;
import com.solvd.library.persistence.BookRepository;
import com.solvd.library.persistence.impl.BookJDBCImpl;
import com.solvd.library.persistence.impl.BookMybatisImpl;
import com.solvd.library.service.BookService;
import com.solvd.library.service.CommentService;
import com.solvd.library.service.InventoryService;
import com.solvd.library.service.LoanService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final CommentService commentService = new CommentServiceImpl();
    private final LoanService loanService = new LoanServiceImpl();
    private final InventoryService inventoryService = new InventoryServiceImpl();

    public BookServiceImpl() {
        this.bookRepository = new BookJDBCImpl();
        //this.bookRepository = new BookMybatisImpl();
    }

    /**
     * Retrieve a book by its ID from the repository.
     *
     * @param id
     * @return
     */
    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    /** Retrieve all books from the repository.
     * @return
     */
    @Override
    public Collection<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /** Create the book in the repository and retrieve it.
     * Create and set comments, loans, and inventories associated with the book.
     * Implements Facade design pattern.
     * @param book
     * @return
     */
    @Override
    public Book createBook(Book book) {
        book.setId(null);
        bookRepository.create(book);

        if (book.getComments() != null) {
            List<Comment> comments = book.getComments().stream()
                    .map(comment -> commentService.createComment(comment))
                    .collect(Collectors.toList());
            book.setComments(comments);
        }
        if (book.getLoans() != null) {
            List<Loan> loans = book.getLoans().stream()
                    .map(loan -> loanService.createLoan(loan))
                    .collect(Collectors.toList());
            book.setLoans(loans);
        }
        if (book.getInventories() != null) {
            List<Inventory> inventories = book.getInventories().stream()
                    .map(inventory -> inventoryService.createInventory(inventory))
                    .collect(Collectors.toList());
            book.setInventories(inventories);
        }
        return book;
    }

    /** Update the book in the repository and retrieve it.
     * @param book
     * @return
     */
    @Override
    public Book updateBook(Book book) {
        bookRepository.update(book);
        return book;
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.delete(id);
    }
}