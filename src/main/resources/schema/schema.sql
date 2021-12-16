--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1
-- Dumped by pg_dump version 14.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: exchange; Type: TABLE; Schema: public; Owner: eanderson
--

CREATE TABLE public.exchange (
    id integer NOT NULL,
    name character varying(50) NOT NULL,
    created_on timestamp without time zone,
    start_date timestamp without time zone,
    end_date timestamp without time zone,
    host_id integer,
    enrollment_status character varying(50),
    match_date timestamp without time zone
);



--
-- Name: exchange_has_participant; Type: TABLE; Schema: public; Owner: eanderson
--

CREATE TABLE public.exchange_has_participant (
    exchange_id integer NOT NULL,
    participant_id integer NOT NULL
);



--
-- Name: exchange_id_seq; Type: SEQUENCE; Schema: public; Owner: eanderson
--

CREATE SEQUENCE public.exchange_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: exchange_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eanderson
--

ALTER SEQUENCE public.exchange_id_seq OWNED BY public.exchange.id;


--
-- Name: match; Type: TABLE; Schema: public; Owner: eanderson
--

CREATE TABLE public.match (
    id integer NOT NULL,
    exchange_id integer NOT NULL,
    gifter_id integer NOT NULL,
    recipient_id integer NOT NULL,
    is_fulfilled boolean NOT NULL,
    fulfillment_date timestamp without time zone
);



--
-- Name: match_id_seq; Type: SEQUENCE; Schema: public; Owner: eanderson
--

CREATE SEQUENCE public.match_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: match_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eanderson
--

ALTER SEQUENCE public.match_id_seq OWNED BY public.match.id;


--
-- Name: user; Type: TABLE; Schema: public; Owner: eanderson
--

CREATE TABLE public."user" (
    id integer NOT NULL,
    email character varying(320) NOT NULL,
    auth0_id character varying
);



--
-- Name: user_id_seq; Type: SEQUENCE; Schema: public; Owner: eanderson
--

CREATE SEQUENCE public.user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;



--
-- Name: user_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: eanderson
--

ALTER SEQUENCE public.user_id_seq OWNED BY public."user".id;


--
-- Name: exchange id; Type: DEFAULT; Schema: public; Owner: eanderson
--

ALTER TABLE ONLY public.exchange ALTER COLUMN id SET DEFAULT nextval('public.exchange_id_seq'::regclass);


--
-- Name: match id; Type: DEFAULT; Schema: public; Owner: eanderson
--

ALTER TABLE ONLY public.match ALTER COLUMN id SET DEFAULT nextval('public.match_id_seq'::regclass);


--
-- Name: user id; Type: DEFAULT; Schema: public; Owner: eanderson
--

ALTER TABLE ONLY public."user" ALTER COLUMN id SET DEFAULT nextval('public.user_id_seq'::regclass);


--
-- Data for Name: exchange; Type: TABLE DATA; Schema: public; Owner: eanderson
--

COPY public.exchange (id, name, created_on, start_date, end_date, host_id, enrollment_status, match_date) FROM stdin;
1	test exchange	2021-12-04 13:37:09.739592	2021-12-01 00:00:00	2021-12-31 00:00:00	1	open	\N
2	my exchange	\N	1970-01-19 15:21:18.742	1970-01-19 15:41:28.342	1	\N	\N
3	my exchange	2021-12-04 23:28:37.801	1970-01-19 15:21:18.742	1970-01-19 15:41:28.342	2	\N	\N
\.


--
-- Data for Name: exchange_has_participant; Type: TABLE DATA; Schema: public; Owner: eanderson
--

COPY public.exchange_has_participant (exchange_id, participant_id) FROM stdin;
1	1
1	2
3	1
3	2
\.


--
-- Data for Name: match; Type: TABLE DATA; Schema: public; Owner: eanderson
--

COPY public.match (id, exchange_id, gifter_id, recipient_id, is_fulfilled, fulfillment_date) FROM stdin;
1	1	1	2	f	\N
2	1	2	1	f	\N
\.


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: eanderson
--

COPY public."user" (id, email, external_id) FROM stdin;
1	foo@bar.com	\N
2	bar@foo.com	\N
\.


--
-- Name: exchange_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eanderson
--

SELECT pg_catalog.setval('public.exchange_id_seq', 3, true);


--
-- Name: match_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eanderson
--

SELECT pg_catalog.setval('public.match_id_seq', 2, true);


--
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: public; Owner: eanderson
--

SELECT pg_catalog.setval('public.user_id_seq', 2, true);


--
-- Name: exchange_has_participant exchange_has_participant_pkey; Type: CONSTRAINT; Schema: public; Owner: eanderson
--

ALTER TABLE ONLY public.exchange_has_participant
    ADD CONSTRAINT exchange_has_participant_pkey PRIMARY KEY (exchange_id, participant_id);


--
-- Name: exchange exchange_pkey; Type: CONSTRAINT; Schema: public; Owner: eanderson
--

ALTER TABLE ONLY public.exchange
    ADD CONSTRAINT exchange_pkey PRIMARY KEY (id);


--
-- Name: match match_pkey; Type: CONSTRAINT; Schema: public; Owner: eanderson
--

ALTER TABLE ONLY public.match
    ADD CONSTRAINT match_pkey PRIMARY KEY (id);


--
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: eanderson
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- Name: match fk_exchange; Type: FK CONSTRAINT; Schema: public; Owner: eanderson
--

ALTER TABLE ONLY public.match
    ADD CONSTRAINT fk_exchange FOREIGN KEY (exchange_id) REFERENCES public.exchange(id);


--
-- Name: exchange_has_participant fk_exchange; Type: FK CONSTRAINT; Schema: public; Owner: eanderson
--

ALTER TABLE ONLY public.exchange_has_participant
    ADD CONSTRAINT fk_exchange FOREIGN KEY (exchange_id) REFERENCES public.exchange(id);


--
-- Name: match fk_gifter; Type: FK CONSTRAINT; Schema: public; Owner: eanderson
--

ALTER TABLE ONLY public.match
    ADD CONSTRAINT fk_gifter FOREIGN KEY (gifter_id) REFERENCES public."user"(id);


--
-- Name: exchange fk_host; Type: FK CONSTRAINT; Schema: public; Owner: eanderson
--

ALTER TABLE ONLY public.exchange
    ADD CONSTRAINT fk_host FOREIGN KEY (host_id) REFERENCES public."user"(id);


--
-- Name: exchange_has_participant fk_participant; Type: FK CONSTRAINT; Schema: public; Owner: eanderson
--

ALTER TABLE ONLY public.exchange_has_participant
    ADD CONSTRAINT fk_participant FOREIGN KEY (participant_id) REFERENCES public."user"(id);


--
-- Name: match fk_recipient; Type: FK CONSTRAINT; Schema: public; Owner: eanderson
--

ALTER TABLE ONLY public.match
    ADD CONSTRAINT fk_recipient FOREIGN KEY (recipient_id) REFERENCES public."user"(id);


--
-- PostgreSQL database dump complete
--

