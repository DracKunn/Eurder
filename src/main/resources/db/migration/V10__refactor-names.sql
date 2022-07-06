alter table eurder."user"
    drop column full_name;

alter table eurder."user"
    add first_name varchar(50);

alter table eurder."user"
    add last_name varchar(50);

