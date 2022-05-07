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