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

DROP PROCEDURE if exists UserLogin;
DELIMITER $$
CREATE PROCEDURE UserLogin(IN email VARCHAR(50),
						   IN pass VARCHAR(50),
                           OUT valid BOOL)
BEGIN
	SELECT COUNT(*)
    INTO valid
    FROM Users u
    WHERE u.email = email AND u.pass = pass;
END$$
DELIMITER ;

