INSERT INTO users (name, email) VALUES
                                    ('John Doe', 'john.doe@example.com'),
                                    ('Jane Smith', 'jane.smith@example.com'),
                                    ('Mark Johnson', 'mark.johnson@example.com'),
                                    ('Emily Davis', 'emily.davis@example.com');

INSERT INTO nutritions (name, user_id, calories, fat, carbs, protein, water_intake, logged_date) VALUES
                                                                                                     ('Chicken Salad', 1, 300, 10.5, 15.3, 25.1, 0.5, '2024-12-28'),
                                                                                                     ('Pasta Carbonara', 2, 550, 25.0, 60.5, 20.0, 1.2, '2024-12-29'),
                                                                                                     ('Grilled Salmon', 3, 400, 15.2, 0.0, 45.0, 0.8, '2024-12-30'),
                                                                                                     ('Vegetable Stir Fry', 4, 250, 8.0, 40.0, 8.0, 0.6, '2024-12-31');

INSERT INTO activities (user_id, description, calories, duration, started) VALUES
                                                                               (1, 'Running', 300, 45.5, '2024-12-28'),
                                                                               (2, 'Yoga', 150, 60.0, '2024-12-29'),
                                                                               (3, 'Cycling', 400, 90.0, '2024-12-30'),
                                                                               (4, 'Swimming', 350, 30.0, '2024-12-31');

INSERT INTO sleeps (user_id, sleep_start, sleep_end, sleep_duration, bedtime_reminder) VALUES
                                                                                           (1, '22:00', '06:30', 8.5, true),
                                                                                           (2, '23:00', '07:00', 8.0, false),
                                                                                           (3, '21:30', '06:00', 8.5, true),
                                                                                           (4, '22:15', '06:00', 7.75, true);

INSERT INTO statistics (user_id, total_sleep_hours, average_calories, total_activity_hours, week_start, week_end) VALUES
                                                                                                                      (1, 60.0, 2500, 5.0, '2024-12-28', '2024-12-31'),
                                                                                                                      (2, 56.0, 2200, 4.5, '2024-12-29', '2024-12-31'),
                                                                                                                      (3, 63.5, 2300, 6.5, '2024-12-30', '2024-12-31'),
                                                                                                                      (4, 61.0, 2400, 5.2, '2024-12-31', '2025-01-03');
