package com.solvd.library;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.library.domain.*;
import com.solvd.library.domain.Comment;
import com.solvd.library.persistence.impl.AbstractRepositoryFactory;
import com.solvd.library.persistence.impl.RepositoryFactory;
import com.solvd.library.service.*;
import com.solvd.library.service.impl.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        // Person test

        Person person = new Person();
        person.setFirstName("Bill");
        person.setLastName("Gates");

        PersonService personService = new PersonServiceImpl();
        LOGGER.info(personService.createPerson(person).getFirstName());
        Collection<Person> persons = personService.getAllPersons();
        for (Person person3 : persons) {
            LOGGER.info(person3.getFirstName());
            LOGGER.info(person3.getLastName());
            person3.setLastName("Smith");
            LOGGER.info(personService.updatePerson(person3));
            LOGGER.info(personService.getPersonById(person3.getId()));
            person.setId(person3.getId());
            //personService.deletePerson(person3.getId());
            if (person.getId() == null && person3.getId() != null) {
                person.setId(person3.getId());
            }
        }
/*
        // Author test
        Author author = new Author();
        author.setName("Brandon Sanderson");
        author.setNationality("United States");
        AuthorService authorService = new AuthorServiceImpl();
        LOGGER.info(authorService.createAuthor(author).getName());
        Collection<Author> authors = authorService.getAllAuthors();
        for (Author a : authors) {
            LOGGER.info(a.getId());
            LOGGER.info(authorService.getAuthorById(a.getId()));
            a.setName("Maria Elena Walsh");
            a.setNationality("Argentina");
            LOGGER.info(authorService.updateAuthor(a));
            authorService.deleteAuthor(a.getId());
        }
*/
        // Publisher test
        Publisher publisher = new Publisher();
        publisher.setName("Alfaguara");
        PublisherService publisherService = new PublisherServiceImpl();
        LOGGER.info(publisherService.createPublisher(publisher).getName());
        Collection<Publisher> publishers = publisherService.getAllPublishers();
        for (Publisher p : publishers) {
            LOGGER.info(p.getId());
            LOGGER.info(publisherService.getPublisherById(p.getId()));
            p.setName("Alpha");
            LOGGER.info(publisherService.updatePublisher(p));
            //publisherService.deletePublisher(p.getId());
            if (publisher.getId() == null && p.getId() != null) {
                publisher.setId(p.getId());
            }
        }

        // Category test
        Category category = new Category();
        category.setName("Fantasy");
        CategoryService categoryService = new CategoryServiceImpl();
        LOGGER.info(categoryService.createCategory(category).getName());
        Collection<Category> categories = categoryService.getAllCategorys();
        for (Category ca : categories) {
            LOGGER.info(ca.getId());
            LOGGER.info(categoryService.getCategoryById(ca.getId()));
            ca.setName("Terror");
            LOGGER.info(categoryService.updateCategory(ca));
            //categoryService.deleteCategory(ca.getId());
            if (category.getId() == null && ca.getId() != null) {
                category.setId(ca.getId());
            }
        }
/*
        // Branch test
        Branch branch = new Branch();
        branch.setLocation("United States");
        BranchService branchService = new BranchServiceImpl("Mybatis");
        LOGGER.info(branchService.createBranch(branch).getLocation());
        Collection<Branch> branches = branchService.getAllBranchs();
        for (Branch br : branches) {
            LOGGER.info(br.getId());
            LOGGER.info(branchService.getBranchById(br.getId()));
            br.setLocation("Chile");
            LOGGER.info(branchService.updateBranch(br));
            //branchService.deleteBranch(br.getId());
            if (branch.getId() == null && br.getId() != null) {
                branch.setId(br.getId());
            }
        }*/

        // Reservation test
        Reservation reservation = new Reservation();
        reservation.setReservationDate(new Date());
        ReservationService reservationService = new ReservationServiceImpl();
        LOGGER.info(reservationService.createReservation(reservation).getReservationDate());
        Collection<Reservation> reservations = reservationService.getAllReservations();
        for (Reservation r : reservations) {
            LOGGER.info(r.getId());
            LOGGER.info(reservationService.getReservationById(r.getId()));
            r.setReservationDate(new Date());
            LOGGER.info(reservationService.updateReservation(r));
            //reservationService.deleteReservation(r.getId());
            if (reservation.getId() == null && r.getId() != null) {
                reservation.setId(r.getId());
            }
        }

        // User test
        User user = new User();
        user.setPersonId(person.getId());
        user.setReservationId(reservation.getId());
        UserService userService = new UserServiceImpl();
        LOGGER.info(userService.createUser(user));

        Reservation r1 = new Reservation();
        r1.setReservationDate(new Date());
        r1.setId(reservationService.createReservation(r1).getId());

        Collection<User> users = userService.getAllUsers();
        for (User u : users) {
            LOGGER.info(u.getId());
            LOGGER.info(userService.getUserById(u.getId()));
            user.setReservationId(r1.getId());
            LOGGER.info(userService.updateUser(u));
            //userService.deleteUser(u.getId());
            if (user.getId() == null && u.getId() != null) {
                user.setId(u.getId());
            }
        }

/*
        // Employee test
        Employee employee = new Employee();
        employee.setPersonId(person.getId());
        employee.setBranchId(branch.getId());
        EmployeeService employeeService = new EmployeeServiceImpl();
        LOGGER.info(employeeService.createEmployee(employee));

        Branch br1 = new Branch();
        br1.setLocation("Uruguay");
        br1.setId(branchService.createBranch(br1).getId());

        Collection<Employee> employees = employeeService.getAllEmployees();
        for (Employee e : employees) {
            LOGGER.info(e.getId());
            LOGGER.info(employeeService.getEmployeeById(e.getId()));
            e.setBranchId(br1.getId());
            LOGGER.info(employeeService.updateEmployee(e));
            employeeService.deleteEmployee(e.getId());
        }*/

        // Book test
        Book book = new Book();
        book.setPublisherId(publisher.getId());
        book.setReservationId(reservation.getId());
        book.setCategoryId(category.getId());
        book.setName("Elantris");
        book.setYear(new Date());
        BookService bookService = new BookServiceImpl();
        LOGGER.info(bookService.createBook(book));
        Collection<Book> books = bookService.getAllBooks();
        for (Book bo : books) {
            LOGGER.info(bo.getId());
            LOGGER.info(bookService.getBookById(bo.getId()));
            bo.setName("Animal Farm");
            LOGGER.info(bookService.updateBook(bo));
            //bookService.deleteBook(bo.getId());
            if (book.getId() == null && bo.getId() != null) {
                book.setId(bo.getId());
            }
        }
/*
        // Inventory test
        Inventory inventory = new Inventory();
        inventory.setBookId(book.getId());
        inventory.setBranchId(branch.getId());
        inventory.setStockQuantity(10);
        InventoryService inventoryService = new InventoryServiceImpl();
        LOGGER.info(inventoryService.createInventory(inventory));
        Collection<Inventory> inventories = inventoryService.getAllInventories();
        for (Inventory in : inventories) {
            LOGGER.info(in.getId());
            LOGGER.info(inventoryService.getInventoryById(in.getId()));
            in.setStockQuantity(4);
            LOGGER.info(inventoryService.updateInventory(in));
            inventoryService.deleteInventory(in.getId());
        }*/
/*
        // Comment test
        Comment comment = new Comment();
        comment.setBookId(book.getId());
        comment.setUserId(user.getId());
        comment.setComment("Great!");
        CommentService commentService = new CommentServiceImpl();
        LOGGER.info(commentService.createComment(comment));
        Collection<Comment> comments = commentService.getAllComments();
        for (Comment co : comments) {
            LOGGER.info(co.getId());
            LOGGER.info(commentService.getCommentById(co.getId()));
            comment.setComment("Good!");
            LOGGER.info(commentService.updateComment(co));
            commentService.deleteComment(co.getId());
        }

        // Loan test
        Loan loan = new Loan();
        loan.setBookId(book.getId());
        loan.setUserId(user.getId());
        loan.setLoanDate(new Date());
        loan.setReturnDate(new Date());
        LoanService loanService = new LoanServiceImpl();
        LOGGER.info(loanService.createLoan(loan));
        Collection<Loan> loans = loanService.getAllLoans();
        for (Loan lo : loans) {
            LOGGER.info(lo.getId());
            LOGGER.info(loanService.getLoanById(lo.getId()));
            lo.setReturnDate(new Date());
            LOGGER.info(loanService.updateLoan(lo));
            loanService.deleteLoan(lo.getId());
        }
        */
/*
            XMLInputFactory factory = XMLInputFactory.newInstance();
        try (FileInputStream fis = new FileInputStream("src/main/resources/library.xml")) {
            XMLEventReader reader = factory.createXMLEventReader(fis);

            while (reader.hasNext()) {
                XMLEvent xmlEvent = reader.nextEvent();

                switch (xmlEvent.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        handleStartElement(xmlEvent.asStartElement());
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        handleCharacters(xmlEvent.asCharacters());
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        handleEndElement(xmlEvent.asEndElement());
                        break;
                }
            }

            try {
                JAXBContext context = JAXBContext.newInstance(Library.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                Library library = (Library) unmarshaller.unmarshal(fis);
                System.out.println();
            } catch(JAXBException e) {
                LOGGER.error(e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try (FileInputStream jsonFile = new FileInputStream("src/main/resources/library.json")) {
            try {
                Library libraryJson = objectMapper.readValue(jsonFile, Library.class);
                System.out.println();
            } catch (RuntimeException e) {
                LOGGER.error(e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        //Factory pattern
        Author author = new Author();
        author.setName("Maria Elena Walsh");
        author.setNationality("Argentina");
        AuthorServiceImpl authorService = new AuthorServiceImpl("Mybatis");
        LOGGER.info(authorService.createAuthor(author).getName());
        LOGGER.info(authorService.getAllAuthors());

        //Abstract factory
        Branch branch = new Branch();
        branch.setLocation("United States");
        BranchService branchService = new BranchServiceImpl("Mybatis");
        //collect id of the branch
        Collection<Branch> branches = branchService.getAllBranchs();
        for (Branch br : branches) {
            if (branch.getId() == null && br.getId() != null) {
                branch.setId(br.getId());
            }
        }

        //Builder
        Comment commentDAO = Comment.builder()
                .comment("Great")
                .bookId(book.getId())
                .userId(user.getId())
                .build();
        LOGGER.info(commentDAO.getComment());

        //Listener
        ListenerHolder.subscribe(new BookInventoryListenerImpl());

        Inventory inventory = new Inventory();
        inventory.setBookId(book.getId());
        inventory.setBranchId(branch.getId());
        inventory.setStockQuantity(10);
        InventoryService inventoryService = new InventoryServiceImpl();
        LOGGER.info(inventoryService.createInventory(inventory));
        Collection<Inventory> inventories = inventoryService.getAllInventories();
        book.setInventories(inventories);

        //Strategy
        authorService.setAuthorRepository("JDBC");

        //Decorator
        Book.sellBook(true, false);


    }

    private static void handleStartElement(StartElement startElement) {
        String elementName = startElement.getName().getLocalPart();
        LOGGER.info("Start Element: " + elementName);

        Iterator<Attribute> attributes = startElement.getAttributes();
        while (attributes.hasNext()) {
            Attribute attribute = attributes.next();
            String attributeName = attribute.getName().getLocalPart();
            String attributeValue = attribute.getValue();
            LOGGER.info("Attribute: " + attributeName + ", Value: " + attributeValue);
        }
    }

    private static void handleCharacters(Characters characters) {
        String text = characters.getData().trim();
        if (!text.isEmpty()) {
            LOGGER.info("Value: " + text);
        }
    }

    private static void handleEndElement(EndElement endElement) {
        String elementName = endElement.getName().getLocalPart();
        LOGGER.info("End Element: " + elementName);
    }






}