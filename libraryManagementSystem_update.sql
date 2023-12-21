-- Update an author's name
UPDATE authors SET name = 'B. Sanderson' WHERE id_author = 1;

-- Update a publisher's location
UPDATE publishers SET name = 'Tor publisher' WHERE id_publisher = 1;

-- Update a person's first name
UPDATE persons SET f_name = 'Johnny' WHERE id_person = 3;

-- Update a category's name
UPDATE categories SET name = 'Sci-Fi' WHERE id_category = 4;

-- Update a reservation's date
UPDATE reservations SET reservation_date = '2023-02-21 09:00:00' WHERE id_reservation = 2;

-- Update a book's title
UPDATE books SET name = 'Stormlight Archive' WHERE id_book = 1;

-- Update the stock quantity in inventory
UPDATE inventories SET stock_quantity = 25 WHERE id_inventory = 2;

-- Update a comment
UPDATE comments SET comment = 'Amazing book!' WHERE id_comment = 1;

-- Update a loan's date
UPDATE loans SET loan_date = '2023-04-01 13:20:00' WHERE id_loan = 1;

-- Update an employee's branch
UPDATE employees SET id_branch = 2 WHERE id_employee = 1;