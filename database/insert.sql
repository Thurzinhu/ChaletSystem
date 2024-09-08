INSERT INTO client (RG, name, birthday) VALUES
('123456789', 'John Doe', '1985-05-15'),
('987654321', 'Jane Smith', '1990-08-22'),
('654321987', 'Robert Johnson', '1975-09-10'),
('789123456', 'Emily Davis', '2000-03-12'),
('321654987', 'Michael Brown', '1995-11-25'),
('456789123', 'Sophia Wilson', '1988-02-27'),
('112233445', 'Chris Evans', '1982-07-14'),
('556677889', 'Jessica Taylor', '1993-10-08'),
('667788990', 'James Anderson', '1978-12-30'),
('778899001', 'Lisa White', '1987-06-18');

INSERT INTO address (client_id, address, neighborhood, city, state, postal_code) VALUES
(1, '123 Main St', 'Downtown', 'New York', 'NY', '10001'),
(2, '456 Oak St', 'Uptown', 'Los Angeles', 'CA', '90001'),
(3, '789 Pine St', 'Suburbia', 'Chicago', 'IL', '60601'),
(4, '101 Maple Ave', 'Eastside', 'Houston', 'TX', '77001'),
(5, '202 Cedar Ave', 'Westside', 'Phoenix', 'AZ', '85001'),
(6, '303 Birch Rd', 'Northside', 'Philadelphia', 'PA', '19019'),
(7, '404 Elm Rd', 'Southside', 'San Antonio', 'TX', '78201'),
(8, '505 Willow St', 'Midtown', 'San Diego', 'CA', '92101'),
(9, '606 Spruce Ln', 'Hilltop', 'Dallas', 'TX', '75201'),
(10, '707 Palm Dr', 'Seaside', 'San Francisco', 'CA', '94101');

INSERT INTO phone (client_id, phone_type, phone_number) VALUES
(1, 'Mobile', '555-1234'),
(2, 'Home', '555-5678'),
(3, 'Work', '555-8765'),
(4, 'Mobile', '555-4321'),
(5, 'Home', '555-8762'),
(6, 'Work', '555-2345'),
(7, 'Mobile', '555-3456'),
(8, 'Home', '555-7654'),
(9, 'Work', '555-6789'),
(10, 'Mobile', '555-9876');

INSERT INTO chalet (chalet_code, location, capacity, peak_season_price, normal_price) VALUES
('C001', 'Mountain', 4, 500.00, 350.00),
('C002', 'Lake', 6, 600.00, 400.00),
('C003', 'Forest', 5, 550.00, 375.00),
('C004', 'Beach', 8, 800.00, 600.00),
('C005', 'Valley', 3, 450.00, 300.00),
('C006', 'Desert', 5, 525.00, 350.00),
('C007', 'City Center', 2, 400.00, 250.00),
('C008', 'Riverside', 7, 650.00, 450.00),
('C009', 'Hilltop', 4, 575.00, 375.00),
('C010', 'Countryside', 6, 700.00, 500.00);

INSERT INTO booking (chalet_id, client_id, status, check_in_date, check_out_date, number_guests, discount, total_price) VALUES
(1, 1, 'Booked', '2024-07-01', '2024-07-10', 4, 5.00, 4500.00),
(2, 2, 'Available', '2024-07-15', '2024-07-20', 6, 10.00, 5400.00),
(3, 3, 'Booked', '2024-08-01', '2024-08-07', 5, 7.50, 3675.00),
(4, 4, 'Booked', '2024-08-10', '2024-08-18', 8, 15.00, 6120.00),
(5, 5, 'Available', '2024-09-01', '2024-09-05', 3, 0.00, 1350.00),
(6, 6, 'Booked', '2024-09-10', '2024-09-15', 5, 12.50, 4580.00),
(7, 7, 'Available', '2024-09-20', '2024-09-25', 2, 5.00, 2375.00),
(8, 8, 'Booked', '2024-10-01', '2024-10-10', 7, 8.00, 6210.00),
(9, 9, 'Booked', '2024-10-15', '2024-10-20', 4, 10.00, 3375.00),
(10, 10, 'Available', '2024-10-25', '2024-11-01', 6, 0.00, 5000.00);