DROP TABLE IF EXISTS users;
CREATE TABLE users (
   id UUID PRIMARY KEY,
   name VARCHAR(50) NOT NULL,
   email VARCHAR(50) NOT NULL,
   password VARCHAR(100) NOT NULL,
   created TIMESTAMP NOT NULL,
   modified TIMESTAMP,
   last_login TIMESTAMP NOT NULL,
   token VARCHAR(300),
   is_active BOOLEAN NOT NULL

);

DROP TABLE IF EXISTS phones;
CREATE TABLE phones(
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id UUID NOT NULL,
    number VARCHAR(25) NOT NULL,
    city_code VARCHAR(3) NOT NULL,
    country_code varchar(3) NOT NULL,
    created TIMESTAMP NOT NULL,
    modified TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);


INSERT INTO users (id, name, email, password, last_login, token, is_active,created, modified)
VALUES('1ac3b15c-3cca-425e-9c4a-22b869aea9c1', 'Byron Delgado', 'bdelgado@gmail.com', '$2a$10$6nIQquStdc8IyKMIZ.rvaeoxrBGpYmQbjvQYYKrNa6tZeh.OiEsgy', '2023-06-30T10:00:00.123456789', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsdWNob0BnbWFpbC5jb20iLCJyb2xlcyI6W3siYXV0aG9yaXR5IjoiUk9MRV9BRE1JTiJ9XSwiaWF0IjoxNjMyNzE1NjQ0LCJleHAiOjE2MzI3MTYyNDR9.tCqhdF_u9fsr-C9YMgfM8J-bcL-CJTIgDzuvpNI5MII', true,'2023-06-30T10:00:00', null);

INSERT INTO phones (user_id, number, city_code, country_code,created, modified)
VALUES
('1ac3b15c-3cca-425e-9c4a-22b869aea9c1', '89982509', 'M', 'NI','2023-06-30T10:00:00', null),
('1ac3b15c-3cca-425e-9c4a-22b869aea9c1', '23349865', 'M', 'NI','2023-06-30T10:00:00', null);
