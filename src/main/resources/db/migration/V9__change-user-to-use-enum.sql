alter table "user"
    add role varchar(50) default 'customer' not null;

alter table "user"
    drop column user_type;

