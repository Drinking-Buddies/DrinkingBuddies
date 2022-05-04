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

DROP PROCEDURE if exists NewUser;
DELIMITER $$
CREATE PROCEDURE NewUser (IN email VARCHAR(50),
                          IN username VARCHAR(50),
						  IN pass VARCHAR(50),
						  IN birthday DATE,
						  IN phone VARCHAR(50),
						  IN emergency_phone VARCHAR(50),
						  IN weight INT UNSIGNED,
						  IN height INT UNSIGNED)
BEGIN
	INSERT INTO Users (email, username, pass, birthday, phone, 
			emergency_phone, weight, height)
    VALUES (email, username, pass, birthday, phone, 
			emergency_phone, weight, height);
END$$
DELIMITER ;

DROP PROCEDURE if exists NewEvent;
DELIMITER $$
CREATE PROCEDURE NewEvent (IN start_time DATETIME, OUT id INT)
BEGIN
	INSERT INTO Past_events (start_time)
    VALUES (start_time);
    SET id = LAST_INSERT_ID();
END$$
DELIMITER ;

DROP PROCEDURE if exists JoinEvent;
DELIMITER $$
CREATE PROCEDURE JoinEvent (IN email VARCHAR(50),
							IN event_id VARCHAR(50),
                            IN amount INT)
BEGIN
	INSERT INTO Drinking_history (email, event_id, amount)
    VALUES (email, event_id, amount);
END$$
DELIMITER ;

DROP PROCEDURE if exists FriendRequest;
DELIMITER $$
CREATE PROCEDURE FriendRequest (IN requester VARCHAR(50),
								IN receiver VARCHAR(50))
BEGIN
	INSERT INTO Friendships (requester, receiver)
    VALUES (requester, receiver);
END$$
DELIMITER ;

DROP PROCEDURE if exists AcceptFriend;
DELIMITER $$
CREATE PROCEDURE AcceptFriend (IN requester VARCHAR(50),
							   IN receiver VARCHAR(50))
BEGIN
	UPDATE Friendships f
    SET pending = FALSE
    WHERE f.requester = requester AND f.receiver = receiver;
END$$
DELIMITER ;
