CREATE TABLE public.buyer (
    id serial NOT NULL primary key,
    lastname character varying(50) NOT NULL,
    district character varying(100) NOT NULL,
    discount integer NOT NULL
);