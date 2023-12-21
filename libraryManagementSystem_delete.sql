-- Delete an specific author
DELETE FROM authors WHERE name like '%Sanderson';

-- Delete an specific book
DELETE FROM books WHERE name like '%de soled%';

-- Delete specific reservation
DELETE FROM reservations WHERE reservation_date = '2023-02-20 15:30:00';

-- Delete specific comment
DELETE FROM comments WHERE comment like 'Great%';

-- Delete specific user
DELETE FROM users WHERE id_person = 2;

-- Delete specific employee
DELETE FROM employees WHERE id_person = 5;

-- Delete specific inventory
DELETE FROM inventories WHERE id_inventory = 2;

-- Delete specific loan with loan date
DELETE FROM loans WHERE loan_date = '2023-04-01 12:00:00';

-- Delete relationship between authors and books
DELETE FROM authors_has_books WHERE id_book = 1 AND id_author = 1;

-- Delete specific categories
DELETE FROM categories WHERE name in ('Science Fiction', 'Fantasy');