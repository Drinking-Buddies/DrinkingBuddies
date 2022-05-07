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
			<img class = "dbLogo" src = "/DrinkingBuddies_Assets/logo_Nav.png" style="margin-top: 30%">
			<br/>
			<div class = "mainNav">
				<a href = "/">Leave</a>
			<div class = "mainNav" style="margin-top: 40%">
				<a href = "/home">Leave</a>
			</div>
		</div>


		<div id = "lobbyDiv">
			<div class="greeting">
				<h1>Room: ${roomName}</h1>
			</div>
			<div class="user" style="margin-top: 16%;margin-left: 2%; width: 16%">
				1
				<form id = "name" method = "POST">
					<button type="submit" name="addShot" id="addShot"> Add Shot </button>
				</form>

			</div>
			<div class="user" style="margin-top: 4%;margin-left: 12%">
				2
			</div>
			<div class="user" style="margin-top: 0%;margin-left: 32%">
				3
			</div>
			<div class="user" style="margin-top: 4%;margin-left: 52%">
				4
			</div>
			<div class="user" style="margin-top: 16%;margin-left: 60%">
				5
			</div>
			<div class="user" style="margin-top: 30%;margin-left: 52%">
				6
			</div>
			<div class="user"style="margin-top: 34%;margin-left: 32%">
				7
			</div>
			<div class="user"style="margin-top: 30%;margin-left: 12%">
				8
			</div>
			<div class="table">
				<img class = "tables" src = "/DrinkingBuddies_Assets/table.png">
			</div>
		</div>
	</div>
</body>
</html>