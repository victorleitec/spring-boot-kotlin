create table anwsers
(
    id         bigint      not null auto_increment,
    message    varchar(50) not null,
    created_at datetime    not null,
    topic_id   bigint      not null,
    author_id  bigint      not null,
    solution   boolean     not null,
    primary key (id),
    foreign key (topic_id) references topics (id),
    foreign key (author_id) references users (id)
);