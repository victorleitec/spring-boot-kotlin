create table users_role
(
    id      bigint not null auto_increment,
    user_id bigint not null,
    role_id bigint not null,
    primary key (id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references role (id)
);

insert into users_role (id, user_id, role_id)
VALUES (1, 1, 1);