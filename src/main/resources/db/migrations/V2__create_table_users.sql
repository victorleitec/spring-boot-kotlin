create table users
(
    id    bigint      not null auto_increment,
    name  varchar(50) not null,
    email varchar(50) not null,
    primary key (id)
);

insert into users (id, name, email)
VALUES (1, 'John', 'john@email.com');