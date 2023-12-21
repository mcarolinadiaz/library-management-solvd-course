-- Show each book per author
SELECT b.id_book, 
b.name as book_name,
a.id_author,
a.name as author_name
FROM books b
JOIN authors_has_books ahb ON b.id_book = ahb.id_book
LEFT JOIN authors a ON a.id_author = ahb.id_author;

-- Show stock of all book in per branch
SELECT 
b.name as book_name,
i.stock_quantity as stock,
br.location as branch_location
FROM books b
INNER JOIN inventories i ON i.id_book = b.id_book
JOIN branches br ON br.id_branch = i.id_branch;

-- show information about users
SELECT *
from persons pe
RIGHT JOIN users u on u.id_person = pe.id_person;

-- show all books with its comments
SELECT b.name as book_name,
c.comment as comment
FROM books b
LEFT OUTER JOIN comments c ON b.id_book = c.id_book;

-- showing first name and last name about each employee.
SELECT f_name, l_name
from persons pe
RIGHT JOIN employees e on e.id_person = pe.id_person;
