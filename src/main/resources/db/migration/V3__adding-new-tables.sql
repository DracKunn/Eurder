CREATE TABLE IF NOT EXISTS "user"
(
    id            serial                NOT NULL,
    user_name     character varying(50) NOT NULL,
    full_name     character varying(50) NOT NULL,
    email         character varying(50) NOT NULL,
    fk_address_id integer,
    phone_number  character varying(50),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS "itemgroup"
(
    id            serial           NOT NULL,
    fk_item_id    integer          NOT NULL,
    price_order   double precision NOT NULL,
    stock_order   integer          NOT NULL,
    order_amount  integer          NOT NULL,
    shipping_date date             NOT NULL,
    fk_order      integer          NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS "order"
(
    id             serial  NOT NULL,
    fk_customer_id integer NOT NULL,
    PRIMARY KEY (id)
);


