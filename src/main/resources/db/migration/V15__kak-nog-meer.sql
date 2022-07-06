alter table eurder."order"
    drop column fk_itemgroup_id;

alter table eurder.order
    add column fk_customer_id int;

alter table eurder."order"
    add constraint fk_customer_id
        foreign key (fk_customer_id) references eurder."user";
