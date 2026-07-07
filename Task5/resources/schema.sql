create table account (
                         id serial primary key,
                         name varchar(20),
                         email varchar(20),
                         password varchar(20),
                         profileDescription varchar(100)
);

INSERT INTO account (name, email, password, profileDescription) VALUES ('zaur', 'zaur@gmail.com', '1324', 'Programmer');
INSERT INTO account (name, email, password, profileDescription) VALUES ('nikita', 'nikita@gmail.com', '228', 'Programmer');
