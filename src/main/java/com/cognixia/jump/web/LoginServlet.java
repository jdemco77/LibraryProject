package com.cognixia.jump.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
		
		List<Patron> allPatrons = libraryDao.getAllPatrons();
		List<Librarian> allLibrarians = libraryDao.getAllLibrarians();
		String inputUsername = request.getParameter("inputUsername");
		String inputPassword = request.getParameter("inputPassword");
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		request.setAttribute("UserAuth", "NA");
		
		for(Librarian user: allLibrarians) {
			if(user.getUsername().equals(inputUsername) && user.getPassword().equals(inputPassword)) {
				request.setAttribute("UserAuth", "Librarian");
				dispatcher = request.getRequestDispatcher("LibrarianView.jsp");
			}
		}
		
		for(Patron user: allPatrons) {
			if(user.getUsername().equals(inputUsername) && user.getPassword().equals(inputPassword)) {
				request.setAttribute("UserAuth", "Patron");
				dispatcher = request.getRequestDispatcher("PatronView.jsp");
			} 
		}


		dispatcher.forward(request, response);
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
