CREATE TABLE public.buy (
    id serial NOT NULL primary key,
    date date NOT NULL,
    idstore integer NOT NULL REFERENCES store(id),
    idbuyer integer NOT NULL REFERENCES buyer(id),
    idbook integer NOT NULL REFERENCES book(id),
    count integer NOT NULL,
    sum real NOT NULL
);