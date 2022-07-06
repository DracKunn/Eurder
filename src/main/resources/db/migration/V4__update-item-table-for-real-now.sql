alter table item
    add description character varying(50) not null;

alter table item
    add price double precision not null default 0;

alter table item
    add stock int not null default 0;
