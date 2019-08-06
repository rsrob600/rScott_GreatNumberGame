<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="styles.css">
<title>The Great Number Game</title>
</head>
<body>


	<div class='container'>
	
		<div class='header'>
			<h1>Welcome to the Great Number Game!</h1>
			<p>I am thinking of a number between 1 and 100</p>
			<p>Take a guess!</p>
		</div>

		
		<div class='alertBox'>			
			<% String setView = (String) session.getAttribute("setView");

			// set html view based upon resulting session status of controller attribute
			if(setView == "newGame"){ %>
				<div class="newGame">
					<p>When is it a new game?</p>
					<!-- regular submit button -->
				</div>
				
			<% } else if(setView == "correct"){ %>
				<div class="correct">
					<h3>CORRECT! <%= session.getAttribute("guessCorrect") %> was the number!</h3>
					<!-- restart button -->
					<form method="get" action="/GreatNumberGame/Home"><input type="submit" value="Play Again?"></form>
				</div>
				
			<% } else if(setView == "gameOver"){%>
				<div class="gameOver">
					<h3>GAME OVER</h3>
					<h3>The correct answer was: <%= session.getAttribute("guessCorrect") %></h3>
					<!-- restart button -->
					<form method="get" action="/GreatNumberGame/Home"><input type="submit" value="Play Again?"></form>
				</div>
	
			<% } else if(setView == "tooLow"){%>
				<div class="tooLow">
					<h3>Too Low</h3>
					<!-- regular submit button -->
				</div>
				
			<% } else if(setView == "tooHigh"){ %>
				<div class="tooHigh">
					<h3>Too High</h3>
					<!-- regular submit button -->
				</div>
				
			<% } %>
			
		</div>
			
			
					<h1>You have <%= session.getAttribute("attempts") %> attempts!</h1>
					<!-- form sends a post request to the root url so we can have only one servlet -->
					<form method="post" action="/GreatNumberGame/Home">
						<input type="number" name="guess" min="1" max="100"><br>
						<input type="submit" value="Submit">
					</form>
	
	
	
	</div>

</body>
</html>