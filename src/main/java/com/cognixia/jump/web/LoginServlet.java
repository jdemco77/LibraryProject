package com.cognixia.jump.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.dao.LibraryDao;
import com.cognixia.jump.model.Librarian;
import com.cognixia.jump.model.Patron;
//import com.cognixia.jump.dao.LibraryDao;
//import com.cognixia.jump.model.User;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private LibraryDao libraryDao;
	
	public void init() {
		
		// will create connection, so no need to open and create it here
		libraryDao = new LibraryDao();
		
	} 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String action = request.getServletPath();
		
		List<Patron> allPatrons = libraryDao.getAllPatrons();
		List<Librarian> allLibrarians = libraryDao.getAllLibrarians();
		String inputUsername = "pj123"; //We still have to change it
		String inputPassword = "1234";
		
		for(Librarian user: allLibrarians) {
			if(user.getUsername().equals(inputUsername) && user.getPassword().equals(inputPassword)) {
				System.out.println("Welcome Librarian");
				//TODO redirect to librarian menu with auth attribute librarian
			}
		}
		
		for(Patron user: allPatrons) {
			if(user.getUsername().equals(inputUsername) && user.getPassword().equals(inputPassword)) {
				System.out.println("Success");
				//TODO redirect to patron menu with auth attribute patron
			} else {
				System.out.println("Fail");
				//TODO redirect to login again
			}	
		}
		
//		// put that data into request object...
//		//request.setAttribute("allAnimals", allAnimals);
//		
//		// ...now we find the jsp for our list of animals...
//		RequestDispatcher dispatcher = request.getRequestDispatcher("BookList.jsp");
//		
//		// ...and redirect there as well as pass on our request and response objects
//		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	public void destroy() {
		
		if (ConnectionManager.getConnection() != null) {

			try {
				ConnectionManager.getConnection().close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

}
