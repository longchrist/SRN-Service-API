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

/* sequence */
CREATE SEQUENCE public.srn_device_seq
INCREMENT 1
MINVALUE 1
START 10000000;

CREATE SEQUENCE public.srn_user_email_seq
INCREMENT 1
MINVALUE 1
START 10000000;

CREATE SEQUENCE public.srn_campaign_promo_seq
INCREMENT 1
MINVALUE 1
START 10000000;

CREATE SEQUENCE public.srn_campaign_type_seq
INCREMENT 1
MINVALUE 1
START 10000000;

CREATE SEQUENCE public.srn_point_seq
INCREMENT 1
MINVALUE 1
START 10000000;

CREATE SEQUENCE public.srn_voucher_store_seq
INCREMENT 1
MINVALUE 1
START 10000000;

CREATE SEQUENCE public.srn_campaign_store_seq
INCREMENT 1
MINVALUE 1
START 10000000;

CREATE SEQUENCE public.srn_user_seq
INCREMENT 1
MINVALUE 1
START 10000000;

CREATE SEQUENCE public.srn_user_point_seq
INCREMENT 1
MINVALUE 1
START 10000000;


DROP sequence srn_device_seq;
DROP sequence srn_user_email_seq;
DROP sequence srn_campaign_promo_seq;
DROP sequence srn_campaign_type_seq;
DROP sequence srn_point_seq;
DROP sequence srn_campaign_store;
DROP sequence srn_user_seq;
DROP sequence srn_user_point_seq;

ALTER TABLE public.srn_device_seq
  OWNER TO sarirasa;
ALTER TABLE public.srn_user_email_seq
  OWNER TO sarirasa;
ALTER TABLE public.srn_campaign_promo_seq
  OWNER TO sarirasa;
ALTER TABLE public.srn_campaign_type_seq
  OWNER TO sarirasa;
ALTER TABLE public.srn_point_seq
  OWNER TO sarirasa;
ALTER TABLE public.srn_campaign_store_seq
  OWNER TO sarirasa;
ALTER TABLE public.srn_user_seq
  OWNER TO sarirasa;
ALTER TABLE public.srn_user_point_seq
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

-- Table: public.srn_user_email
-- DROP TABLE public.srn_user_email;

CREATE TABLE public.srn_user_email
(
  id integer NOT NULL DEFAULT nextval('srn_user_email_seq'::regclass),
  email text NOT NULL,
  login_type text NOT NULL,
  created timestamp without time zone not null default current_timestamp,
  last_updated timestamp without time zone not null default current_timestamp,
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
  session_id text,
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


CREATE TABLE public.srn_brand
(
  brand_id text NOT NULL,
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


-- Table: public.srn_campaign
-- DROP TABLE public.srn_campaign;

CREATE TABLE public.srn_campaign
(
  id integer NOT NULL DEFAULT nextval('srn_campaign_promo_seq'::regclass),
  brand_id text not null,
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
  CONSTRAINT pk_srn_campaign PRIMARY KEY (id),
  constraint srn_campaign_fk_srn_brand foreign key (brand_id) REFERENCES srn_brand (brand_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.srn_campaign
  OWNER TO sarirasa;

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

-- Table: public.srn_brand
-- DROP TABLE public.srn_brand;


insert into srn_brand values (nextval('srn_brand_seq'),
	'brand1',
	'https://pbs.twimg.com/profile_images/1415302809/logo-tesate-web_400x400.jpg',
	current_timestamp,
	current_timestamp);
insert into srn_brand values (nextval('srn_brand_seq'),
	'brand2',
	'https://pbs.twimg.com/profile_images/524759823372386304/9Y4nmrAS_400x400.jpeg',
	current_timestamp,
	current_timestamp);
insert into srn_brand values (nextval('srn_brand_seq'),
	'brand3',
	'https://pbs.twimg.com/profile_images/882062826138882049/fY1gM_8X_400x400.jpg',
	current_timestamp,
	current_timestamp);
insert into srn_brand values (nextval('srn_brand_seq'),
	'brand4',
	'http://www.binuscareer.com/Events/2008/06/2008060021/logo1.JPG',
	current_timestamp,
	current_timestamp);

-- Table: public.srn_store
-- DROP TABLE public.srn_store;

CREATE TABLE public.srn_store
(
  store_id text NOT NULL,
  brand_id text NOT NULL,
  store_name text NOT NULL,
  store_address text,
  store_city text,
  store_province text,
  store_latitude real,
  store_longitude real,
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

insert into srn_store values ('11111','10000000','store brand 1','jalan bendungan walahar','jakarta','DKI',current_timestamp,current_timestamp);
insert into srn_store values ('11112','10000000','store brand 1','jalan bendungan walahar','jakarta','DKI',current_timestamp,current_timestamp);
insert into srn_store values ('11113','10000000','store brand 1','jalan bendungan walahar','jakarta','DKI',current_timestamp,current_timestamp);

insert into srn_store values ('11121','10000001','store brand 2','jalan bendungan walahar','jakarta','DKI',current_timestamp,current_timestamp);
insert into srn_store values ('11122','10000001','store brand 2','jalan bendungan walahar','jakarta','DKI',current_timestamp,current_timestamp);
insert into srn_store values ('11123','10000001','store brand 2','jalan bendungan walahar','jakarta','DKI',current_timestamp,current_timestamp);

insert into srn_store values ('11131','10000002','store brand 3','jalan bendungan walahar','jakarta','DKI',current_timestamp,current_timestamp);
insert into srn_store values ('11132','10000002','store brand 3','jalan bendungan walahar','jakarta','DKI',current_timestamp,current_timestamp);
insert into srn_store values ('11133','10000002','store brand 3','jalan bendungan walahar','jakarta','DKI',current_timestamp,current_timestamp);

insert into srn_store values ('11141','10000003','store brand 4','jalan bendungan walahar','jakarta','DKI',current_timestamp,current_timestamp);
insert into srn_store values ('11142','10000003','store brand 4','jalan bendungan walahar','jakarta','DKI',current_timestamp,current_timestamp);
insert into srn_store values ('11143','10000003','store brand 4','jalan bendungan walahar','jakarta','DKI',current_timestamp,current_timestamp);

-- drop table srn_points
create table srn_points (
  point_id integer not null default nextval('srn_point_seq'::regclass),
  user_id integer not null,
  brand_id text not null,
  store_id text not null,
  point_value integer not null,
  point_expired timestamp without time zone,
  created timestamp without time zone NOT NULL DEFAULT current_timestamp,
  last_updated timestamp without time zone NOT NULL DEFAULT current_timestamp,
  constraint srn_points_pk primary key (point_id),
  constraint srn_points_fk_srn_user_profile foreign key (user_id) references srn_user_profile (user_id),
  constraint srn_points_fk_srn_brand foreign key (brand_id) references srn_brand (brand_id),
  constraint srn_points_fk_srn_store foreign key (store_id) references srn_store (store_id)
)WITH (
  OIDS=FALSE
);
ALTER TABLE public.srn_points
  OWNER TO sarirasa;

-- Table: public.srn_voucher_campaign
-- DROP TABLE public.srn_voucher_campaign;

CREATE TABLE public.srn_voucher_campaign
(
  campaign_id integer NOT NULL,
  voucher_id text NOT NULL,
  user_id integer,
  claim_timestamp timestamp without time zone,
  voucher_expired timestamp without time zone NOT NULL,
  created timestamp without time zone NOT NULL DEFAULT current_timestamp,
  last_updated timestamp without time zone NOT NULL DEFAULT current_timestamp,
  CONSTRAINT srn_voucher_campaign_pk PRIMARY KEY (campaign_id, voucher_id),
  CONSTRAINT srn_voucher_campaign_fk_srn_campaign FOREIGN KEY (campaign_id)
      REFERENCES public.srn_campaign (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT srn_voucher_campaign_voucher_id_key UNIQUE (voucher_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.srn_voucher_campaign
  OWNER TO sarirasa;


-- drop table srn_campaign_store;
create table srn_campaign_store
(
  id integer not null default nextval('srn_campaign_store_seq'),
  campaign_id integer not null,
  store_id text not null,
  constraint srn_campaign_store_pk primary key (id),
  constraint srn_campaign_store_fk_srn_store foreign key (store_id) references srn_store(store_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.srn_campaign_store
  OWNER TO sarirasa;

