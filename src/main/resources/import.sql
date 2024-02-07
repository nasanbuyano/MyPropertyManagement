-- credential entity
INSERT INTO credential (email, password) VALUES ('john@example.com', 'password123');
INSERT INTO credential (email, password) VALUES ('jane@example.com', 'password456');
INSERT INTO credential (email, password) VALUES ('michael@example.com', 'password789');
INSERT INTO credential (email, password) VALUES ('emily@example.com', 'password012');
INSERT INTO credential (email, password) VALUES ('david@example.com', 'password345');
INSERT INTO credential (email, password) VALUES ('jennifer@example.com', 'password678');
INSERT INTO credential (email, password) VALUES ('daniel@example.com', 'password901');
INSERT INTO credential (email, password) VALUES ('lisa@example.com', 'password234');
INSERT INTO credential (email, password) VALUES ('matthew@example.com', 'password567');
INSERT INTO credential (email, password) VALUES ('sarah@example.com', 'password890');

-- user entity
INSERT INTO user (credential_id, first_name, last_name, user_type, active) VALUES (1, 'John', 'Doe', 'Customer', true);
INSERT INTO user (credential_id, first_name, last_name, user_type, active) VALUES (2, 'Jane', 'Smith', 'Owner', false);
INSERT INTO user (credential_id, first_name, last_name, user_type, active) VALUES (3, 'Michael', 'Johnson', 'Customer', true);
INSERT INTO user (credential_id, first_name, last_name, user_type, active) VALUES (4, 'Emily', 'Davis', 'Admin', true);
INSERT INTO user (credential_id, first_name, last_name, user_type, active) VALUES (5, 'David', 'Wilson', 'Customer', true);
INSERT INTO user (credential_id, first_name, last_name, user_type, active) VALUES (6, 'Jennifer', 'Brown', 'Owner', true);
INSERT INTO user (credential_id, first_name, last_name, user_type, active) VALUES (7, 'Daniel', 'Miller', 'Admin', true);
INSERT INTO user (credential_id, first_name, last_name, user_type, active) VALUES (8, 'Lisa', 'Moore', 'Customer', true);
INSERT INTO user (credential_id, first_name, last_name, user_type, active) VALUES (9, 'Matthew', 'Taylor', 'Customer', true);
INSERT INTO user (credential_id, first_name, last_name, user_type, active) VALUES (10, 'Sarah', 'Anderson', 'Owner', true);

-- property entity
INSERT INTO property (location, img_url, number_of_rooms, property_home_type, property_sale_type, property_status, user_id) VALUES ('123 Main St', 'https://photos.zillowstatic.com/fp/b2195afdcc450e3fcff59b2886c6a158-p_e.webp', 3, 'Apartment', 'Rent', 'Available', 2);
INSERT INTO property (location, img_url, number_of_rooms, property_home_type, property_sale_type, property_status, user_id) VALUES ('456 Elm St', 'https://www.houseplans.net/uploads/plans/29061/elevations/74288-1200.jpg?v=033023152320', 4, 'Ranch', 'Sell', 'Pending', 2);
INSERT INTO property (location, img_url, number_of_rooms, property_home_type, property_sale_type, property_status, user_id) VALUES ('789 Oak St', 'https://www.houseplans.net/uploads/plans/28652/elevations/72049-1200.jpg?v=010923100215', 2, 'Duplex', 'Rent', 'Available', 2);
INSERT INTO property (location, img_url, number_of_rooms, property_home_type, property_sale_type, property_status, user_id) VALUES ('101 Pine St', 'https://photos.zillowstatic.com/fp/b2195afdcc450e3fcff59b2886c6a158-p_e.webp', 5, 'Apartment', 'Sell', 'Pending', 2);
INSERT INTO property (location, img_url, number_of_rooms, property_home_type, property_sale_type, property_status, user_id) VALUES ('111 Maple St', 'https://www.houseplans.net/uploads/plans/28652/elevations/72049-1200.jpg?v=010923100215', 4, 'Duplex', 'Rent', 'Available', 6);
INSERT INTO property (location, img_url, number_of_rooms, property_home_type, property_sale_type, property_status, user_id) VALUES ('222 Cedar St', 'https://photos.zillowstatic.com/fp/b2195afdcc450e3fcff59b2886c6a158-p_e.webp', 3, 'Congo', 'Rent', 'Available', 6);
INSERT INTO property (location, img_url, number_of_rooms, property_home_type, property_sale_type, property_status, user_id) VALUES ('333 Walnut St', 'https://photos.zillowstatic.com/fp/b2195afdcc450e3fcff59b2886c6a158-p_e.webp', 6, 'Apartment', 'Rent', 'Available', 6);
INSERT INTO property (location, img_url, number_of_rooms, property_home_type, property_sale_type, property_status, user_id) VALUES ('444 Birch St', 'https://photos.zillowstatic.com/fp/b2195afdcc450e3fcff59b2886c6a158-p_e.webp', 5, 'Congo', 'Sell', 'Pending', 10);
INSERT INTO property (location, img_url, number_of_rooms, property_home_type, property_sale_type, property_status, user_id) VALUES ('555 Cherry St', 'https://www.houseplans.net/uploads/plans/29061/elevations/74288-1200.jpg?v=033023152320', 4, 'Ranch', 'Rent', 'Available', 10);
INSERT INTO property (location, img_url, number_of_rooms, property_home_type, property_sale_type, property_status, user_id) VALUES ('666 Elm St', 'https://photos.zillowstatic.com/fp/b2195afdcc450e3fcff59b2886c6a158-p_e.webp', 3, 'TownHouse', 'Sell', 'Pending', 10);

-- offer entity
INSERT INTO offer (price, local_date_time, offer_status_from, offer_status_to, offer_type, offer_from, offer_to, property_id) VALUES (100000, '2023-01-01 12:00:00', 'Rejected', 'Accepted', 'Buy', 1, 6, 5);
INSERT INTO offer (price, local_date_time, offer_status_from, offer_status_to, offer_type, offer_from, offer_to, property_id) VALUES (800, '2023-02-01 12:00:00', 'Accepted', 'Neutral', 'Rent', 3, 10, 8);
INSERT INTO offer (price, local_date_time, offer_status_from, offer_status_to, offer_type, offer_from, offer_to, property_id) VALUES (120000, '2023-03-01 12:00:00', 'Neutral', 'Rejected', 'Buy', 5, 6, 6);
INSERT INTO offer (price, local_date_time, offer_status_from, offer_status_to, offer_type, offer_from, offer_to, property_id) VALUES (900, '2023-04-01 12:00:00', 'Accepted', 'Neutral', 'Rent', 8, 10, 9);
INSERT INTO offer (price, local_date_time, offer_status_from, offer_status_to, offer_type, offer_from, offer_to, property_id) VALUES (150000, '2023-05-01 12:00:00', 'Rejected', 'Neutral', 'Buy', 9, 6, 7);
INSERT INTO offer (price, local_date_time, offer_status_from, offer_status_to, offer_type, offer_from, offer_to, property_id) VALUES (1000, '2023-06-01 12:00:00', 'Accepted', 'Rejected', 'Rent', 1, 10, 10);
INSERT INTO offer (price, local_date_time, offer_status_from, offer_status_to, offer_type, offer_from, offer_to, property_id) VALUES (180000, '2023-07-01 12:00:00', 'Neutral', 'Accepted', 'Buy', 3, 6, 5);
INSERT INTO offer (price, local_date_time, offer_status_from, offer_status_to, offer_type, offer_from, offer_to, property_id) VALUES (1200, '2023-08-01 12:00:00', 'Accepted', 'Neutral', 'Rent', 5, 10, 8);
INSERT INTO offer (price, local_date_time, offer_status_from, offer_status_to, offer_type, offer_from, offer_to, property_id) VALUES (200000, '2023-09-01 12:00:00', 'Rejected', 'Neutral', 'Buy', 8, 6, 6);
INSERT INTO offer (price, local_date_time, offer_status_from, offer_status_to, offer_type, offer_from, offer_to, property_id) VALUES (1500, '2023-10-01 12:00:00', 'Neutral', 'Accepted', 'Rent', 9, 10, 9);
INSERT INTO offer (price, local_date_time, offer_status_from, offer_status_to, offer_type, offer_from, offer_to, property_id) VALUES (250000, '2023-11-01 12:00:00', 'Neutral', 'Neutral', 'Buy', 1, 6, 7);
INSERT INTO offer (price, local_date_time, offer_status_from, offer_status_to, offer_type, offer_from, offer_to, property_id) VALUES (2000, '2023-12-01 12:00:00', 'Neutral', 'Neutral', 'Rent', 9, 10, 10);

-- message entity
INSERT INTO message (from_id, to_id, content, date_time) VALUES (1, 2, 'Hello Jane, I''m interested in your property', '2023-01-05 09:00:00');
INSERT INTO message (from_id, to_id, content, date_time) VALUES (2, 1, 'Hi John, I''d be happy to discuss further. When are you available?', '2023-01-06 10:00:00');
INSERT INTO message (from_id, to_id, content, date_time) VALUES (3, 6, 'Hi Jennifer, I''m interested in your property', '2023-01-07 11:00:00');
INSERT INTO message (from_id, to_id, content, date_time) VALUES (6, 3, 'Hi Michael, I''d be happy to discuss further. When are you available?', '2023-01-08 12:00:00');
INSERT INTO message (from_id, to_id, content, date_time) VALUES (5, 6, 'Hello Jennifer, I''m interested in your property', '2023-01-09 13:00:00');
INSERT INTO message (from_id, to_id, content, date_time) VALUES (6, 5, 'Hi David, I''d be happy to discuss further. When are you available?', '2023-01-10 14:00:00');
INSERT INTO message (from_id, to_id, content, date_time) VALUES (6, 8, 'Hello Lisa, I''m interested in your property', '2023-01-11 15:00:00');
INSERT INTO message (from_id, to_id, content, date_time) VALUES (8, 6, 'Hi Jennifer, I''d be happy to discuss further. When are you available?', '2023-01-12 16:00:00');
INSERT INTO message (from_id, to_id, content, date_time) VALUES (9, 10, 'Hello Sarah, I''m interested in your property', '2023-01-13 17:00:00');
INSERT INTO message (from_id, to_id, content, date_time) VALUES (10, 9, 'Hi Matthew, I''d be happy to discuss further. When are you available?', '2023-01-14 18:00:00');