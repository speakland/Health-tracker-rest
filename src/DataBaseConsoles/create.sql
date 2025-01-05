-- Table: users
CREATE TABLE users
(
    id    serial PRIMARY KEY,
    name  VARCHAR(100)        NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);


-- Table: nutrition
CREATE TABLE nutritions
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(100) NOT NULL,
    user_id      INTEGER      NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    calories     INTEGER,
    fat          FLOAT,
    carbs        FLOAT,
    protein      FLOAT,
    water_intake FLOAT,
    logged_date  DATE         NOT NULL
);

-- Table: activity
CREATE TABLE activities
(
    id          SERIAL PRIMARY KEY,
    user_id     INTEGER NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    description VARCHAR(255),
    calories    INTEGER,
    duration    FLOAT,
    started     DATE    NOT NULL
);


-- Table: sleep
CREATE TABLE sleeps
(
    id               SERIAL PRIMARY KEY,
    user_id          INTEGER NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    sleep_start      DATE,
    sleep_end        DATE,
    sleep_duration   FLOAT,
    bedtime_reminder BOOLEAN
);

-- Table: statistics
CREATE TABLE statistics
(
    id                   SERIAL PRIMARY KEY,
    user_id              INTEGER NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    total_sleep_hours    FLOAT,
    average_calories     INTEGER,
    total_activity_hours FLOAT,
    week_start           VARCHAR(255),
    week_end             VARCHAR(255)
);

