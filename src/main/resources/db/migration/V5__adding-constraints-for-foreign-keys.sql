alter table eurder.itemgroup
    add constraint fk_item_id
        foreign key (id) references eurder.item,
    add constraint fk_order
        foreign key (id) references eurder.order;

alter table eurder.order
    add constraint fk_customer_id
        foreign key (id) references eurder.user;

alter table eurder.user
    add constraint fk_address_id
        foreign key (id) references eurder.address;

