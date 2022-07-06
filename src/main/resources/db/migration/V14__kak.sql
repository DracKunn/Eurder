alter table eurder."order"
    drop column fk_customer_id;

alter table eurder.itemgroup
    add column fk_order_id int;

alter table eurder."itemgroup"
    add constraint fk_order_id
        foreign key (fk_order_id) references eurder."order";
