-- remove the drop stuff for final script
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
    height INT UNSIGNED
);

CREATE TABLE Past_events (
	event_id INT AUTO_INCREMENT PRIMARY KEY,
    start_time DATETIME NOT NULL DEFAULT NOW()
);

CREATE TABLE Drinking_history (
	email VARCHAR(50) NOT NULL,
    event_id INT NOT NULL,
    amount INT,
	FOREIGN KEY (email) REFERENCES Users(email),
    FOREIGN KEY (event_id) REFERENCES Past_events(event_id)
);

CREATE TABLE Friendships (
	requester VARCHAR(50) NOT NULL,
    receiver VARCHAR(50) NOT NULL,
    pending BOOL NOT NULL DEFAULT TRUE,
    FOREIGN KEY (requester) REFERENCES Users(email),
    FOREIGN KEY (receiver) REFERENCES Users(email)
);

