CREATE TABLE IF NOT EXISTS employee (
  id  INTEGER NOT NULL AUTO_INCREMENT,
  firstName VARCHAR(500) NOT NULL,
  lastName VARCHAR(500) NOT NULL,
  email VARCHAR(500) NOT NULL,
  phonenumber VARCHAR(500),
  PRIMARY KEY (id)
);