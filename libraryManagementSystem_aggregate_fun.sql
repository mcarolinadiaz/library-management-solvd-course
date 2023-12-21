-- max value of stock per book
select max(stock_quantity), b.name as book_name
from inventories i
join books b on b.id_book = i.id_book
group by i.id_book;

-- count of comments per book
select count(*), b.name as book_name
from comments c
join books b on b.id_book = c.id_book
group by c.id_book;

-- Count of categories per book
select count(*), c.name
from books b
join categories c on c.id_category = b.id_category
group by b.id_category;

-- count of persons who last name begins with 'S'
select count(l_name)
from persons
where l_name like 'S%';

-- Average comment length per book
SELECT b.name, AVG(LENGTH(c.comment)) AS avg_comment_length
FROM comments c
join books b on c.id_book = b.id_book
GROUP BY c.id_book;

-- maximun value of loan date per book
SELECT b.name, MAX(l.loan_date) AS max_loan_date
FROM loans l
join books b on l.id_book = b.id_book
GROUP BY l.id_book;

-- Count employees 
SELECT count(*) as count_employees
from employees 