DROP SCHEMA IF EXISTS example CASCADE;

DROP ROLE IF EXISTS owner_example;

CREATE ROLE owner_example WITH
NOLOGIN
NOSUPERUSER
INHERIT
NOCREATEDB
NOCREATEROLE
NOREPLICATION;

GRANT owner_example TO postgres;

CREATE SCHEMA example AUTHORIZATION owner_example;

GRANT ALL ON SCHEMA example TO owner_example;

CREATE TYPE example.genre AS ENUM ('DRAMA', 'ROMANCE', 'GUIDE', 'TRAVEL');

ALTER TYPE example.genre OWNER TO owner_example;

CREATE TABLE example.books
(
  id uuid,
  title text NOT NULL,
  author text NOT NULL,
  genre example.genre NOT NULL,
  PRIMARY KEY (id))
WITH (OIDS = FALSE);

ALTER TABLE example.books OWNER to owner_example;

-- Table: example.categoria

-- DROP TABLE example.categoria;

CREATE TABLE example.categoria
(
    id bigint NOT NULL DEFAULT nextval('example.categoria_id_seq'::regclass),
    nombre character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT categoria_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE example.books OWNER to owner_example;
