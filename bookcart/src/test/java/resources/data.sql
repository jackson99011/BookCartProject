-- user
INSERT INTO user (email, password, created_date, last_modified_date) VALUES ('user1@gmail.com', '202cb962ac59075b964b07152d234b70', '2022-06-30 10:30:00', '2022-06-30 10:30:00');
INSERT INTO user (email, password, created_date, last_modified_date) VALUES ('user2@gmail.com', '202cb962ac59075b964b07152d234b70', '2022-06-30 10:40:00', '2022-06-30 10:40:00');

-- order, order_item
INSERT INTO `order` (user_id, total_amount, created_date, last_modified_date) VALUES (1, 500690, '2022-06-30 11:10:00', '2022-06-30 11:10:00');
INSERT INTO order_item (order_id, product_id, quantity, amount) VALUES (1, 1, 3, 90);
INSERT INTO order_item (order_id, product_id, quantity, amount) VALUES (1, 2, 2, 600);
INSERT INTO order_item (order_id, product_id, quantity, amount) VALUES (1, 5, 1, 500000);

INSERT INTO `order` (user_id, total_amount, created_date, last_modified_date) VALUES (1, 100000, '2022-06-30 12:03:00', '2022-06-30 12:03:00');
INSERT INTO order_item (order_id, product_id, quantity, amount) VALUES (2, 4, 1, 100000);
