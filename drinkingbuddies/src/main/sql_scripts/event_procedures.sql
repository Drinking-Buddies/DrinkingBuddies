DROP PROCEDURE if exists NewEvent;
DELIMITER $$
CREATE PROCEDURE NewEvent (IN start_time DATETIME, IN event_name VARCHAR(50))
BEGIN
	INSERT INTO Past_events (start_time, event_name)
    VALUES (start_time, event_name);
    SELECT LAST_INSERT_ID();
END$$
DELIMITER ;

DROP PROCEDURE if exists JoinEvent;
DELIMITER $$
CREATE PROCEDURE JoinEvent (IN email VARCHAR(50),
							IN event_id INT,
                            IN amount INT)
BEGIN
	INSERT INTO Drinking_history (email, event_id, amount)
    VALUES (email, event_id, amount);
END$$
DELIMITER ;

DROP PROCEDURE if exists GetParticipants;
DELIMITER $$
CREATE PROCEDURE GetParticipants (IN event_id INT)
BEGIN
	SELECT u.email
		FROM Users u
		INNER JOIN drinking_history h
		ON h.email = u.email
		INNER JOIN past_events e
		ON h.event_id = e.event_id
		WHERE e.event_id = event_id;
END$$
DELIMITER ;