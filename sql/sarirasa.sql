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
  created timestamp without time zone NOT NULL DEFAULT now(),
  last_updated timestamp without time zone NOT NULL DEFAULT now(),
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
  created timestamp without time zone NOT NULL DEFAULT now(),
  last_updated timestamp without time zone NOT NULL DEFAULT now(),
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
  created timestamp without time zone NOT NULL DEFAULT now(),
  last_updated timestamp without time zone NOT NULL DEFAULT now(),
  CONSTRAINT pk_srn_campaign_type PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.srn_campaign_type
  OWNER TO sarirasa;