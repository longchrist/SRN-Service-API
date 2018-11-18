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

CREATE SEQUENCE public.srn_campaign_detail_seq
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

CREATE SEQUENCE public.srn_voucher_campaign_seq
INCREMENT 1
MINVALUE 1
START 10000000;

CREATE SEQUENCE public.srn_voucher_campaign_detail_seq
INCREMENT 1
MINVALUE 1
START 10000000;

CREATE SEQUENCE public.srn_campaign_redeem_seq
INCREMENT 1
MINVALUE 1
START 10000000;

CREATE SEQUENCE public.srn_campaign_claim_seq
INCREMENT 1
MINVALUE 1
START 10000000;

/*
DROP sequence srn_device_seq;
DROP sequence srn_user_email_seq;
DROP sequence srn_campaign_promo_seq;
DROP sequence srn_campaign_type_seq;
DROP sequence srn_point_seq;
DROP sequence srn_campaign_store;
DROP sequence srn_user_seq;
DROP sequence srn_user_point_seq;
*/

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

-- Table: public.srn_store
-- DROP TABLE public.srn_store;
-- COPY srn_store(store_id, brand_id, store_name, store_address, store_city, store_phone, store_seating, store_province, store_latitude, store_longitude)
-- FROM '/var/lib/postgresql/srn_store_data.csv' DELIMITER ',' CSV HEADER;

CREATE TABLE public.srn_store
(
  store_id text NOT NULL,
  brand_id text NOT NULL,
  store_name text NOT NULL,
  store_address text,
  store_city text,
  store_phone text,
  store_province text,
  store_seating integer default 0,
  store_operational_hours text,
  store_latitude float,
  store_longitude float,
  created timestamp without time zone NOT NULL DEFAULT current_timestamp,
  last_updated timestamp without time zone NOT NULL DEFAULT current_timestamp,
  CONSTRAINT srn_brand_store_pk PRIMARY KEY (store_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.srn_store
  OWNER TO sarirasa;

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
  voucher_campaign_id integer not null default nextval('srn_voucher_campaign_seq'::regclass),
  voucher_campaign_name text not null, 
  voucher_expired timestamp without time zone NOT NULL,
  created timestamp without time zone NOT NULL DEFAULT current_timestamp,
  last_updated timestamp without time zone NOT NULL DEFAULT current_timestamp,
  CONSTRAINT srn_voucher_campaign_pk PRIMARY KEY (voucher_campaign_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.srn_voucher_campaign
  OWNER TO sarirasa;

-- drop table srn_voucher_campaign_detail
create table srn_voucher_campaign_detail (
   voucher_campaign_detail_id integer not null default nextval('srn_voucher_campaign_detail_seq'::regclass),
   voucher_campaign_id integer not null,
   voucher_code text unique not null,
   created timestamp without time zone NOT NULL DEFAULT current_timestamp,
   last_updated timestamp without time zone NOT NULL DEFAULT current_timestamp,
   constraint srn_voucher_campaign_detail_pk primary key (voucher_campaign_detail_id),
   constraint srn_voucher_campaign_detail_fk_srn_voucher_campaign foreign key (voucher_campaign_id) references srn_voucher_campaign (voucher_campaign_id)
)

-- drop table srn_campaign_detail;
create table srn_campaign_detail
(
  id integer not null default nextval('srn_campaign_detail_seq'),
  campaign_id integer not null,
  store_id text not null,
  voucher_campaign_id integer not null,
  constraint srn_campaign_detail_pk primary key (id),
  constraint srn_campaign_detail_fk_srn_store foreign key (store_id) references srn_store(store_id),
  constraint srn_campaign_detail_fk_srn_campaign foreign key (campaign_id) references srn_campaign (id),
  constraint srn_campaign_detail_fk_srn_voucher_campaign foreign key (voucher_campaign_id) references srn_voucher_campaign (voucher_campaign_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.srn_campaign_detail
  OWNER TO sarirasa;

create unique index idx_srn_campaign_detail on srn_campaign_detail (campaign_id, store_id, voucher_campaign_id);

--drop table srn_campaign_redeem
create table srn_campaign_redeem (
  redeem_id integer not null default nextval('srn_campaign_redeem_seq'),
  user_id integer not null,
  voucher_code text not null,
  redeem_date timestamp without time zone NOT NULL DEFAULT current_timestamp,
  created timestamp without time zone NOT NULL DEFAULT current_timestamp,
  last_updated timestamp without time zone NOT NULL DEFAULT current_timestamp,
  constraint srn_campaign_redeem_pk primary key (redeem_id),
  constraint srn_campaign_redeem_fk_srn_voucher_campaign_detail foreign key (voucher_code) references srn_voucher_campaign_detail (voucher_code),
  constraint srn_campaign_redeem_fk_srn_user_profile foreign key (user_id) references srn_user_profile (user_id)
);
create index idx_srn_campaign_redeem on srn_campaign_redeem(user_id);

--drop table srn_campaign_claim
create table srn_campaign_claim (
  claim_id integer not null default nextval('srn_campaign_claim_seq'),
  user_id integer not null,
  store_id text not null,
  claim_date timestamp without time zone NOT NULL,
  created timestamp without time zone NOT NULL DEFAULT current_timestamp,
  last_updated timestamp without time zone NOT NULL DEFAULT current_timestamp,
  constraint srn_campaign_claim_pk primary key (claim_id),
  constraint srn_campaign_claim_fk_srn_store foreign key (store_id) references srn_store (store_id),
  constraint srn_campaign_claim_fk_srn_user_profile foreign key (user_id) references srn_user_profile (user_id)
);
create index idx_srn_campaign_claim on srn_campaign_claim(user_id, store_id);


select * from srn_campaign_detail scd join srn_campaign sc on scd.campaign_id = sc.id
left join srn_voucher_campaign svc on svc.voucher_campaign_id = scd.voucher_campaign_id
left join srn_voucher_campaign_detail svcd on svc.voucher_campaign_id = svcd.voucher_campaign_id
where scd.store_id = 'C05'

