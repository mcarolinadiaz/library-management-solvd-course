package com.solvd.library;

import com.solvd.library.domain.*;
import com.solvd.library.service.*;
import com.solvd.library.service.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.Date;

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
            publisherService.deletePublisher(p.getId());
        }

        // Category test
        Category category = new Category();
        category.setName("Fantasy");
        CategoryService categoryService = new CategoryServiceImpl();
        LOGGER.info(categoryService.createCategory(category).getName());
        Collection<Category> categorys = categoryService.getAllCategorys();
        for (Category ca : categorys) {
            LOGGER.info(ca.getId());
            LOGGER.info(categoryService.getCategoryById(ca.getId()));
            ca.setName("Terror");
            LOGGER.info(categoryService.updateCategory(ca));
            categoryService.deleteCategory(ca.getId());
        }

        // Branch test
        Branch branch = new Branch();
        branch.setLocation("United States");
        BranchService branchService = new BranchServiceImpl();
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
        }

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
/*
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
            userService.deleteUser(u.getId());
        }
 */

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
        }
/*
        EmployeeService employeeService = new EmployeeServiceImpl();
        UserService userService = new UserServiceImpl();
        BranchService branchService = new BranchServiceImpl();
        BookService bookService = new BookServiceImpl();
        AuthorService authorService = new AuthorServiceImpl();
        InventoryService inventoryService = new InventoryServiceImpl();
        ReservationService reservationService = new ReservationServiceImpl();
        CategoryService categoryService = new CategoryServiceImpl();
        LoanService loanService = new LoanServiceImpl();
        PublisherService publisherService = new PublisherServiceImpl();

        Book book1 = new Book();
        book1.setName("Elantris");
        book1.setYear(new Date());
        Book book2 = new Book();
        book2.setName("1984");
        book2.setYear(new Date());

        Branch branch1 = new Branch();
        branch1.setLocation("United States");
        Branch branch2 = new Branch();
        branch2.setLocation("Colombia");

        Employee employee = new Employee();
        employee.setBranchId(branch1.getId());
        employee.setPersonId(person.getId());


        Inventory inventory = new Inventory();
        inventory.setStockQuantity(10);
        inventory.setBranchId(branch1.getId());
        inventory.setBookId(book1.getId());

        Publisher publisher = new Publisher();
        publisher.setName("Orange");

        Reservation reservation = new Reservation();
        reservation.setReservationDate(new Date());

      //  publisher = publisherService.createPublisher(publisher);
      //  reservation = reservationService.createReservation(reservation);
     //   branch1 = branchService.createBranch(branch1);
       //LOGGER.info(employee.getPersonId());


        List<Branch> branches = branchService.getAllBranchs();
        for (Branch br : branches) {
            LOGGER.info(br.getId());
          //  branchService.deleteBranch(br.getId());
        }

        LOGGER.info(reservationService.getAllReservations());
        List<Reservation> reservations = reservationService.getAllReservations();
        for (Reservation r : reservations) {
           // reservationService.deleteReservation(r.getId());
            LOGGER.info(r.getId());
        }

*/

    }
}