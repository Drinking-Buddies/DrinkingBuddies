<html>
<head>
	<link rel="stylesheet" type="text/css" href="/index.css">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
	<title>Drink Page</title>
	
	<%
		
       	Cookie[] cookies = null;
       	Cookie myCookie = null;
		
		cookies = request.getCookies();
		boolean login = false;
		if (cookies != null){
			for (int i = 0; i < cookies.length; i++){
				if (cookies[i].getName().equals("userName")){
					myCookie = cookies[i];
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
			<br/>
			<div class = "mainNav">
				<a href = "/home">Home</a>
				<a href = "/profile">Profile</a>
				<a href = "/drink">Drink!</a>
			</div>
			<div class = "toLogin">
				<%
					if (!login){
						out.println("<a href = '/login'>login / register</a>");
					}else{
						out.println("<a href = '/home' onclick = 'LogOut();'>logout</a>");
					}
				%>
			</div>
		</div>
		<div id = "joinLobby">
			<h2>${errorMsg}</h2>
			<form id = "joinForm" method = "POST">
				<%
				// only show host a lobby if logged in
					if (login){
				%>
					<h2>Be the Host</h2>
					<input class = "textBox_Drink" type = "text" name = "hostName" placeholder = "Lobby Name"/>
					<button class = "drinkButton" type = "submit">Host</button>
				<%
					}
				%>
				<h2>Join a Lobby</h2>
				<input class = "textBox_Drink" type = "text" name = "joinName" placeholder = "Lobby Name"/>
				<button class = "drinkButton" type = "submit">Join</button>
			</form>
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