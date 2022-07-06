CREATE TABLE IF NOT EXISTS "address"
(
    id serial NOT NULL,
    street_name character varying(50) NOT NULL,
    street_number integer NOT NULL,
    postal_code character varying(50) NOT NULL,
    PRIMARY KEY (id)
);
