alter table eurder."order"
    add fk_itemgroup_id int;

alter table eurder.itemgroup
    drop column fk_order;

alter table eurder."order"
    add constraint fk_itemgroup_id
        foreign key (fk_itemgroup_id) references eurder."itemgroup";
