create table item
(
    id   integer not null,
    name text    not null unique,
    constraint pk_item primary key (id)
);
