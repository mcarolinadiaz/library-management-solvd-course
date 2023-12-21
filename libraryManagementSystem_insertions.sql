INSERT INTO authors (name, nationality)
VALUES ("Brandon Sanderson", "united-statesian"),
 ('Gabriel Garcia Marquez', 'colombian');
 
-- Insertion in the table 'publishers'
INSERT INTO publishers (name)
VALUES 
  ('Tor Books'),
  ('Editorial Sudamericana');

-- Insertions in the table 'persons'
INSERT INTO persons (f_name, l_name)
VALUES 
  ('John', 'Smith'),
  ('Sam', 'Petterson'),
  ('Carl', 'Smith'),
  ('Jean', 'Stinson'),
  ('Phoebe', 'White'),
  ('Ross', 'Green');

-- Insertions in the table 'categories'
INSERT INTO categories (name)
VALUES 
  ('Fiction'),
  ('Non-Fiction'),
  ('Mystery'),
  ('Science Fiction'),
  ('Romance'),
  ('Fantasy');

-- Insertions in the table 'reservations'
INSERT INTO reservations (reservation_date)
VALUES 
  ('2023-01-15 10:00:00'),
  ('2023-02-20 15:30:00'),
  ('2023-03-10 08:45:00');

-- Insertions in the table 'books'
INSERT INTO books (name, year, id_publisher, id_category, id_reservation)
VALUES 
  ('Rhythm of War', '2000-01-01', 1, 6, 2),
  ('Cien anos de soledad', '2015-05-15', 2, 1, 1),
  ('Elantris', '1987-11-30', 1, 6, 3);

-- Insertions in the table 'authors_has_books'
INSERT INTO authors_has_books (id_book, id_author)
VALUES 
  (1, 1), -- Brandon Sanderson wrote Book1
  (2, 2); -- Gabril garcia Marquez wrote Book2

-- Insertions in the table 'users'
INSERT INTO users (id_person, id_reservation)
VALUES 
  (1, 2),
  (2, 1),
  (3, 3);
  
-- Insertion in the table 'branches'
INSERT INTO branches (location)
VALUES 
  ('United States'),
  ('Italy');

-- Insertions in the table 'employees'
INSERT INTO employees (id_branch, id_person)
VALUES 
  (1, 4),
  (2, 5),
  (1, 6);

-- Insertions in the table 'inventories'
INSERT INTO inventories (stock_quantity, id_branch, id_book)
VALUES 
  (10, 1, 1),
  (15, 2, 2),
  (20, 2, 1);

-- Insertions in the table 'comments'
INSERT INTO comments (comment, id_book, id_user)
VALUES 
  ('Great book!', 1, 1),
  ('Interesting plot.', 3, 2),
  ('Must-read!', 1, 3);

-- Insertions in the table 'loans'
INSERT INTO loans (loan_date, return_date, id_book, id_user)
VALUES 
  ('2023-04-01 12:00:00', '2023-04-15 12:00:00', 1, 1),
  ('2023-04-02 14:30:00', '2023-04-16 14:30:00', 2, 2),
  ('2023-04-03 10:45:00', '2023-04-17 10:45:00', 3, 1);