-- Table: users
CREATE TABLE users (
                       user_id SERIAL PRIMARY KEY,
                       username VARCHAR(255) NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table: nutrition
CREATE TABLE nutrition (
                           nutrition_id SERIAL PRIMARY KEY,
                           user_id INTEGER NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
                           calories INTEGER,
                           fat DOUBLE PRECISION,
                           carbs DOUBLE PRECISION,
                           protein DOUBLE PRECISION,
                           water_intake DOUBLE PRECISION,
                           logged_date DATE NOT NULL
);

-- Table: activity
CREATE TABLE activity (
                          activity_id SERIAL PRIMARY KEY,
                          user_id INTEGER NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
                          activity_type VARCHAR(255),
                          calories_burned INTEGER,
                          duration INTERVAL,
                          activity_date DATE NOT NULL
);

-- Table: sleep
CREATE TABLE sleep (
                       sleep_id SERIAL PRIMARY KEY,
                       user_id INTEGER NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
                       sleep_start TIMESTAMP NOT NULL,
                       sleep_end TIMESTAMP NOT NULL,
                       sleep_duration INTERVAL,
                       bedtime_reminder BOOLEAN
);

-- Table: statistics
CREATE TABLE statistics (
                            stat_id SERIAL PRIMARY KEY,
                            user_id INTEGER NOT NULL REFERENCES users(user_id) ON DELETE CASCADE,
                            total_sleep_hours DOUBLE PRECISION,
                            average_calories INTEGER,
                            total_calories INTEGER,
                            total_activity_hours DOUBLE PRECISION,
                            week_start DATE NOT NULL,
                            week_end DATE NOT NULL
);



-- Insert Users
INSERT INTO users (username, email, password)
VALUES
    ('john_doe', 'john.doe@example.com', 'password123'),
    ('jane_smith', 'jane.smith@example.com', 'securepass456');

-- Insert Nutrition Data
INSERT INTO nutrition (user_id, calories, fat, carbs, protein, water_intake, logged_date)
VALUES
    (1, 2000, 70.5, 250.0, 100.0, 2.5, '2024-12-27'),
    (1, 1800, 65.0, 200.0, 90.0, 3.0, '2024-12-28'),
    (2, 1500, 50.0, 180.0, 80.0, 2.0, '2024-12-27'),
    (2, 1600, 55.0, 190.0, 85.0, 2.5, '2024-12-28');

-- Insert Activity Data
INSERT INTO activity (user_id, activity_type, calories_burned, duration, activity_date)
VALUES
    (1, 'Running', 300, '00:30:00', '2024-12-27'),
    (1, 'Cycling', 400, '01:00:00', '2024-12-28'),
    (2, 'Yoga', 150, '00:45:00', '2024-12-27'),
    (2, 'Swimming', 350, '01:15:00', '2024-12-28');

-- Insert Sleep Data
INSERT INTO sleep (user_id, sleep_start, sleep_end, sleep_duration, bedtime_reminder)
VALUES
    (1, '2024-12-26 22:00:00', '2024-12-27 06:00:00', '08:00:00', TRUE),
    (1, '2024-12-27 22:30:00', '2024-12-28 06:30:00', '08:00:00', TRUE),
    (2, '2024-12-26 23:00:00', '2024-12-27 07:00:00', '08:00:00', FALSE),
    (2, '2024-12-27 23:30:00', '2024-12-28 07:30:00', '08:00:00', FALSE);

-- Insert Statistics Data
INSERT INTO statistics (user_id, total_sleep_hours, average_calories, total_calories, total_activity_hours, week_start, week_end)
VALUES
    (1, 16.0, 1900, 3800, 1.5, '2024-12-25', '2024-12-31'),
    (2, 16.0, 1550, 3100, 2.0, '2024-12-25', '2024-12-31');