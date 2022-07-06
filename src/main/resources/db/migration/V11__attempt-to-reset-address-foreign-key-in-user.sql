alter table eurder."user"
    drop constraint fk_address_id;

alter table eurder."user"
    add constraint user_address_id_fk
        foreign key (fk_address_id) references eurder.address;

