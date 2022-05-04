<html>
<head>
	<link rel="stylesheet" type="text/css" href="/index.css">
	<title>Login Page</title>
</head>
<body>
	<div class = "navBar">
		<h1>DRINKING BUDDIES</h1>
		<a>Home</a>
		<br/>
		<a>Profile</a>
		<br/>
		<a>Drink!</a>
	</div>
	<div id = "loginDiv">
		<h2>${errorMsg}</h2>
		<form method = "POST">
			<h2>Login</h2>
			<input class = "textBox" type = "text" name = "userID" placeholder = "User ID"/>
			<br/>
			<input type = "password" name = "password" placeholder = "Password" />
			<br/>
			<button type = "submit">Login</button>
			<br/>
			<button type = "submit">Login with Google</button>
		</form>
	</div>
	<br class="clear">
	
	
</body>
</html>