package com.codingdojo.web.controllers;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// when home page is initially visited start a new session
		HttpSession session = request.getSession();
		// set attempt variable to track how many attempts remain (starting point is null)
		Integer attempts = (Integer) session.getAttribute("attempts");

		// check if there is an existing game session in process - otherwise start new game
		if(session.getAttribute("attempts") == null) {
			// reset attempts once new game is started
			session.setAttribute("attempts", 5);
			// set html view for new game form
			session.setAttribute("setView", "newGame");
		}
		
		// set view
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/index.jsp");
        view.forward(request, response);

        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// start a new session when form is submitted
		HttpSession session = request.getSession();
		// set secret variable in session (starting point is null)
		Integer secret = (Integer) session.getAttribute("secret");
		// set guessCorrect variable in session - used to output at end of game before a new random is 
		// created (starting point is null)
		Integer guessCorrect = (Integer) session.getAttribute("guessCorrect");
		// set guess variable in session (starting point is null)
		Integer guess = Integer.parseInt(request.getParameter("guess"));
		// get random number
		Random random = new Random();
		// set attempt variable to track how many attempts remain (starting point is null)
		Integer attempts = (Integer) session.getAttribute("attempts");
		

		
		// check if there is an existing game session in process - otherwise start new game
		if(session.getAttribute("secret") == null) {
			// set the session secret to random number between 1-100;
			session.setAttribute("secret", random.nextInt(100));
			// reset attempts once new game is started
			session.setAttribute("attempts", 5);
			// set html view for new game form
			session.setAttribute("setView", "newGame");
			
			
		}
		// for testing purposes only
		System.out.println("secret: " + secret);
		System.out.println("guess: " + guess);
		System.out.println("attempts: " + attempts);
		System.out.println("guessCorrect: " + guessCorrect);
		

		
		if(attempts > 0) {
			// compare guess against secret - if equal then set html view as correct (end game) - restart game
			if((int) secret == (int) guess) {
				session.setAttribute("guessCorrect", secret);
				session.setAttribute("secret", random.nextInt(100));
				session.setAttribute("attempts", 5);
				session.setAttribute("setView", "correct");
			} else if(guess > secret) {
				// if guess is higher - set html view as high and decrement attempts
				session.setAttribute("setView", "tooHigh");
				// decrement attempts
				session.setAttribute("attempts", attempts-1);
			} else {
				// if guess is lower - set html view as low and decrement attempts
				session.setAttribute("setView", "tooLow");
				// decrement attempts
				session.setAttribute("attempts", attempts-1);
			} 
		} else {
			session.setAttribute("guessCorrect", secret);
			session.setAttribute("secret", random.nextInt(100));
			session.setAttribute("attempts", 5);
			session.setAttribute("setView", "gameOver");
			//request.getSession().invalidate();
		}
		
		if(request.getParameter("reset") != null) {
			session.invalidate();
		}
		
		doGet(request, response);
		

	}

}
