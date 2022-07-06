alter table eurder.itemgroup
    drop constraint fk_item_id;

alter table eurder.itemgroup
    add constraint fk_item_id
        foreign key (fk_item_id) references eurder.item;

alter table eurder.itemgroup
    drop constraint fk_order;

alter table eurder.itemgroup
    add constraint fk_order
        foreign key (fk_order) references eurder."order";

alter table eurder."order"
    drop constraint fk_customer_id;

alter table eurder."order"
    add constraint fk_customer_id
        foreign key (fk_customer_id) references eurder."user";

