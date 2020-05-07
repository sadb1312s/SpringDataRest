CREATE TABLE public.book (
    id serial NOT NULL primary key,
    title character varying(50) NOT NULL,
    price real NOT NULL,
    warehouse character varying(50) NOT NULL,
    count integer NOT NULL
)