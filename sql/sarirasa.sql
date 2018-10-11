/*
this is sarirasa database schema
table is already in sequence order to create and execute

database name: sarirasa
database owner and user : sarirasa
database password: 123456
*/

/* create user first if not created */
create user sarirasa with superuser createdb createrole password '123456';

/* create database */
CREATE database sarirasa;

/* create schema tables */

CREATE SEQUENCE public.srn_device_seq
  INCREMENT 1
  MINVALUE 1
  START 10000000;

ALTER TABLE public.srn_device_seq
  OWNER TO sarirasa;

-- Table: public.srn_device
-- DROP TABLE public.srn_device;
CREATE TABLE public.srn_device
(
  id integer NOT NULL DEFAULT nextval('srn_device_seq'::regclass),
  fcm_id text,
  imei text NOT NULL,
  manufacture text,
  model text,
  osversion text,
  created timestamp without time zone NOT NULL DEFAULT current_timestamp,
  last_updated timestamp without time zone NOT NULL DEFAULT current_timestamp,
  CONSTRAINT srn_device_pkey PRIMARY KEY (imei),
  CONSTRAINT srn_device_id_key UNIQUE (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.srn_device
  OWNER TO sarirasa;

CREATE SEQUENCE public.srn_user_email_seq
  INCREMENT 1
  MINVALUE 1
  START 10000000;

-- Table: public.srn_user_email
-- DROP TABLE public.srn_user_email;

CREATE TABLE public.srn_user_email
(
  id integer NOT NULL DEFAULT nextval('srn_user_email_seq'::regclass),
  email text NOT NULL,
  login_type text NOT NULL,
  created timestamp without time zone,
  last_updated timestamp without time zone,
  CONSTRAINT srn_user_email_pkey PRIMARY KEY (email),
  CONSTRAINT srn_user_email_id_key UNIQUE (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.srn_user_email
  OWNER TO sarirasa;


-- Table: public.srn_user_profile
-- DROP TABLE public.srn_user_profile;
CREATE TABLE public.srn_user_profile
(
  user_id integer NOT NULL,
  address text,
  alternate_email text,
  city text,
  full_name text,
  nickname text,
  phone text,
  province text,
  created timestamp without time zone,
  last_updated timestamp without time zone,
  CONSTRAINT srn_user_profile_pkey PRIMARY KEY (user_id),
  CONSTRAINT srn_user_profile_fk_srn_user_email FOREIGN KEY (user_id)
      REFERENCES public.srn_user_email (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.srn_user_profile
  OWNER TO sarirasa;

-- Table: public.srn_user_device_session
-- DROP TABLE public.srn_user_device_session;

CREATE TABLE public.srn_user_device_session
(
  device_id integer NOT NULL,
  session_id character varying(255),
  user_id integer,
  created timestamp without time zone,
  last_updated timestamp without time zone,
  CONSTRAINT srn_user_device_session_pk PRIMARY KEY (device_id),
  CONSTRAINT srn_user_device_session_fk_srn_device FOREIGN KEY (device_id)
      REFERENCES public.srn_device (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.srn_user_device_session
  OWNER TO sarirasa;


CREATE SEQUENCE public.srn_campaign_promo_seq
  INCREMENT 1
  MINVALUE 1
  START 10000000;

-- Table: public.srn_campaign
-- DROP TABLE public.srn_campaign;

CREATE TABLE public.srn_campaign
(
  id integer NOT NULL DEFAULT nextval('srn_campaign_promo_seq'::regclass),
  campaign_type integer DEFAULT 0,
  campaign_name text NOT NULL,
  description text NOT NULL,
  tnc text NOT NULL,
  start_date timestamp without time zone NOT NULL,
  end_date timestamp without time zone NOT NULL,
  is_active boolean DEFAULT false,
  required_points integer NOT NULL DEFAULT 0,
  created timestamp without time zone NOT NULL DEFAULT current_timestamp,
  last_updated timestamp without time zone NOT NULL DEFAULT current_timestamp,
  CONSTRAINT pk_srn_campaign PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.srn_campaign
  OWNER TO sarirasa;

CREATE SEQUENCE public.srn_campaign_type_seq
  INCREMENT 1
  MINVALUE 1
  START 10000000;
-- Table: public.srn_campaign_type
-- DROP TABLE public.srn_campaign_type;

CREATE TABLE public.srn_campaign_type
(
  id integer NOT NULL DEFAULT nextval('srn_campaign_type_seq'::regclass),
  type_name text NOT NULL,
  is_active boolean DEFAULT true,
  created timestamp without time zone NOT NULL DEFAULT current_timestamp,
  last_updated timestamp without time zone NOT NULL DEFAULT current_timestamp,
  CONSTRAINT pk_srn_campaign_type PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.srn_campaign_type
  OWNER TO sarirasa;


CREATE SEQUENCE srn_brand_seq
  INCREMENT 1
  MINVALUE 1
  START 10000000;

-- Table: public.srn_brand
-- DROP TABLE public.srn_brand;

CREATE TABLE public.srn_brand
(
  brand_id integer NOT NULL DEFAULT nextval('srn_brand_seq'::regclass),
  brand_name text NOT NULL,
  brand_image_url text,
  created timestamp without time zone NOT NULL DEFAULT current_timestamp,
  last_updated timestamp without time zone NOT NULL DEFAULT current_timestamp,
  CONSTRAINT srn_brand_pk PRIMARY KEY (brand_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.srn_brand
  OWNER TO sarirasa;

insert into srn_brand values (nextval('srn_brand_seq'),
	'brand1',
	'https://www.detik.com',
	current_timestamp,
	current_timestamp);
insert into srn_brand values (nextval('srn_brand_seq'),
	'brand2',
	'https://www.detik.com',
	current_timestamp,
	current_timestamp);
insert into srn_brand values (nextval('srn_brand_seq'),
	'brand3',
	'https://www.detik.com',
	current_timestamp,
	current_timestamp);

-- Table: public.srn_store
-- DROP TABLE public.srn_store;

CREATE TABLE public.srn_store
(
  store_id integer NOT NULL,
  brand_id integer NOT NULL,
  store_name text NOT NULL,
  store_address text,
  store_city text,
  store_province text,
  created timestamp without time zone NOT NULL DEFAULT current_timestamp,
  last_updated timestamp without time zone NOT NULL DEFAULT current_timestamp,
  CONSTRAINT srn_brand_store_pk PRIMARY KEY (store_id),
  CONSTRAINT srn_store_fk_srn_brand FOREIGN KEY (brand_id)
      REFERENCES public.srn_brand (brand_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.srn_store
  OWNER TO sarirasa;

insert into srn_store values ('11111','10000006','store brand 1','jalan bendungan walahar','jakarta','DKI',current_timestamp,current_timestamp);
insert into srn_store values ('11112','10000006','store brand 1','jalan bendungan walahar','jakarta','DKI',current_timestamp,current_timestamp);
insert into srn_store values ('11113','10000006','store brand 1','jalan bendungan walahar','jakarta','DKI',current_timestamp,current_timestamp);

insert into srn_store values ('11121','10000007','store brand 2','jalan bendungan walahar','jakarta','DKI',current_timestamp,current_timestamp);
insert into srn_store values ('11122','10000007','store brand 2','jalan bendungan walahar','jakarta','DKI',current_timestamp,current_timestamp);
insert into srn_store values ('11123','10000007','store brand 2','jalan bendungan walahar','jakarta','DKI',current_timestamp,current_timestamp);

insert into srn_store values ('11131','10000008','store brand 3','jalan bendungan walahar','jakarta','DKI',current_timestamp,current_timestamp);
insert into srn_store values ('11132','10000008','store brand 3','jalan bendungan walahar','jakarta','DKI',current_timestamp,current_timestamp);
insert into srn_store values ('11133','10000008','store brand 3','jalan bendungan walahar','jakarta','DKI',current_timestamp,current_timestamp);

CREATE SEQUENCE public.srn_point_seq
  INCREMENT 1
  MINVALUE 1
  START 10000000;

-- drop table srn_points
create table srn_points (
  point_id integer not null default nextval('srn_point_seq'::regclass),
  user_id integer not null,
  brand_id integer not null,
  store_id integer not null,
  point_value integer not null,
  point_expired timestamp without time zone,
  created timestamp without time zone NOT NULL DEFAULT current_timestamp,
  last_updated timestamp without time zone NOT NULL DEFAULT current_timestamp,
  constraint srn_points_pk primary key (point_id),
  constraint srn_points_fk_srn_user_profile foreign key (user_id) references srn_user_profile (user_id),
  constraint srn_points_fk_srn_brand foreign key (brand_id) references srn_brand (brand_id),
  constraint srn_points_fk_srn_store foreign key (store_id) references srn_store (store_id)
);

create table srn_voucher
(
  voucher_id text not null,
  brand_id integer not null,
  user_id integer not null,
  claim_timestamp timestamp without time zone,
  voucher_expired timestamp without time zone NOT NULL,
  created timestamp without time zone NOT NULL DEFAULT current_timestamp,
  last_updated timestamp without time zone NOT NULL DEFAULT current_timestamp,
  constraint srn_voucher_pk primary key (voucher_id),
  constraint srn_voucher_fk_srn_brand foreign key (brand_id) references srn_brand (brand_id),
  constraint srn_voucher_fk_srn_user_profile foreign key (user_id) references srn_user_profile (user_id)
);

CREATE SEQUENCE public.srn_voucher_store_seq
  INCREMENT 1
  MINVALUE 1
  START 10000000;

-- drop table srn_voucher_store
create table srn_references_voucher_store
(
  id integer not null default nextval('srn_voucher_store_seq'),
  voucher_id text not null,
  store_id integer not null,
  constraint srn_voucher_store primary key (id),
  constraint srn_voucher_store_fk_srn_voucher foreign key (voucher_id) references srn_voucher (voucher_id),
  constraint srn_voucher_fk_srn_store foreign key (store_id) references srn_store(store_id)
);

