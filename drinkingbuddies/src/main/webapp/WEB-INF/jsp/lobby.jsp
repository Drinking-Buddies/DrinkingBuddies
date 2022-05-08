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
		
       	// need to deal with space later!!!!!!!!!!!!!!!!!!!!!!!!!!!
       	
		cookies = request.getCookies();
		boolean login = false;
		String roomName = "";
		if (cookies != null){
			for (int i = 0; i < cookies.length; i++){
				if (cookies[i].getName().equals("lobbyName")){
					myCookie = cookies[i];
					roomName = cookies[i].getValue();
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
			<img class = "dbLogo" src = "/DrinkingBuddies_Assets/logo_Nav.png" style="margin-top: 30%">
			<br/>
			<div class = "mainNav" style="margin-top: 40%">
				<a href = "${pageContext.servletContext.contextPath}/leave">Leave</a>
			</div>
		</div>


		<div id = "lobbyDiv">
			<div class="greeting">
				<h1>
					<%
						out.println("Lobby: "+roomName);
					%>
				</h1>
			</div>
			<div class="user" style="margin-top:180px; margin-left:8%; width: 180px">
				1
				<div id = "addDrink">
					<a href="${pageContext.servletContext.contextPath}/addShot" id="addShot"> Add Shot </a>
				</div>

			</div>
			<div class="user" style="margin-top: 55px;margin-left: 15%;width: 180px">
				2
			</div>
			<div class="user" style="margin-top: 0px;margin-left: 42%;width: 180px">
				3
			</div>
			<div class="user" style="margin-top: 55px;margin-left: 68%;width: 180px">
				4
			</div>
			<div class="user" style="margin-top: 180px;margin-left: 75%;width: 180px">
				5
			</div>
			<div class="user" style="margin-top: 330px;margin-left: 71%;width: 180px">
				6
			</div>
			<div class="user"style="margin-top: 420px;margin-left: 42%;width: 180px">
				7
			</div>
			<div class="user"style="margin-top: 330px;margin-left: 12%;width: 180px">
				8
			</div>
			<div class="table">
				<img class = "tables" src = "/DrinkingBuddies_Assets/table.png">
			</div>
		</div>
	</div>
</body>
</html>