package com.cognixia.jump.web;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.dao.LibraryDao;
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
		switch (action) {
		case "/listPatrons":							//done
			listPatrons(request, response);
			break;
		case "/ListBooks":								//done
			listBooks(request, response);
			break;
		case "/ListBooksLib":								//done
			listBooksLib(request, response);
			break;
		case "/checkout":							//rent book from library
			CheckOutBook(request, response);
			break;
		case "/returnBook":							//return to library
			ReturnBooks(request, response);
			break;
		case "/pastCheckout":				    //james's
			PastCheckouts(request, response);
			break;
		case "/updatePatronUserName":				//merge w password
			UpdatePatronUsername(request, response);
			break;
		case "/Api/updatePatronPassword"://			//merge w username
			UpdatePatronPassword(request, response);
			break;
		case "/Api/updateLibrarianUsername":    	//same as above different view
			UpdateLibrarianUsername(request, response);
			break;
		case "/Api/updateLibrarianpassword":		//same as above ifferent view
			UpdateLibrarianPassword(request, response);
			break;
		case "/updateBook":				//merge w description
			UpdateBook(request, response);
			break;
		case "/Api/addNewBook":						//form 
			addNewBook(request, response);
			break;
		case "/freeze":									//done
			FreezeAccount(request, response);
			break;
		case "/approve":								//done
			ApproveAccount(request, response);
			break;
//		case "/Api/deletebook":
//			deleteBook(request, response);
//			break;
			
			
		default:  // default will just go to our index.jsp page
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
			dispatcher.forward(request, response);
			break;
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	}


	private void ReturnBooks(HttpServletRequest request, HttpServletResponse response) {
		
		
	}

	private void PastCheckouts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		if(request.getParameter("patron_id") != null) {
			id = Integer.parseInt(request.getParameter("patron_id"));
		}
		
		List<Book>  allBooks = LibraryDao.getBookList();
		List<BookCheckout>  userCheckouts = LibraryDao.viewPastCheckouts(id);
		
		request.setAttribute("userCheckouts", userCheckouts);
		request.setAttribute("allBooks", allBooks);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("mybooks.jsp");
		
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	private void listBooks(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		List<Book>  allBooks = LibraryDao.getBookList();
		
		request.setAttribute("allBooks", allBooks);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Book-list.jsp");
		
		dispatcher.forward(request, response);
	}
	
	private void listBooksLib(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		List<Book>  allBooks = LibraryDao.getBookList();
		
		request.setAttribute("allBooks", allBooks);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Book-list-lib.jsp");
		
		dispatcher.forward(request, response);
	}
	
	private void listPatrons(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		List<Patron>  allPatrons = LibraryDao.getAllPatrons();

		
		request.setAttribute("allPatrons", allPatrons);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("patron-list.jsp");
		
		dispatcher.forward(request, response);
	}
	
//	private void deleteBook(HttpServletRequest request, HttpServletResponse response) 
//			throws ServletException, IOException {
//		
//		String isbn = request.getParameter("isbn");
//		
//		LibraryDao.deleteBook(isbn);
//		
//		//check what redirect is for delete book
//		response.sendRedirect("SuccessPage.jsp");
//	}

	private void addNewBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title") ;
			String descr = request.getParameter("descr");
			boolean rented= Boolean.parseBoolean(request.getParameter("rented"));
			String added_to_library= request.getParameter("added_to_library");
			
			LibraryDao.addNewBook(isbn, title, descr, rented, added_to_library);
			
			response.sendRedirect("SuccessPageL.jsp");
		}	
	
	private void CheckOutBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String title = request.getParameter("title");
		LibraryDao.CheckOutBook(title);
		
		response.sendRedirect("SuccessPageP.jsp");
	}
	
	private void UpdatePatronUsername (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name") ;
		String username = request.getParameter("username");
		
		LibraryDao.UpdateUsernamePatron(username, first_name, last_name);
				
		response.sendRedirect("SuccessPageP.jsp");
}
	
	private void UpdatePatronPassword (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name") ;
		String password = request.getParameter("password");
		
		LibraryDao.UpdatePasswordPatron(password, first_name, last_name);
				
		response.sendRedirect("SuccessPageP.jsp");
		}
			
	private void UpdateLibrarianUsername (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int librarian_id = Integer.parseInt(request.getParameter("librarian_id"));
		String username = request.getParameter("username");
		
		LibraryDao.UpdateUsernameLibrarian(username, librarian_id);
				
		response.sendRedirect("SuccessPageL.jsp");
}
	
	private void UpdateLibrarianPassword (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int librarian_id = Integer.parseInt(request.getParameter("librarian_id"));
		String password = request.getParameter("password");
		
		LibraryDao.UpdatePasswordLibrarian(password, librarian_id);
				
		response.sendRedirect("SuccessPageL.jsp");
}

	private void UpdateBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String isbn = request.getParameter("bookIsbn");
		String title = request.getParameter("bookTitle");
		String descr = request.getParameter("bookDescr");
		
		//System.out.println("Updating " + isbn + " to " + title + " : " + descr);
		
		LibraryDao.updateTitle(title, isbn);
		LibraryDao.updateDescription(descr, isbn);
				
		response.sendRedirect("SuccessPageL.jsp");
}
	
	private void FreezeAccount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("patron_id"));
		int librarian_id = Integer.parseInt(request.getParameter("patron_id"));
		String url = "/listPatrons?librarian_id=" + librarian_id;
		LibraryDao.FreezeAccount(id);
		
		request.getRequestDispatcher(url).forward(request, response);
	}

	private void ApproveAccount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int librarian_id = Integer.parseInt(request.getParameter("patron_id"));
		int id = Integer.parseInt(request.getParameter("patron_id"));
		String url = "/listPatrons?librarian_id=" + librarian_id;
		LibraryDao.approveAccount(id);
		
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	
	
	
}
