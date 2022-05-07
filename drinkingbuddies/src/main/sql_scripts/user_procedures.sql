DROP PROCEDURE if exists NewUser;
DELIMITER $$
CREATE PROCEDURE NewUser (IN email VARCHAR(50),
                          IN username VARCHAR(50),
						  IN pass VARCHAR(50),
						  IN birthday DATE,
						  IN phone VARCHAR(50),
						  IN emergency_phone VARCHAR(50),
						  IN weight INT UNSIGNED,
                          IN bio VARCHAR(200))
BEGIN
	INSERT INTO Users (email, username, pass, birthday, phone, 
			emergency_phone, weight, bio)
    VALUES (email, username, pass, birthday, phone, 
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

