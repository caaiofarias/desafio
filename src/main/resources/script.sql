CREATE TABLE IF NOT EXISTS USER 
(id INTEGER not NULL AUTO_INCREMENT, 
name VARCHAR(255), 
email VARCHAR(255) unique,  
password VARCHAR(255), 
token VARCHAR(255),
PRIMARY KEY ( id ), 
);

CREATE TABLE IF NOT EXISTS PHONE
(id INTEGER not NULL AUTO_INCREMENT,
ddd INTEGER,
fk INTEGER, 
number VARCHAR(9),
type VARCHAR(255), 
PRIMARY KEY ( id ), 
FOREIGN KEY(fk) REFERENCES USER(id)
);


INSERT INTO USER (name, email, password)
VALUES(
'caio',
'caio@farias.com',
'admin'
);


