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
		<div class = "navBar">
			<div class = "text-center">
				<div class = "display-inline">
					<h1>DRINKING BUDDIES</h1>
				</div>
			</div>
			<img class = "dbLogo" src = "/DrinkingBuddies_Assets/logo_Nav.png">
			<div class = "mainNav">
				<a href = "/home">Home</a>
				<a href = "/profile">Profile</a>
				<a href = "/drink">Drink!</a>
			</div>
			<div class = "toLogin">
				<a href = "/login">login / register</a>
			</div>
		</div>


		<div class="profilePage" style="width:100%;height:95%;margin-left: 60px; padding-top:20px">
			<div class="greeting">

			<div class="profileUser"><h1>${userName}'s profile</h1></div>
		</div>

		<h2 style="margin-bottom: 0px;margin-left: 5px">BIO:</h2>
		<div id="bioBlock">
			<p id = "userBio">${bio}</p>
		</div>
		<div class="userInfo">
			<div>
				<h2 id = "userEmail">Email: ${email}</h2>
				<h2 id = "userPhoneNum">Phone: ${phone}</h2>
				<h2 id = "userDOB">DOB: ${DOB}</h2>
			</div>
			<div>
				<h2 id="userWeight">Weight: ${weight}</h2>
				<h2 id="userGender">Gender: ${gender}</h2>
			</div>


			<div class="editProfileButton">
				<a href = "/editProfile" id="editProfile">Edit Profile</a>
			</div>
		</div>

		<div class="friendblock">
			<div>
				<h2>Your Friends: </h2>
				<div id="friendListBox">
					<%
						LinkedList<String> friends = (LinkedList<String>) request.getAttribute("friends");
					%>
				</div>
			</div>

			<form id = "addFriend" method = "POST">
				<p id="requestMsg">${msg}
				</p>
				<input class = "textBox" type = "text" name = "userEmail" style="color:rgb(50,50,50)" placeholder="Enter your friend's email...">
				<div id="addFriendButton">
					<button type = "submit" style="background:none;border:none;font-family: 'Gill Sans', sans-serif;
					font-size: medium">
						Send Friend Request</button>
				</div>
			</form>
		</div>

		<div id = "friendsList">
			<%--
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
			--%>
		</div>


	</div></div>
</body>
</html>