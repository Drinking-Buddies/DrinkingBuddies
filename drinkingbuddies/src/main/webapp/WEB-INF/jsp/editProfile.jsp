<%@ page language="java" import = "java.util.*,com.drinkingbuddies.drinkingbuddies.classes.*"
    contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="/index.css">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
	
	<title>Profile Page</title>
	<%
		Cookie[] cookies = null;
		Cookie myCookie = null;
		cookies = request.getCookies();
		boolean login = false;
		String name = "Guest";
		if (cookies != null){
			for (int i = 0; i < cookies.length; i++){
				if (cookies[i].getName().equals("userName")){
					myCookie = cookies[i];
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
		<div class="editContent">
			<div class="profileUser">
				<h1><%= name%>'s profile</h1>
			</div>
			<br/>
			<div class="editBlock">
				<div id="bioEdit">
					<form method = "POST">
						<textarea name="newBio">Enter your new bio here...</textarea>
						<p class = "errorMsg">${bioError}</p>
						<button id="editBioBut" type="submit">Edit</button>
					</form>
				</div>
	
				<div id="restEdit">
					<form method = "POST">
						<div id="updateSelectors">
							<input class = "dateSelect" type = "date" name = "birthDate"/>
							<select name = "gender" class = "selectBox">
								<option value="" disabled selected>Select your gender</option>
								<option value = "Male">Male</option>
								<option value = "Female">Female</option>
							</select>
						</div>
						<div class="editProfileContent">
							<input class = "textBox" type = "text" name = "weight" placeholder = "Please enter your weight in lbs" />
							<p class = "errorMsg">${weightError}</p>
							<input class = "textBox" type = "text" name = "phone" placeholder = "Please enter your phone number" />
							<p class = "errorMsg">${phoneError}</p>
							<input class = "textBox" type = "text" name = "emergency" placeholder = "Please enter your emergency contact" />
							<p class = "errorMsg">${emergencyError}</p>
							<button id="applyButton" type="submit">Apply</button>
							<p>${editMsg}</p>
						</div>
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