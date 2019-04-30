--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.11
-- Dumped by pg_dump version 9.6.11

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: order_product; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.order_product (
    id integer NOT NULL,
    product_id integer NOT NULL,
    order_id integer NOT NULL
);


--
-- Name: TABLE order_product; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON TABLE public.order_product IS 'Lista de productos por ordenes de compra';


--
-- Name: COLUMN order_product.product_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.order_product.product_id IS 'Identificador foraneo del producto';


--
-- Name: COLUMN order_product.order_id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.order_product.order_id IS 'Identificador unico de la orden de pago';


--
-- Name: order_product_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.order_product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: order_product_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.order_product_id_seq OWNED BY public.order_product.id;


--
-- Name: orders; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.orders (
    id integer NOT NULL,
    total double precision DEFAULT 0,
    currency character varying(3)
);


--
-- Name: TABLE orders; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON TABLE public.orders IS 'Ordenes de compra';


--
-- Name: COLUMN orders.total; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.orders.total IS 'Total de la lista de ordenes de compra de productos';


--
-- Name: COLUMN orders.currency; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.orders.currency IS 'Moneda';


--
-- Name: orders_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.orders_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: orders_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.orders_id_seq OWNED BY public.orders.id;


--
-- Name: products; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.products (
    id integer NOT NULL,
    sku character varying(50) NOT NULL,
    nombre character varying(50) NOT NULL,
    precio double precision NOT NULL,
    moneda character varying(3) NOT NULL
);


--
-- Name: TABLE products; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON TABLE public.products IS 'Tabla de los productos';


--
-- Name: COLUMN products.id; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.products.id IS 'Campo unico secuencial';


--
-- Name: COLUMN products.sku; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.products.sku IS 'Serial del producto';


--
-- Name: COLUMN products.nombre; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.products.nombre IS 'Nombre del producto';


--
-- Name: COLUMN products.precio; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.products.precio IS 'Precio del producto';


--
-- Name: COLUMN products.moneda; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.products.moneda IS 'Moneda del producto';


--
-- Name: products_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.products_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- Name: products_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;


--
-- Name: order_product id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.order_product ALTER COLUMN id SET DEFAULT nextval('public.order_product_id_seq'::regclass);


--
-- Name: orders id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.orders ALTER COLUMN id SET DEFAULT nextval('public.orders_id_seq'::regclass);


--
-- Name: products id; Type: DEFAULT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);


--
-- Data for Name: order_product; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.order_product (id, product_id, order_id) VALUES (1, 1, 1);
INSERT INTO public.order_product (id, product_id, order_id) VALUES (2, 2, 1);


--
-- Name: order_product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.order_product_id_seq', 2, true);


--
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.orders (id, total, currency) VALUES (2, 0, NULL);
INSERT INTO public.orders (id, total, currency) VALUES (1, 300, 'USD');


--
-- Name: orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.orders_id_seq', 2, true);


--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO public.products (id, sku, nombre, precio, moneda) VALUES (1, '101010', 'Producto1', 100, 'USD');
INSERT INTO public.products (id, sku, nombre, precio, moneda) VALUES (2, '101012', 'Producto2', 200, 'USD');
INSERT INTO public.products (id, sku, nombre, precio, moneda) VALUES (3, '101013', 'Producto3', 300, 'USD');
INSERT INTO public.products (id, sku, nombre, precio, moneda) VALUES (4, '101014', 'Producto4', 400, 'USD');


--
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public.products_id_seq', 4, true);


--
-- Name: order_product order_product_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.order_product
    ADD CONSTRAINT order_product_pkey PRIMARY KEY (id);


--
-- Name: order_product order_product_product_id_order_id_key; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.order_product
    ADD CONSTRAINT order_product_product_id_order_id_key UNIQUE (product_id, order_id);


--
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);


--
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


--
-- Name: order_product order_product_order_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.order_product
    ADD CONSTRAINT order_product_order_id_fkey FOREIGN KEY (order_id) REFERENCES public.orders(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: order_product order_product_product_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.order_product
    ADD CONSTRAINT order_product_product_id_fkey FOREIGN KEY (product_id) REFERENCES public.products(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--