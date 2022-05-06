<html>
<head>
	<meta content="324990434540-apuh9j1a03gebp7mgak5fsvrcvi825dm.apps.googleusercontent.com"
	          name="google-signin-client_id">
	<link rel="stylesheet" type="text/css" href="/index.css">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
	<title>Login Page</title>
</head>
<body>
	<div class = "container">
		<div class = "navBar">
			<h1>DRINKING BUDDIES</h1>
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
			<h2>${errorMsg}</h2>
			<form id = "loginForm"method = "POST">
				<h2>Login</h2>
				<input class = "textBox" type = "text" name = "userID" placeholder = "User ID"/>
				<br/>
				<input class = "textBox" type = "password" name = "password" placeholder = "Password" />
				<br/>
				<button id = "loginButton" type = "submit">Login</button>
				<br/>
				<button type = "submit">Login with Google</button>
				<br/>
				<p>Don't have a drinking buddy? <a href="/register" id = "registerLink">Register Here!</a></p>
			</form>
		</div>
	</div>
	
</body>
</html>