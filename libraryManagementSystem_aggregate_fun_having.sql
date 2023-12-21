-- max value of loan_date per book
SELECT b.name, MAX(l.loan_date) AS max_loan_date
FROM books b
LEFT JOIN loans l ON b.id_book = l.id_book
GROUP BY b.id_book
HAVING COUNT(l.id_loan) >= 1;
----------------------------------------------------------------
-- max stock that is greater than 16
select max(i.stock_quantity)
from inventories i
group by i.id_book
having max(i.stock_quantity) > 16;
-------------------------------------
-- Count of Books per Category with more than 1 Book:
SELECT c.name AS category_name, COUNT(b.id_book) AS book_count
FROM categories c
LEFT JOIN books b ON c.id_category = b.id_category
GROUP BY c.name
HAVING COUNT(b.id_book) > 1;
    
------------------------
-- Average comment length per book with average length above 10 characters:
SELECT c.name, AVG(LENGTH(co.comment)) AS avg_comment_length
FROM comments co
RIGHT JOIN books c ON c.id_book = co.id_book
GROUP BY c.id_book
HAVING AVG(LENGTH(co.comment)) > 10;
----------------------------------
-- Total Reservations per User with more than 1 Reservation:
SELECT u.id_user, COUNT(r.id_reservation) AS reservation_count
FROM users u
LEFT JOIN reservations r ON u.id_reservation = r.id_reservation
GROUP BY u.id_user
HAVING COUNT(r.id_reservation) > 1;

-- Authors with more than 1 Book:
SELECT a.name AS author_name, COUNT(ahb.id_book) AS book_count
FROM authors a
LEFT JOIN authors_has_books ahb ON a.id_author = ahb.id_author
GROUP BY a.name
HAVING COUNT(ahb.id_book) > 1;

-- Average Stock Quantity per Branch with Average Stock Quantity above 15:
SELECT br.location, AVG(inv.stock_quantity) AS avg_stock
FROM branches br
LEFT JOIN inventories inv ON br.id_branch = inv.id_branch
GROUP BY br.location
HAVING AVG(inv.stock_quantity) > 15;