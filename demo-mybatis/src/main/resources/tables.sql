-- Table: blog

-- DROP TABLE blog;

CREATE TABLE blog
(
  id serial NOT NULL,
  title character varying(255) NOT NULL,
  content character varying(50000) NOT NULL,
  CONSTRAINT blog_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE blog
  OWNER TO postgres;

--Table: author

--DROP TABLE author;

CREATE TABLE author
(
  id serial NOT NULL,
  name character varying(255) NOT NULL,
  brief character varying(500),
  CONSTRAINT author_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE author
  OWNER TO postgres;
