-- Insert sample data into category
INSERT INTO category (id, description, name) VALUES
                                                 (1, 'Electronic devices and gadgets', 'Electronics'),
                                                 (2, 'Books across various genres', 'Books'),
                                                 (3, 'Apparel and clothing items', 'Clothing'),
                                                 (4, 'Home and kitchen appliances', 'Home Appliances'),
                                                 (5, 'Outdoor and indoor sports gear', 'Sports');

-- Insert sample data into product
INSERT INTO product (id, description, name, available_quantity, price, category_id) VALUES
                                                                                        (1, 'High-performance gaming laptop', 'Gaming Laptop', 10, 1299.99, 1),
                                                                                        (2, 'Latest model smartphone', 'Smartphone X', 25, 899.50, 1),
                                                                                        (3, 'Bestselling mystery novel', 'Mystery Book', 100, 15.99, 2),
                                                                                        (4, 'Men casual T-shirt', 'Casual T-shirt', 50, 12.49, 3),
                                                                                        (5, 'Stainless steel blender', 'Blender Pro', 20, 79.99, 4);
