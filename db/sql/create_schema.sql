CREATE TABLE flights (
                         id SERIAL PRIMARY KEY,
                         city_origin VARCHAR(255) NOT NULL,
                         destination VARCHAR(255) NOT NULL,
                         departure_date DATE NOT NULL,
                         departure_time TIME NOT NULL,
                         price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE hotel (
                       id SERIAL PRIMARY KEY,
                       hotel_name VARCHAR(255) NOT NULL,
                       city VARCHAR(255) NOT NULL,
                       checkin_date DATE NOT NULL,
                       price_per_night DECIMAL(10, 2) NOT NULL
);
