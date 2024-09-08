--DROP TABLE client CASCADE;
--DROP TABLE address;
--DROP TABLE phone;
--DROP TABLE chalet;
--DROP TABLE booking;

CREATE TABLE IF NOT EXISTS client (
    client_id SERIAL PRIMARY KEY,
    RG VARCHAR(50) UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    birthday DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS address (
    address_id SERIAL PRIMARY KEY,
    client_id INT UNIQUE NOT NULL,
    address VARCHAR(255) NOT NULL,
    neighborhood VARCHAR(255),
    city VARCHAR(100) NOT NULL,
    state VARCHAR(50) NOT NULL,
    postal_code VARCHAR(20),
    FOREIGN KEY (client_id) REFERENCES client(client_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS phone (
	phone_id SERIAL PRIMARY KEY,
	client_id INT NOT NULL,
	phone_type VARCHAR(20),
	phone_number VARCHAR(30) NOT NULL,
	FOREIGN KEY (client_id) REFERENCES client(client_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS chalet (
    chalet_id SERIAL PRIMARY KEY,
    chalet_code VARCHAR(50) UNIQUE NOT NULL,
    location VARCHAR(255) NOT NULL,
    capacity INT NOT NULL,
    peak_season_price DECIMAL(10, 2) NOT NULL,
    normal_price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS booking (
    booking_id SERIAL PRIMARY KEY,
    chalet_id INT NOT NULL,
    client_id INT NOT NULL,
    status VARCHAR(15) DEFAULT 'Not available' NOT NULL,
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    number_guests INT NOT NULL,
    discount DECIMAL(5, 2),
    total_price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (chalet_id) REFERENCES chalet(chalet_id),
    FOREIGN KEY (client_id) REFERENCES client(client_id)
);