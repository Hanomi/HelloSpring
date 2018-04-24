DROP TABLE IF EXISTS users;
-- Table: users
CREATE TABLE users(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY ,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL

) ENGINE = InnoDB;

DROP TABLE IF EXISTS roles;
-- Table: roles
CREATE TABLE roles (
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(45) NOT NULL
)
  ENGINE = InnoDB;

DROP TABLE IF EXISTS user_roles;
-- Table mapping user and roles
CREATE TABLE user_roles (
  user_id INT NOT NULL,
  role_id INT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (role_id) REFERENCES roles(id),

  UNIQUE (user_id, role_id)

)
  ENGINE = InnoDB;

-- test data
INSERT INTO users VALUES (1, 'freefpdie', '$2a$11$DmvWJ7c7Cgb8Dp4KuCLQO.Xhv27qC4FmTXv1TUy2cAaFL.21jxVqO');

INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_roles VALUE (1, 2);