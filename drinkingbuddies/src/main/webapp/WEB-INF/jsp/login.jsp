<html>
<head>
	<meta content="324990434540-apuh9j1a03gebp7mgak5fsvrcvi825dm.apps.googleusercontent.com"
	          name="google-signin-client_id">
	<link rel="stylesheet" type="text/css" href="/index.css">
	<title>Login Page</title>
</head>
<body>
	<div class = "navBar">
		<h1>DRINKING BUDDIES</h1>
		<img class = "dbLogo" src = "/DrinkingBuddies_Assets/logo_Nav.png">
		<br/>
		<div class = "mainNav">
			<a>Home</a>
			<a>Profile</a>
			<a>Drink!</a>
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
			<p>Don't have a drinking buddy? <a href="/register">Register Here!</a></p>
		</form>
	</div>
	<br class="clear">
	
	
</body>
</html>