# library-management-solvd-course
This design provides a foundation for a library management system with various relationships between tables.
- The "Books" table stores information about books, including related details.
- "Authors," "Categories," and "Publishers" are auxiliary tables containing information about authors, categories, and publishers respectively.
- The "Loans" and "Reservations" tables establish relationships between books and users to manage loans and reservations.
- "Employees" and "Branches" can be used to manage library staff and locations.
- "Inventory" tracks the quantity of books in stock at each branch.
- The "Comments" table allows users to leave comments and ratings for books.
- "Authors_Books" is a junction table establishing many-to-many relationships between authors and books.

1- Create a database schema using Mysql Workbench for the new hierarchy with at least 12 tables and all relations. The schema should satisfy the 3 Normal Forms.

2- 
- 10 statements for insertion.
- 10 statements for updating.
- 10 statements for deletions. 
- 5 alter table.
- 1 big statement to join all tables in the database.
- 5 statements with left, right, inner, outer joins.
- 7 statements with aggregate functions and group by and without having.
- 7 statements with aggregate functions and group by and with having.
- Solution:
    - Create a database schema using Mysql Workbench for the new hierarchy, it's represented by libraryManagementSystem_hierarchy.mwb that is the schema created by workbench and libraryManagementSystem_schema.sql in sql code.
    - libraryManagementSystem_insertions.sql: 10 statements for insertion.
    - libraryManagementSystem_delete.sql: 10 statements for deletions. 
    - libraryManagementSystem_update.sql: 10 statements for updating. 
    - libraryManagementSystem_alter_table.sql: 5 alter table.
    - libraryManagementSystem_aggregate_fun.sql: 7 statements with aggregate functions and group by and without having.
    - libraryManagementSystem_joins.sql: 5 statements with left, right, inner, outer joins.
    - libraryManagementSystem_big_join.sql:  1 big statement to join all tables in the database.
    - libraryManagementSystem_aggregate_fun_having.sql: 7 statements with aggregate functions and group by and with having.

3- 
- Read the following articles:
    DAO pattern doc1, doc2 and doc3
    JDBC (official docs)
    JDBC (w3schools)
- Build hierarchy for Schema from the below course.
- Create DAO classes with necessary interfaces, abstract classes, and Generics.  DAO should be scalable and flexible to support another framework and another database as well. All CRUD operations should be supported using JDBC. Use connection pool from the below block.
- Implement Service layer with necessary abstraction to be able to switch between databases and frameworks.
