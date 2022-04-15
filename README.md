# DrinkingBuddies
## The repo for a dumb CS201 project
**Members:** Quanyong Bi, Eun Cho, Dongwook Choi, Brandon Gong, Ethan Tat, Zhehao Xu

## Features:
* **Home Page** (This will be the welcoming page for all users, registered or not registered. There will be a button/link that will take users to the login/sign up page where they can register a new account with Drinking Pal or login to an existing account.): 
Graphics that display some of the previous drinking sessions. Your stats, etc...
    * Two branches: Login user and guest 
    * A list of your friends who are currently online (drinking)
    * There will be an "Add Friends" button accessible to users when they're logged in that will create a pop-up where they can search for friends and add them to their friends list
    * Link to create a new drinking game room page
* **Login/Sign Up Page** (This page will allow users to register a new account or sign in to an existing one. They can also sign up and login through Google Sign In if they have a Google account.):
    * This page will allow new users to register for an account and current users to login into their account
    * Required fields for user to sign up for account: name, username, email, password, birthday, phone number, emergency contact number (cannot be your own number), THE USER MUST AGREE TO THE TERMS OF SERVICE.
    * Input sex, weight, height to calculate safe amount to drink
    * There is also an option to use Google Sign In to create/login to an account. (Initially signing in through Google Sign In will create an account associated with the email address of a given Google account, and the user can just sign in through the Google Sign In button to access their account. We may also allow Google Account users to access their account through normal login with email/password by letting them set a password on their personal profile page.)
    * Required fields for user to login: email and password
    * (some questions: should we hash the passwords / emails before we store the stuff)
* **Profile Page** (This page is where the user can access their personal information and change what information can be seen in their  public profile. Other users of Drinking Pal will be able to see a user's public profile.):
Only accessible if logged in
Public profile displays username, name, drinking activity, and email (email can be toggle to be visible or not visible in public profile pages) 
    * Birthday, sex, phone number, and emergency contact number is only visible to the user in a personal information section
    * Displays friends list
    * Menu to edit weight, height, etc (only visible to self)
    * Drinking Page (This page is where a lot of the primary functionality of Drinking Pal takes place. It allows users to create rooms and invite other people into the rooms with a URL. Additionally, users can record their drink consumption, access drinking games, and quit when they can no longer drink anymore. This page will provide a warning to users when it sees that their alcohol intake is reaching dangerous levels.):
    * When you create a room, a link will be generated and all people can access the room by the link. The page displays the room and all the people in it. Also, the amount of their drinks alongside yours. 
    * Allows the user to record and input the amount of drinks 
    * we will support some really basic types of drinks - beer, vodka
    * All people in room can see the amount drank by others
    * Warn the user if the amount of alcohol consumed is considered dangerous
    * Use BAC calculations
    * A button to quit/surrender from drinking to show everyone that you can't drink.
    * A button to play games, which will lead to the Game Page (If we have time to do it) (maybe can lead to an exterior game page.)
* **Game Page** (Users would be redirected to this page from the Drinking page. Here, they can access the drinking games. Once the games are complete, the users will be redirected to the drinking page, which will tell them which users lost the game and thus have to drink.): 
Different games will be on different pages.
    * All pages will return to the drinking page with a list of losers who'll drink.
    * Idea: Dice game, simple card games, etc. (wait for research.)