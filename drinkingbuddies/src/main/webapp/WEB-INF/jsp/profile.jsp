<%@ page language="java" import = "java.util.*,com.drinkingbuddies.drinkingbuddies.classes.*"
    contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.drinkingbuddies.drinkingbuddies.controller.Util.dbUtility" %>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="/index.css">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
	<title>Profile Page</title>
	
	<%
		Cookie[] cookies = null;
		cookies = request.getCookies();
		boolean login = false;
		String name = "Guest";
		if (cookies != null){
			for (int i = 0; i < cookies.length; i++){
				if (cookies[i].getName().equals("userName")){
					name = cookies[i].getValue();
					login = true;
				}
			}
		}
	%>
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
				<%
					if (!login) {
						out.println("<a href = '/login'>login / register</a>");
					} else {
						out.println("<a href = '/home' onclick = 'LogOut();'>logout</a>");
					}
				%>
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
				<h2 id="userWeight">Weight: ${weight}</h2>
				<h2 id = "userPhoneNum">Phone: ${phone}</h2>
				<h2 id = "userDOB">DOB: ${DOB}</h2>
			</div>
			<div style="margin-left:40px">
				<h2 id ="userEmail">Email: ${email}</h2>
				<h2 id ="userGender">Gender: ${gender}</h2>
				<h2 id ="emergencyNum">Emergency contact: ${emergencyNum}</h2>
			</div>

			<div class="editProfileButton">
				<a href = "/editProfile" id="editProfile">Edit Profile</a>
			</div>
		</div>

		<div class="friendblock">
			<div class = "friendblockContent">
				<h2>Your Friends: </h2>
				<div id="friendListBox">

					<%
						LinkedList<String> friends = (LinkedList<String>) request.getAttribute("friends");
						if(friends != null && !friends.isEmpty())
						{
							for(int i = 0; i < friends.size(); i++) {
								out.println("<div class=\"friend\"> -  "+(new dbUtility()).getUser(friends.get(i)).getUsername()+
										"<form method=\"Post\"> <button name =\"removeFriendEmail\" " +
										"value ="+friends.get(i)+" type = \"submit\" class=\"removeBut\"> REMOVE FRIEND" +
										"</button></form></div>");
							}
						}
					%>
				</div>
			</div>
			<div class = "friendblockContent">
				<form id = "addFriend" method = "POST">
					<p id="requestMsg">${msg}
					</p>
					<input id = "friendTextBox" type = "text" name = "receiverEmail" style="color:rgb(50,50,50)" placeholder="Enter your friend's email...">
					<br/>
					<button id="addFriendButton" type = "submit"> Send Friend Request</button>
				</form>
			</div>
			
		</div>


	</div>
	</div>
	<script>
		function LogOut(){
			document.cookie = "userName=; expires=Thu, 01 Jan 1970 00:00:00 UTC;"
			document.cookie = "userEmail=; expires=Thu, 01 Jan 1970 00:00:00 UTC;"
			return true;
		}
	</script>
</body>
</html>