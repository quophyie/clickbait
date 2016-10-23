CREATE TABLE IF NOT EXISTS users
(
  id bigint NOT NULL DEFAULT nextval('users_id_seq'::regclass),
  email character varying(300),
  username character varying(300),
  password character varying(4000),
  first_name character varying(400),
  last_name character varying(400),
  gender character varying(1),
  created_date timestamp without time zone DEFAULT now(),
  CONSTRAINT users_pkey PRIMARY KEY (id),
  CONSTRAINT users_gender_check CHECK (gender::text = ANY (ARRAY['M'::character varying, 'F'::character varying, 'O'::character varying]::text[]))
)