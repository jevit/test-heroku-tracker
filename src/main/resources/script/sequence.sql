
DROP SEQUENCE  IF EXISTS CustomGenerator;
CREATE SEQUENCE CustomGenerator  INCREMENT BY 1  MINVALUE 1  MAXVALUE 99999999 START WITH 1 ;
ALTER TABLE CustomGenerator  OWNER TO jive;
--
--DROP SEQUENCE  IF EXISTS ut_role_sequence;
--CREATE SEQUENCE ut_role_sequence  INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1  CACHE 1;
--ALTER TABLE ut_role_sequence  OWNER TO postgres;
--
--DROP SEQUENCE  IF EXISTS pa_parcours_sequence;
--CREATE SEQUENCE pa_parcours_sequence  INCREMENT 1  MINVALUE 1  MAXVALUE 9223372036854775807  START 1  CACHE 1;
--ALTER TABLE pa_parcours_sequence  OWNER TO postgres;
  
DROP SEQUENCE  IF EXISTS ut_utilisateur_sequence;
CREATE SEQUENCE ut_utilisateur_sequence  INCREMENT BY 1  MINVALUE 1  MAXVALUE 99999999  START WITH 1 ;
ALTER TABLE ut_utilisateur_sequence  OWNER TO jive;