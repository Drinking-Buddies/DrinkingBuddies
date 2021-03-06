-- User Procedures
CALL NewUser("email", "user", "pass", "2002-12-12", "phone", "emer", 1, 2);
CALL NewUser("email1", "user1", "pass1", "2020-12-12", "phone1", "emer1", 3, 4);
SELECT * FROM users;
CALL UserLogin("email", "pass", @ligma);
SELECT @ligma;

-- Event Procedures
CALL NewEvent("2022-05-03 20:29:12", @event1);
SELECT * FROM past_events;
CALL JoinEvent("email", @event1, 69420);
SELECT * FROM drinking_history;

-- Friend Procedures
CALL FriendRequest("email", "email1");
SELECT * FROM friendships;
CALL AcceptFriend("email", "email1");
SELECT * FROM friendships;
