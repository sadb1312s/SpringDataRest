CREATE TABLE public.store (
    id serial NOT NULL primary key,
    title character varying(50) NOT NULL,
    district character varying(50) NOT NULL,
    commission integer NOT NULL
);