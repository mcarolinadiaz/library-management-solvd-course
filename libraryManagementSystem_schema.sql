create table if not exists authors(
	id_author serial,
    name varchar(45) not null,
    nationality varchar(45),
    PRIMARY KEY (id_author)
);

create table if not exists publishers(
	id_publisher serial,
    name varchar(45),
    PRIMARY KEY (id_publisher)
);

create table if not exists branches(
	id_branch serial,
    location varchar(45),
    PRIMARY KEY (id_branch)
);

create table if not exists persons(
	id_person serial,
    f_name varchar(45) not null,
    l_name varchar(45) not null,
    PRIMARY KEY (id_person)
);

create table if not exists categories(
	id_category serial,
    name varchar(45) not null,
    PRIMARY KEY (id_category)
);

create table if not exists reservations(
	id_reservation serial,
    reservation_date timestamp,
    PRIMARY KEY (id_reservation)
);

create table if not exists books(
	id_book serial,
    name varchar(45) not null,
    year timestamp,
    id_publisher bigint unsigned,
    id_category bigint unsigned,
    id_reservation bigint unsigned,
    PRIMARY KEY (id_book),
    CONSTRAINT FK_books_publishers FOREIGN KEY (id_publisher)
    REFERENCES publishers(id_publisher) ON UPDATE NO ACTION ON DELETE CASCADE,
    CONSTRAINT FK_books_categories FOREIGN KEY (id_category)
    REFERENCES categories(id_category) ON UPDATE NO ACTION ON DELETE CASCADE,
    CONSTRAINT FK_books_reservations FOREIGN KEY (id_reservation)
    REFERENCES reservations(id_reservation) ON UPDATE NO ACTION ON DELETE CASCADE
);

create table if not exists authors_has_books(
	id_book bigint unsigned unsigned,
    id_author bigint unsigned unsigned,
    PRIMARY KEY (id_book, id_author),
    CONSTRAINT FK_authors_has_books_books FOREIGN KEY (id_book)
    REFERENCES books(id_book) ON UPDATE NO ACTION ON DELETE CASCADE,
    CONSTRAINT FK_authors_has_books_authors FOREIGN KEY (id_author)
    REFERENCES authors(id_author) ON UPDATE NO ACTION ON DELETE CASCADE
);

create table if not exists users(
	id_user serial,
    id_person bigint unsigned unsigned,
    id_reservation bigint unsigned,
    PRIMARY KEY (id_user),
    CONSTRAINT FK_users_persons FOREIGN KEY (id_person)
    REFERENCES persons(id_person) ON UPDATE NO ACTION ON DELETE CASCADE,
    CONSTRAINT FK_users_reservations FOREIGN KEY (id_reservation)
    REFERENCES reservations(id_reservation) ON UPDATE NO ACTION ON DELETE CASCADE
);

create table if not exists employees(
	id_employee serial,
    id_branch bigint unsigned,
    id_person bigint unsigned,
    PRIMARY KEY (id_employee),
    CONSTRAINT FK_employees_persons FOREIGN KEY (id_person)
    REFERENCES persons(id_person) ON UPDATE NO ACTION ON DELETE CASCADE,
    CONSTRAINT FK_users_branches FOREIGN KEY (id_branch)
    REFERENCES branches(id_branch) ON UPDATE NO ACTION ON DELETE CASCADE
);

create table if not exists inventories(
	id_inventory serial,
    stock_quantity int,
    id_branch bigint unsigned,
    id_book bigint unsigned,
    PRIMARY KEY (id_inventory),
    CONSTRAINT FK_inventories_books FOREIGN KEY (id_book)
    REFERENCES books(id_book) ON UPDATE NO ACTION ON DELETE CASCADE,
    CONSTRAINT FK_inventories_branches FOREIGN KEY (id_branch)
    REFERENCES branches(id_branch) ON UPDATE NO ACTION ON DELETE CASCADE
);

create table if not exists comments(
	id_comment serial,
    comment varchar(200),
    id_book bigint unsigned,
    id_user bigint unsigned,
    PRIMARY KEY (id_comment),
    CONSTRAINT FK_comments_books FOREIGN KEY (id_book)
    REFERENCES books(id_book) ON UPDATE NO ACTION ON DELETE CASCADE,
    CONSTRAINT FK_comments_users FOREIGN KEY (id_user)
    REFERENCES users(id_user) ON UPDATE NO ACTION ON DELETE CASCADE
);

create table if not exists loans(
	id_loan serial,
    loan_date timestamp,
    return_date timestamp,
    id_book bigint unsigned,
    id_user bigint unsigned,
    PRIMARY KEY (id_loan),
    CONSTRAINT FK_loans_books FOREIGN KEY (id_book)
    REFERENCES books(id_book) ON UPDATE NO ACTION ON DELETE CASCADE,
    CONSTRAINT FK_loans_users FOREIGN KEY (id_user)
    REFERENCES users(id_user) ON UPDATE NO ACTION ON DELETE CASCADE
);


