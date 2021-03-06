<%@ page language="java" import = "java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<HTML>
<head>
	<link rel="stylesheet" type="text/css" href="/index.css">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
	<title>Home Page</title>
	<%
		Cookie[] cookies = null;
		Cookie myCookie = null;
		cookies = request.getCookies();
		boolean login = false;
		String name = "Guest";
		if (cookies != null){
			for (int i = 0; i < cookies.length; i++){
				if (cookies[i].getName().equals("userName")){
					myCookie = cookies[i];
					name = cookies[i].getValue().replaceAll("=", " ");
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
			<div class = "mainNav">
				<a href = "/home">Home</a>
				<a href = "/profile">Profile</a>
				<a href = "/drink">Drink!</a>
			</div>
			<div class = "toLogin">
				<%
					if (!login) {
						out.println("<a href = '/login'>login / register</a>");
					} else {
						out.println("<a href = '/home' onclick = 'LogOut();'>logout</a>");
					}
				%>
			</div>
		</div>
		<div id = "homePage">
			<h1>Welcome <%= name %>!</h1>
			<%
				if (login){
			%>
			<h2>Here's your stats from the past few days: </h2>
			<div id = "drinkingHistory">
				<%
				LinkedList<String> history = (LinkedList<String>)request.getAttribute("history");
				if (history != null && !history.isEmpty()){
					for (String h : history){
					%>
						<div class = "historyBlock">
							<%
							out.println(h);
							%>
						</div>
					<%
					}
				}else{
					out.println("<h3> You have no drinking sessions... </h3>");
				}
				%>
			
			<!--
				/*
					List<Date> drinkingHistory;
					drinkingHistory = (List<Date>) request.getAttribute("drinkingHistory");
					if(drinkingHistory != null && !drinkingHistory.isEmpty())
					{
						for(int i = 0; i < drinkingHistory.size(); i++)
						{
				*/
						<button>
							drinkingHistory.get(i).getDate();
						</button>
				/*
						}
					}
				*/
			-->
			</div>
			<h2>Your pending friend requests: </h2>

			<div id="requestDisplay">
				<%
					LinkedList<String> pendingReq = (LinkedList<String>) request.getAttribute("curRequest");
					if(pendingReq != null && !pendingReq.isEmpty())
					{
						for (String curRequest : pendingReq){
							out.print("<h3>"+curRequest+" wants to be your friend.</h3>");
							out.print("<div id=\"acceptFriendButton\">");
							out.print("<form action=\"/acceptFriend\" method=\"Post\"><button class = 'acceptFriend' value="+curRequest+" type = \"submit\" name='reqEmail'>");
							out.print("Accept</button></form></div>");
						}
					}else{
						out.println("<h3> You have no pending requests... </h3>");
					}
				%>
			</div>
			<%
				}else{
			%>
				<h2>Please login to see your stats! </h2>
			<% } %>
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

</HTML>
