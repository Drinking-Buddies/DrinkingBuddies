<%@ page language="java" import = "java.util.*,com.drinkingbuddies.drinkingbuddies.classes.*"
    contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="/index.css">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
	
	<title>Profile Page</title>
</head>
<body>
	<div class = "container">
	<%
		User currentUser = (User) request.getAttribute("user");
	%>
		<div class = "navBar">
			<div class = "text-center">
				<div class = "display-inline">
					<h1>DRINKING BUDDIES</h1>
				</div>
			</div>
			<img class = "dbLogo" src = "/DrinkingBuddies_Assets/logo_Nav.png">
			<div class = "mainNav">
				<a href = "/">Home</a>
				<a href = "/profile">Profile</a>
				<a href = "/drink">Drink!</a>
			</div>
			<div class = "toLogin">
				<a href = "/login">login / register</a>
			</div>
		</div>
		<h1 id = "userID">${userID}</h1>
		<button id = "editButton" href = "editProfile.jsp">Edit Profile</button>
		<h2 id = "userName">${userName}</h2>
		<p id = "userBio">${bio}</p>
		<h2 id = "userEmail">${email}</h2>
		<h2 id = "userPhoneNum">${phone}</h2>
		<h2 id = "userDOB">${DOB}</h2>
		<h2>Your Friends: </h2>
		<div id = "friendsList">
			<%
				List<Friend> friendsList;
				friendsList = (List<Friend>) request.getAttribute("friendsList");
				if(friendsList != null && !friendsList.isEmpty())
				{
					for(int i = 0; i < friendsList.size(); i++)
					{
			%>
				<button>
					friendsList.get(i).getUsername();
					friendsList.get(i).getOnlineStatus();
				</button>
			<% 
					}
				}
				
			%>
		</div> 
		<form id = "addFriend" method = "POST">
			<input class = "textBox" type = "text" name = "friendName">
			<button id = "addFriendButton" type = "submit">Add</button>
		</form>
	</div>
</body>
</html>