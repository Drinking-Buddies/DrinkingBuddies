<html>
<head>
	<link rel="stylesheet" type="text/css" href="/index.css">
	<title>Drink Page</title>
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
	<div id = "joinLobby">
		<h2>${errorMsg}</h2>
		<form id = "joinForm" method = "POST">
			<h2>Be the Host</h2>
			<input class = "textBox_Drink" type = "text" name = "hostName" placeholder = "Lobby Name"/>
			<button class = "drinkButton" type = "submit">Host</button>
			<br/>
			<h2>Join a Lobby</h2>
			<input class = "textBox_Drink" type = "text" name = "joinName" placeholder = "Lobby Name"/>
			<button class = "drinkButton" type = "submit">Join</button>
		</form>
	</div>
	<br class="clear">
</body>
</html>