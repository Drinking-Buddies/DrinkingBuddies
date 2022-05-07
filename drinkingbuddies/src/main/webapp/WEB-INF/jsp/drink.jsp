<html>
<head>
	<link rel="stylesheet" type="text/css" href="/index.css">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
	
	<title>Drink Page</title>
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
			<br/>
			<div class = "mainNav">
				<a href = "/home">Home</a>
				<a href = "/profile">Profile</a>
				<a href = "/drink">Drink!</a>
			</div>
			<div class = "toLogin">
				<a href = "/login">login / register</a>
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
	</div>
</body>
</html>