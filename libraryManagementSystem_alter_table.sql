-- add column into authors table
ALTER TABLE authors ADD COLUMN birth_date DATE;

-- drop column into authors birth_date
ALTER TABLE authors DROP COLUMN birth_date;

-- rename name column's categories table
ALTER TABLE categories RENAME COLUMN name TO category_name;

-- modify value of category_name column 
ALTER TABLE categories ALTER COLUMN category_name SET DEFAULT 'Fantasy';

-- drop name colum's publishers table
ALTER TABLE publishers DROP COLUMN name;