INSERT INTO ut_utilisateur(version,id , email, firstname, lastname, leavingdatetime, creatingdatetime, login,password,enabled) VALUES (0, nextval('ut_utilisateur_sequence'),'j2@v.com','nom2','prenom2',LOCALTIMESTAMP,LOCALTIMESTAMP,'admin','koala',true);
INSERT INTO ut_utilisateur(version,id , email, firstname, lastname, leavingdatetime, creatingdatetime, login,password,enabled) VALUES (0, nextval('ut_utilisateur_sequence'),'j2@v.com','nom22','prenéom2',LOCALTIMESTAMP,LOCALTIMESTAMP,'admin','koala',true);
INSERT INTO ut_personne(version,id , nom,prenom) VALUES (0, nextval('ut_personne_sequence'),'Ema','Nolia');
INSERT INTO ut_personne(version,id , nom,prenom) VALUES (0, nextval('ut_personne_sequence'),'Larry','Bambelle');
INSERT INTO ut_personne(version,id , nom,prenom) VALUES (0, nextval('ut_personne_sequence'),'Jerry','Kan');