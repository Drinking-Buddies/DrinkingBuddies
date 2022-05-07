<%@ page language="java" import = "java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<HTML>
<head>
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
	<title>Home Page</title>
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
		<h1>Welcome ${userID}!</h1>
		<h2>Here's your stats from the past few days: </h2>
		<div id = "drinkingHistory">
			<%
				List<Date> drinkingHistory;
				drinkingHistory = (List<Date>) request.getAttribute("drinkingHistory");
				if(drinkingHistory != null && !drinkingHistory.isEmpty())
				{
					for(int i = 0; i < drinkingHistory.size(); i++)
					{
			%>
					<button>
						drinkingHistory.get(i).getDate();
					</button>
			<% 
					}
				}
			
			%>
		</div>
		<h2>Friends currently online: </h2>
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
	</div>
</body>

</HTML>