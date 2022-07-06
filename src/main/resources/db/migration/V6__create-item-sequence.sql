CREATE SEQUENCE item_id_seq;

ALTER TABLE item ALTER COLUMN id SET DEFAULT nextval('item_id_seq');
ALTER SEQUENCE item_id_seq OWNED BY item.id;
