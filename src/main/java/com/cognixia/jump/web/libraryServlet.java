package com.cognixia.jump.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.dao.LibraryDao;
import com.cognixia.jump.model.Librarian;

import com.cognixia.jump.model.*;

@WebServlet("/")
public class libraryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public libraryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO add action getcontextpath/api1/the-action
		
		String action = request.getServletPath();
		System.out.println(action);
		switch (action) {
		case "/Api/listPatrons":
			listPatrons(request, response);
			break;
		case "/ListBooks":
			listBooks(request, response);
			break;
		case "/Api/checkout":
			CheckOutBook(request, response);
			break;
		case "/Api/returnBook":
			ReturnBooks(request, response);
			break;
		case "/Api/pastCheckout":
			PastCheckouts(request, response);
			break;
		case "/Api/updatePatronUserName":
			UpdatePatronUsername(request, response);
			break;
		case "/Api/updatePatronPassword":
			UpdatePatronPassword(request, response);
			break;
		case "/Api/updateLibrarianUsername":
			UpdateLibrarianUsername(request, response);
			break;
		case "/Api/updateLibrarianpassword":
			UpdateLibrarianPassword(request, response);
			break;
		case "/Api/updateBookTitle":
			UpdateBookTitle(request, response);
			break;
		case "/Api/addNewBook":
			addNewBook(request, response);
			break;
		case "/Api/updateBookDescription":
			UpdateBookDescription(request, response);
			break;
		case "/Api/freeze":
			FreezeAccount(request, response);
			break;
		case "/Api/isAvailable":
			IsBookAvail(request, response);
			break;
		case "/Api/approve":
			ApproveAccount(request, response);
			break;
		case "/Api/deletebook":
			deleteBook(request, response);
			break;
			
			
		default:  // default will just go to our index.jsp page
			RequestDispatcher dispatcher = request.getRequestDispatcher("PatronView.jsp");
			dispatcher.forward(request, response);
			break;
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	}


	private void ReturnBooks(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void PastCheckouts(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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
		response.sendRedirect("SuccessPage.jsp");
	}

	private void addNewBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title") ;
			String descr = request.getParameter("descr");
			boolean rented= Boolean.parseBoolean(request.getParameter("rented"));
			String added_to_library= request.getParameter("added_to_library");
			
			LibraryDao.addNewBook(isbn, title, descr, rented, added_to_library);
			
			response.sendRedirect("SuccessPage.jsp");
		}
	
	private void IsBookAvail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String isbn = request.getParameter("isbn");
		boolean avail= LibraryDao.isBookAvail(isbn);
		if(avail==true) {
			System.out.println("book is available");
		}
		response.sendRedirect("SuccessPage.jsp");
	}
	
	private void CheckOutBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String title = request.getParameter("title");
		LibraryDao.CheckOutBook(title);
		
		response.sendRedirect("SuccessPage.jsp");
	}
	
	private void UpdatePatronUsername (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name") ;
		String username = request.getParameter("username");
		
		LibraryDao.UpdateUsernamePatron(username, first_name, last_name);
				
		response.sendRedirect("SuccessPage.jsp");
}
	
	private void UpdatePatronPassword (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name") ;
		String password = request.getParameter("password");
		
		LibraryDao.UpdatePasswordPatron(password, first_name, last_name);
				
		response.sendRedirect("SuccessPage.jsp");
		}
			
	private void UpdateLibrarianUsername (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int librarian_id = Integer.parseInt(request.getParameter("librarian_id"));
		String username = request.getParameter("username");
		
		LibraryDao.UpdateUsernameLibrarian(username, librarian_id);
				
		response.sendRedirect("SuccessPage.jsp");
}
	
	private void UpdateLibrarianPassword (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int librarian_id = Integer.parseInt(request.getParameter("librarian_id"));
		String password = request.getParameter("password");
		
		LibraryDao.UpdatePasswordLibrarian(password, librarian_id);
				
		response.sendRedirect("SuccessPage.jsp");
}

	private void UpdateBookTitle (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String isbn = request.getParameter("isbn");
		String title = request.getParameter("title");
		
		LibraryDao.updateTitle(title, isbn);
				
		response.sendRedirect("SuccessPage.jsp");
}
	
	private void UpdateBookDescription (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String isbn = request.getParameter("isbn");
		String descr = request.getParameter("descr");
		
		LibraryDao.updateDescription(descr, isbn);
				
		response.sendRedirect("SuccessPage.jsp");
}
	
	private void FreezeAccount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		int id = Integer.parseInt(request.getParameter("patron_id"));
		LibraryDao.FreezeAccount(id);
		
		response.sendRedirect("SuccessPage.jsp");
	}

	private void ApproveAccount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("patron_id"));
		LibraryDao.approveAccount(id);
		
		response.sendRedirect("SuccessPage.jsp");
	}
	
	
	
	
}
