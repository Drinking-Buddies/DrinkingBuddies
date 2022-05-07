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