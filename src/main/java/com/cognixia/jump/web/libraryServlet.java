package com.cognixia.jump.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.dao.LibraryDao;
import com.cognixia.jump.model.Librarian;

import com.cognixia.jump.model.*;



public class libraryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public libraryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void listLibrarians(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		List<Librarian> allLibrarians = LibraryDao.getAllLibrarians();
		System.out.println("called, getAllLibrarians = " + allLibrarians);
		
		request.setAttribute("allLibrarians", allLibrarians);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Librarian-list.jsp");
		
		dispatcher.forward(request, response);
	}
	
	private void listBooks(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		List<Book>  allBooks = LibraryDao.getBookList();
		System.out.println("called, getBookList() = " + allBooks);
		
		request.setAttribute("allBooks", allBooks);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Book-list.jsp");
		
		dispatcher.forward(request, response);
	}
	
	private void listPatrons(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		List<Patron>  allPatrons = LibraryDao.getAllPatrons();
		System.out.println("called, getAllPatrons = " + allPatrons);
		
		request.setAttribute("allPatrons", allPatrons);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Patron-list.jsp");
		
		dispatcher.forward(request, response);
	}
	
	private void deleteBook(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String isbn = request.getParameter("isbn");
		
		LibraryDao.deleteBook(isbn);
		
		//check what redirect is for delete book
		response.sendRedirect("list");
	}

	private void addNewBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title") ;
			String descr = request.getParameter("descr");
			boolean rented= Boolean.parseBoolean(request.getParameter("rented"));
			String added_to_library= request.getParameter("added_to_library");
			
			LibraryDao.addNewBook(isbn, title, descr, rented, added_to_library);
			
			response.sendRedirect("list");
		}
	
	private void UpdatePatronUsername (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int patron_id = Integer.parseInt(request.getParameter("patron_id"));
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name") ;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean account_frozen= Boolean.parseBoolean(request.getParameter("account_frozen"));
		
		
		LibraryDao.UpdateUsernamePatron(username, first_name, last_name);
				
		response.sendRedirect("list");
}
	
	private void UpdatePatronPassword (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int patron_id = Integer.parseInt(request.getParameter("patron_id"));
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name") ;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean account_frozen= Boolean.parseBoolean(request.getParameter("account_frozen"));
		
		LibraryDao.UpdatePasswordPatron(username, first_name, last_name);
				
		response.sendRedirect("list");
		}
			
	private void UpdateLibrarianUsername (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int librarian_id = Integer.parseInt(request.getParameter("librarian_id"));
		String username = request.getParameter("username");
		
		LibraryDao.UpdateUsernameLibrarian(username, librarian_id);
				
		response.sendRedirect("list");
}
	
	private void UpdateLibrarianPassword (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int librarian_id = Integer.parseInt(request.getParameter("librarian_id"));
		String password = request.getParameter("password");
		
		LibraryDao.UpdatePasswordLibrarian(password, librarian_id);
				
		response.sendRedirect("list");
}

	private void CheckOutBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String title = request.getParameter("title");
		LibraryDao.CheckOutBook(title);
		
		response.sendRedirect("list");
	}

	private void FreezeAccount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		int id = Integer.parseInt(request.getParameter("patron_id"));
		LibraryDao.FreezeAccount(id);
		
		response.sendRedirect("list");
	}

	
	private void ApproveAccount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		int id = Integer.parseInt(request.getParameter("patron_id"));
		LibraryDao.approveAccount(id);
		
		response.sendRedirect("list");
	}
	
}
