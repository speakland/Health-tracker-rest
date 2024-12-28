-- Table: users
CREATE TABLE users (
                       id serial PRIMARY KEY,
                       name VARCHAR (100) NOT NULL,
                       email VARCHAR (255) UNIQUE NOT NULL
);


-- Table: nutrition
CREATE TABLE nutrition (
                           nutrition_id SERIAL PRIMARY KEY,
                        name VARCHAR (100) NOT NULL,
                           user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
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
                          user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                          activity_type VARCHAR(255),
                          calories_burned INTEGER,
                          duration INTERVAL,
                          activity_date DATE NOT NULL
);

-- Table: sleep
CREATE TABLE sleep (
                       sleep_id SERIAL PRIMARY KEY,
                       user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                       sleep_start TIMESTAMP NOT NULL,
                       sleep_end TIMESTAMP NOT NULL,
                       sleep_duration INTERVAL,
                       bedtime_reminder BOOLEAN
);

-- Table: statistics
CREATE TABLE statistics (
                            stat_id SERIAL PRIMARY KEY,
                            user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                            total_sleep_hours DOUBLE PRECISION,
                            average_calories INTEGER,
                            total_activity_hours DOUBLE PRECISION,
                            week_start DATE NOT NULL,
                            week_end DATE NOT NULL
);


