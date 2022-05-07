DROP DATABASE IF EXISTS buddy;
CREATE DATABASE buddy;

USE buddy;

DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Drinking_history;
DROP TABLE IF EXISTS Past_events;
DROP TABLE IF EXISTS Friendships;

CREATE TABLE Users (
	email VARCHAR(50) PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    pass VARCHAR(50) NOT NULL,
    birthday DATE,
    phone VARCHAR(50),
    emergency_phone VARCHAR(50),
    weight INT UNSIGNED,
    bio VARCHAR(200)
);

CREATE TABLE Past_events (
    event_name VARCHAR(50) NOT NULL PRIMARY KEY,
    start_time DATETIME NOT NULL DEFAULT NOW()
);

CREATE TABLE Drinking_history (
	email VARCHAR(50) NOT NULL,
    event_name VARCHAR(50) NOT NULL,
    amount INT,
    FOREIGN KEY (event_name) REFERENCES Past_events(event_name),
	FOREIGN KEY (email) REFERENCES Users(email)
);

CREATE TABLE Friendships (
	requester VARCHAR(50) NOT NULL,
    receiver VARCHAR(50) NOT NULL,
    pending BOOL NOT NULL DEFAULT TRUE,
    FOREIGN KEY (requester) REFERENCES Users(email),
    FOREIGN KEY (receiver) REFERENCES Users(email)
);

-- CREATE TABLE DrinkLobbies (
-- 	lobbyName VARCHAR(50) NOT NULL PRIMARY KEY,
--     event_id INT AUTO_INCREMENT,
--     email_p1 VARCHAR(50),
--     email_p2 VARCHAR(50),
--     email_p3 VARCHAR(50),
--     email_p4 VARCHAR(50),
--     email_p5 VARCHAR(50),
--     email_p6 VARCHAR(50),
--     email_p7 VARCHAR(50),
--     email_p8 VARCHAR(50),
--     FOREIGN KEY (email_p1) REFERENCES Users(email),
--     FOREIGN KEY (email_p2) REFERENCES Users(email),
--     FOREIGN KEY (email_p3) REFERENCES Users(email),
--     FOREIGN KEY (email_p4) REFERENCES Users(email),
--     FOREIGN KEY (email_p5) REFERENCES Users(email),
--     FOREIGN KEY (email_p6) REFERENCES Users(email),
--     FOREIGN KEY (email_p7) REFERENCES Users(email),
--     FOREIGN KEY (email_p8) REFERENCES Users(email)
-- );

