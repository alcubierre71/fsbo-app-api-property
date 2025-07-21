-- data.sql
DELETE FROM valuations;
DELETE FROM properties;

INSERT INTO properties (id, alias, property_type, built_area, bedrooms, bathrooms, floor, condition, description,
                        is_exterior, has_elevator, has_parking, has_storage_room, has_air_conditioning,
                        has_balcony_or_terrace, has_pool, country, region, province, city, district,
                        neighborhood, user_id, created_at)
VALUES
(1, 'Propiedad A', 'Piso', 85, 3, 2, 2, 'Reformado', 'Luminoso y amplio piso céntrico.', true, true, false, false, true, true, false,
 'España', 'Comunidad de Madrid', 'Madrid', 'Madrid', 'Centro', 'Justicia', 'user1', CURRENT_TIMESTAMP),
(2, 'Propiedad B', 'Ático', 70, 2, 1, 5, 'Buen estado', 'Ático con vistas y terraza.', true, true, true, true, true, true, false,
 'España', 'Cataluña', 'Barcelona', 'Barcelona', 'Eixample', 'Dreta Eixample', 'user2', CURRENT_TIMESTAMP),
(3, 'Propiedad C', 'Chalet', 200, 5, 3, 0, 'Nuevo', 'Chalet con jardín y piscina.', true, false, true, true, true, true, true,
 'España', 'Andalucía', 'Sevilla', 'Sevilla', 'Nervión', 'La Buhaira', 'user3', CURRENT_TIMESTAMP),
(4, 'Propiedad D', 'Estudio', 40, 1, 1, 1, 'Necesita reforma', 'Estudio ideal para inversión.', false, false, false, false, false, false, false,
 'España', 'Comunidad Valenciana', 'Valencia', 'Valencia', 'Ciutat Vella', 'El Carmen', 'user4', CURRENT_TIMESTAMP),
(5, 'Propiedad E', 'Dúplex', 120, 4, 2, 3, 'Buen estado', 'Dúplex amplio con parking.', true, true, true, false, false, true, false,
 'España', 'Galicia', 'A Coruña', 'Santiago', 'Casco Histórico', 'Vite', 'user5', CURRENT_TIMESTAMP),
(6, 'Propiedad F', 'Apartamento', 60, 2, 1, 4, 'Reformado', 'Apartamento céntrico y moderno.', true, false, false, false, true, false, false,
 'España', 'País Vasco', 'Bizkaia', 'Bilbao', 'Abando', 'Indautxu', 'user1', CURRENT_TIMESTAMP),
(7, 'Propiedad G', 'Piso', 90, 3, 2, 1, 'Nuevo', 'Piso en zona residencial.', true, true, true, false, false, true, true,
 'España', 'Castilla y León', 'Valladolid', 'Valladolid', 'Centro', 'Rondilla', 'user1', CURRENT_TIMESTAMP);

 INSERT INTO valuations (property_id, user_id, min_sale_price, max_sale_price, min_rental_price, max_rental_price, valuation_date)
VALUES
(1, 'user1', 180000, 210000, 700, 900, '2025-06-01'),
(2, 'user2', 220000, 260000, 850, 1100, '2025-06-01'),
(3, 'user3', 450000, 520000, 1500, 1800, '2025-06-01'),
(4, 'user4', 95000, 110000, 400, 550, '2025-06-01'),
(5, 'user5', 260000, 300000, 1000, 1250, '2025-06-01'),
(6, 'user1', 140000, 165000, 600, 800, '2025-06-01'),
(7, 'user1', 200000, 230000, 750, 950, '2025-06-01');
