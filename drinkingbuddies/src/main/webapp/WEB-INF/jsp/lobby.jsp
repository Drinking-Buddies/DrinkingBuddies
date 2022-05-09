<html>
<head>
	<link rel="stylesheet" type="text/css" href="/index.css">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
	<title>Drink Page</title>
	
	<%
		response.setIntHeader("Refresh", 5);
	
       	Cookie[] cookies = null;
       	Cookie myCookie = null;
		
       	// need to deal with space later!!!!!!!!!!!!!!!!!!!!!!!!!!!
       	
		cookies = request.getCookies();
		boolean login = false;
		String roomName = "";
		String userEmail = "";
		if (cookies != null){
			for (int i = 0; i < cookies.length; i++){
				if (cookies[i].getName().equals("lobbyName")){
					myCookie = cookies[i];
					roomName = cookies[i].getValue().replaceAll("="," ");
				}
				if (cookies[i].getName().equals("userEmail")){
					userEmail = cookies[i].getValue();
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
			<img class = "dbLogoLobby1" src = "/DrinkingBuddies_Assets/logo_Nav.png" style="margin-top: 30%">
			<img class = "dbLogoLobby2" src = "/DrinkingBuddies_Assets/logo_Nav.png" style="margin-top: 30%">
			<br/>
			<div class = "mainNav" style="margin-top: 40%">
				<a href = "/home">Leave</a>
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
				<p class = "lobbyPlayer">${seat1}</p>
				<p class = "lobbyPlayerDrink">${drink1}</p>
				<div id = "addDrink">
					<%
						if (!userEmail.isBlank()){
					%>
						<a href="${pageContext.servletContext.contextPath}/addShot" id="addShot"> Add Shot </a>
					<%
						}
					%>
				</div>
			</div>
			<div class="user" style="margin-top: 55px;margin-left: 15%;width: 180px">
				<c:choose>
					<c:when test="${empty seat2}">
						<p class = "lobbyPlayer">no one is sitting here...</p>
					</c:when>
					<c:otherwise>
						<p class = "lobbyPlayer">${seat2}</p>
						<p class = "lobbyPlayerDrink">${drink2}</p>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="user" style="margin-top: 0px;margin-left: 42%;width: 180px">
				<c:choose>
					<c:when test="${empty seat3}">
						<p class = "lobbyPlayer">no one is sitting here...</p>
					</c:when>
					<c:otherwise>
						<p class = "lobbyPlayer">${seat3}</p>
						<p class = "lobbyPlayerDrink">${drink3}</p>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="user" style="margin-top: 55px;margin-left: 68%;width: 180px">
				<c:choose>
					<c:when test="${empty seat4}">
						<p class = "lobbyPlayer">no one is sitting here...</p>
					</c:when>
					<c:otherwise>
						<p class = "lobbyPlayer">${seat4}</p>
						<p class = "lobbyPlayerDrink">${drink4}</p>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="user" style="margin-top: 180px;margin-left: 75%;width: 180px">
				<c:choose>
					<c:when test="${empty seat5}">
						<p class = "lobbyPlayer">no one is sitting here...</p>
					</c:when>
					<c:otherwise>
						<p class = "lobbyPlayer">${seat5}</p>
						<p class = "lobbyPlayerDrink">${drink5}</p>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="user" style="margin-top: 330px;margin-left: 71%;width: 180px">
				<c:choose>
					<c:when test="${empty seat6}">
						<p class = "lobbyPlayer">no one is sitting here...</p>
					</c:when>
					<c:otherwise>
						<p class = "lobbyPlayer">${seat6}</p>
						<p class = "lobbyPlayerDrink">${drink6}</p>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="user"style="margin-top: 420px;margin-left: 42%;width: 180px">
				<c:choose>
					<c:when test="${empty seat7}">
						<p class = "lobbyPlayer">no one is sitting here...</p>
					</c:when>
					<c:otherwise>
						<p class = "lobbyPlayer">${seat7}</p>
						<p class = "lobbyPlayerDrink">${drink7}</p>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="user"style="margin-top: 330px;margin-left: 12%;width: 180px">
				<c:choose>
					<c:when test="${empty seat8}">
						<p class = "lobbyPlayer">no one is sitting here...</p>
					</c:when>
					<c:otherwise>
						<p class = "lobbyPlayer">${seat8}</p>
						<p class = "lobbyPlayerDrink">${drink8}</p>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="table">
				<img class = "tables" src = "/DrinkingBuddies_Assets/table.png">
				<img class = "dbLogoTable" src = "/DrinkingBuddies_Assets/logo_Back.png">
				<c:choose>
					<c:when test="${BAC eq '0.00'}">
						<h3 class = "bacUpper">No drinks yet. Better get started.</h3>
					</c:when>
					<c:when test="${(empty BAC)}">
						<h3 class = "bacUpper">Login to join</h3>
					</c:when>
					<c:otherwise>
						<h3 class = "bacUpper">Your BAC: ~${BAC}%</h3>
						<h3 class = "bacLower">${drunkMsg}</h3>
						<h3 class = "bacRemind">1 shot = 12oz of beer = 9oz of mix = 5 oz of wine</h3>
					</c:otherwise>
				</c:choose>
				
			</div>
		</div>
	</div>
</body>
</html>