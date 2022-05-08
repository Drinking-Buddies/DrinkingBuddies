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

		<div class="profileUser"><h1>${userName}'s profile</h1></div>



		<div class="editBlcok">
			<div id="bioEdit">
				<form method = "POST">
					<input type="text" id = "editBio" name="newBio" placeholder="Enter your new bio here..." required>
					<div id="editBioBut"><button type="submit">Change to new Bio</button></div>
				</form>
			</div>

			<div id="restEdit">
				<form method = "POST">
					<input class = "dateSelect" type = "date" name = "birthDate"/>
					<select name = "gender" class = "selectBox">
						<option value = "Male">Male</option>
						<option value = "Female">Female</option>
					</select>
					<input class = "textBox" type = "text" name = "weight" placeholder = "Please enter your weight in lbs" />
					<input class = "textBox" type = "text" name = "phone" placeholder = "Please enter your phone number" />
					<input class = "textBox" type = "text" name = "emergency" placeholder = "Please enter your emergency contact" />
					<button type="submit">Apply</button>
				</form>

			</div>
		</div>

	</div>
</body>
</html>