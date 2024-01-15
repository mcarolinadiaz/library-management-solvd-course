package com.solvd.library;

import com.solvd.library.domain.*;
import com.solvd.library.persistence.PersonRepository;
import com.solvd.library.persistence.impl.PersonJDBCImpl;
import com.solvd.library.service.*;
import com.solvd.library.service.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        // DAOs test
        Person person = new Person();
        person.setFirstName("John");
        person.setLastName("Smith");
        Person person2 = new Person();
        person2.setFirstName("Anna");
        person2.setLastName("Johnson");

        PersonService personService = new PersonServiceImpl();
        //LOGGER.info(personService.createPerson(person).getFirstName());
        //LOGGER.info(personService.createPerson(person2).getFirstName());
        List<Person> persons = personService.getAllPersons();
        for (Person person3 : persons) {
            LOGGER.info(person3.getFirstName());
            LOGGER.info(person3.getLastName());
        }

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



    }
}