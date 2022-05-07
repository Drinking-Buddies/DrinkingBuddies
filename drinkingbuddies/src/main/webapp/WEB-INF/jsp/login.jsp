<html>
<head>
	<link rel="stylesheet" type="text/css" href="/index.css">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
	<script src="http://apis.google.com/js/api:client.js"></script>
	<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
	<title>Login Page</title>
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
		<div id = "loginDiv">
			<form id = "loginForm"method = "POST">
				<h2>Login</h2>
				<input class = "textBox" type = "text" name = "email" placeholder = "Email" required/>
				<input class = "textBox" type = "password" name = "password" placeholder = "Password" required/>
				<p class = "errorMsg">${errorMsg}</p>
				<button id = "loginButton" type = "submit">Login</button>
				<p>Don't have a drinking buddy? <a href="/register" id = "registerLink">Register Here!</a></p>
			</form>
		</div>
	</div>
	
</body>
</html>