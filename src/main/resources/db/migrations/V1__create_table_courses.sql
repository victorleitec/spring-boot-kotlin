create table courses
(
    id       bigint      not null auto_increment,
    name     varchar(50) not null,
    category varchar(50) not null,
    primary key (id)
);

insert into courses (id, name, category)
VALUES (1, 'Kotlin', 'Programming');

insert into courses (id, name, category)
VALUES (2, 'Java', 'Programming');