SELECT
    b.name AS book_name,
    b.year AS book_year,
    a.name AS author_name,
    a.nationality AS author_nationality,
    p.name AS publisher_name,
    pe.f_name AS person_first_name,
    pe.l_name AS person_last_name,
    c.name AS category_name,
    r.reservation_date,
    i.stock_quantity AS inventory_stock_quantity,
    co.comment AS comment_text,
    l.loan_date,
    l.return_date
FROM
    authors a
JOIN
    authors_has_books ahb ON a.id_author = ahb.id_author
JOIN
    books b ON ahb.id_book = b.id_book
JOIN
    publishers p ON b.id_publisher = p.id_publisher
JOIN
    categories c ON b.id_category = c.id_category
JOIN
    reservations r ON b.id_reservation = r.id_reservation
LEFT JOIN
    users u ON u.id_reservation = r.id_reservation
LEFT JOIN
    inventories i ON i.id_book = b.id_book
LEFT JOIN
    branches br ON i.id_branch = br.id_branch
LEFT JOIN
    employees e ON e.id_branch = br.id_branch
LEFT JOIN
    comments co ON co.id_book = b.id_book
LEFT JOIN
    loans l ON l.id_book = b.id_book
LEFT JOIN
    persons pe ON pe.id_person = u.id_person;