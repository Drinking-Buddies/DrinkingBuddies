<html>
<head>
	<link rel="stylesheet" type="text/css" href="/index.css">
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">
	
	<title>Register Page</title>
</head>
<body>
	<div id = "registerDiv">
		<form id = "registerForm" method = "POST">
			<h2>Register</h2>
			<input class = "textBox" type = "text" name = "email" placeholder = "Email" required/>
			<br/>
			<input class = "textBox" type = "password" name = "password" placeholder = "Password" required/>
			<br/>
			<input class = "textBox" type = "text" name = "userID" placeholder = "User ID" required/>
			<br/>
			<input class = "textBox" type = "text" name = "legalName" placeholder = "Your Legal Name" required/>
			<br/>
			<div id = "userInfo">
				<p> This section is for your personal BAC calculation to ensure a safe drinking experience! </p>
				<div>
					<label for = "birthdate">Your birth date:</label>
					<input class = "dateSelect" type = "date" name = "birthDate" required/>
				</div>
				<div>
					<label for = "gender">Please input your binary sex: </label>
					<select name = "gender" class = "selectBox" required>
						<option value = "Male">Male</option>
						<option value = "Female">Female</option>
					</select>
				</div>
			</div>
			<input class = "textBox" type = "text" name = "weight" placeholder = "Please enter your weight in lbs" required/>
			<br/>
			<button id = "registerButton" type = "submit">Sign me up!</button>
		</form>
		<img src = "/DrinkingBuddies_Assets/logo_Back.png" id = "beerLogo_1">
		<img src = "/DrinkingBuddies_Assets/logo_Back.png" id = "beerLogo_2">
		<img src = "/DrinkingBuddies_Assets/logo_Back.png" id = "beerLogo_3">
		<img src = "/DrinkingBuddies_Assets/logo_Back.png" id = "beerLogo_4">
		<img src = "/DrinkingBuddies_Assets/logo_Back.png" id = "beerLogo_5">
	</div>
	<br class="clear">
	
	
</body>
</html>