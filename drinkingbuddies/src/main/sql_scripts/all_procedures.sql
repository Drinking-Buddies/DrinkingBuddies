DROP PROCEDURE if exists NewUser;
DELIMITER $$
CREATE PROCEDURE NewUser (IN email VARCHAR(50),
                          IN username VARCHAR(50),
						  IN pass VARCHAR(50),
                          IN sex VARCHAR(10),
						  IN birthday DATE,
						  IN phone VARCHAR(50),
						  IN emergency_phone VARCHAR(50),
						  IN weight INT UNSIGNED,
                          IN bio VARCHAR(200))
BEGIN
	INSERT INTO Users (email, username, pass, sex, birthday, phone, 
			emergency_phone, weight, bio)
    VALUES (email, username, pass, sex, birthday, phone, 
			emergency_phone, weight, bio);
END$$
DELIMITER ;

DROP PROCEDURE if exists UserExists;
DELIMITER $$
CREATE PROCEDURE UserExists(IN email VARCHAR(50),
						    IN pass VARCHAR(50))
BEGIN
	SELECT COUNT(*)
    FROM Users u
    WHERE u.email = email AND u.pass = pass;
END$$
DELIMITER ;

DROP PROCEDURE if exists NewEvent;
DELIMITER $$
CREATE PROCEDURE NewEvent (IN start_time DATE, IN event_name VARCHAR(50))
BEGIN
	INSERT INTO Past_events (event_name, start_time)
    VALUES (event_name, start_time);
END$$
DELIMITER ;

DROP PROCEDURE if exists JoinEvent;
DELIMITER $$
CREATE PROCEDURE JoinEvent (IN email VARCHAR(50),
							IN event_name VARCHAR(50),
                            IN amount INT)
BEGIN
	IF NOT EXISTS (SELECT event_name FROM drinking_history d WHERE d.email = email and d.event_name = event_name)
	THEN	
		INSERT INTO Drinking_history (email, event_name, amount)
		VALUES (email, event_name, amount);
    END IF;
END$$
DELIMITER ;

DROP PROCEDURE if exists AddDrink;
DELIMITER $$
CREATE PROCEDURE AddDrink (IN email VARCHAR(50), IN event_name VARCHAR(50), IN amount INT)
BEGIN
    UPDATE drinking_history d
    SET d.amount = d.amount + amount
    WHERE d.email = email and d.event_name = event_name;
END$$
DELIMITER ;

DROP PROCEDURE if exists GetAmountDrinked;
DELIMITER $$
CREATE PROCEDURE GetAmountDrinked (IN email VARCHAR(50), IN event_name VARCHAR(50))
BEGIN
    SELECT d.amount FROM drinking_history d
    WHERE d.email = email and d.event_name = event_name;
END$$
DELIMITER ;

DROP PROCEDURE if exists EventExists;
DELIMITER $$
CREATE PROCEDURE EventExists (IN event_name VARCHAR(50))
BEGIN
	SELECT COUNT(*)
    FROM past_events p
    WHERE p.event_name = event_name;
END$$
DELIMITER ;

DROP PROCEDURE if exists GetParticipants;
DELIMITER $$
CREATE PROCEDURE GetParticipants (IN event_name VARCHAR(50))
BEGIN
	SELECT u.email
		FROM Users u
		INNER JOIN drinking_history h
		ON h.email = u.email
		INNER JOIN past_events e
		ON h.event_name = e.event_name
		WHERE e.event_name = event_name;
END$$
DELIMITER ;

DROP PROCEDURE if exists FriendRequest;
DELIMITER $$
CREATE PROCEDURE FriendRequest (IN requester VARCHAR(50),
								IN receiver VARCHAR(50))
BEGIN
	INSERT INTO Friendships (requester, receiver, pending)
    VALUES (requester, receiver, true);
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

DROP PROCEDURE if exists GetFriends;
DELIMITER $$
CREATE PROCEDURE GetFriends (IN email VARCHAR(50))
BEGIN
	SELECT f.receiver
    FROM Friendships f
    WHERE f.requester = email
    UNION
    SELECT f.requester
    FROM Friendships f
    WHERE f.receiver = email;
END$$
DELIMITER ;

DROP PROCEDURE if exists GetPendingFriend;
DELIMITER $$
CREATE PROCEDURE GetPendingFriend (IN email VARCHAR(50))
BEGIN
	SELECT f.receiver
    FROM Friendships f
    WHERE f.requester = email and f.pending = true
    UNION
    SELECT f.requester
    FROM Friendships f
    WHERE f.receiver = email and f.pending = true; 
END$$
DELIMITER ;

DROP PROCEDURE if exists AreFriends;
DELIMITER $$
CREATE PROCEDURE AreFriends (IN email1 VARCHAR(50),
							 IN email2 VARCHAR(50))
BEGIN
	SELECT COUNT(*)
	FROM Friendships f
	WHERE (f.requester = email1 AND f.receiver = email2)
    OR (f.requester = email2 AND f.receiver = email1);
END$$
DELIMITER ;