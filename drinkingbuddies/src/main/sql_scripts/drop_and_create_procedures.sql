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
