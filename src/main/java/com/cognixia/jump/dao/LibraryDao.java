package com.cognixia.jump.dao;


import com.cognixia.jump.connection.*;
import com.cognixia.jump.model.Book;
import com.cognixia.jump.model.BookCheckout;
import com.cognixia.jump.model.Librarian;
import com.cognixia.jump.model.Patron;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibraryDao {

	private static final Connection conn = ConnectionManager.getConnection();

	private static final String SELECT_ALL_BOOKS = "select * from book;";
	private static final String DELETE_BOOK = "delete title from book where isbn=?; ";
	private static final String UPDATE_TITLE = "update book set title = ? where isbn=?;";
	private static final String UPDATE_DESCRIPTION = "update book set descr=? where isbn=?;";
	private static final String APPROVE_ACCOUNT = "update patron set account_frozen = false where patron_id=?;";
	private static final String FREEZE_ACCOUNT = "update patron set account_frozen=true where patron_id=?;";
	private static final String UPDATE_USERNAME_PATRON = "update patron set username =? where first_name = ? and last_name =?;";
	private static final String UPDATE_USERNAME_LIBRARIAN = "update librarian set username = ? where librarian_id=?;";
	private static final String UPDATE_PASSWORD_LIBRARIAN = "update librarian set password = ? where librarian_id=?;";
	private static final String UPDATE_PASSWORD_PATRON = "update patron set password=? where first_name=? and last_name=?;";
	private static final String SIGN_UP_PATRON = "insert into patron(first_name,last_name,username,password,account_frozen) values(?,?,?,?,true);";
	private static final String CHECK_OUT_BOOK = "update book set rented= true where title=?;";
	private static final String SELECT_ALL_PATRONS = "select * from patron";
	private static final String ADD_NEW_BOOK = "insert into book(isbn,title,descr,rented,added_to_library) values (?,?,?,?,?);";
	private static final String IS_BOOK_AVAILABLE = "select rented from book where isbn=?;";
	private static final String GET_ALL_LIBRARIANS = "select * from librarian;";
	private static final String RETURN_BOOK = "update book_checkout set returned=? where isbn=?; ";
	private static final String VIEW_PAST_CHECKOUTS = "select * from book_checkout;";
	private static final String VIEW_PAST_CHECKOUTS_WITH_ID = "select * from book_checkout where patron_id=?;";
	

	public static void returnBook(String returnDate, String isbn) {
		try (PreparedStatement pstmt = conn.prepareStatement(RETURN_BOOK);) {

			pstmt.setString(1, returnDate);
			pstmt.setString(2, isbn);
			pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<BookCheckout> viewPastCheckouts() {
		List<BookCheckout> allcheckouts = new ArrayList<BookCheckout>();

		try (PreparedStatement pstmt = conn.prepareStatement(VIEW_PAST_CHECKOUTS);
				ResultSet rs = pstmt.executeQuery()) {
			int checkout_id = rs.getInt("checkout_id");
			int patron_id = rs.getInt("patron_id");
			String isbn = rs.getString("isbn");
			String checkoutdate = rs.getString("checkedout");
			String dueDate = rs.getString("due_date");
			String returned = rs.getString("returned");

			allcheckouts.add(new BookCheckout(checkout_id, patron_id, isbn, checkoutdate, dueDate, returned));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allcheckouts;
	}
	
	public static List<BookCheckout> viewPastCheckouts(int id) {
		List<BookCheckout> allcheckouts = new ArrayList<BookCheckout>();

		try (PreparedStatement pstmt = conn.prepareStatement(VIEW_PAST_CHECKOUTS_WITH_ID);
				) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
			int checkout_id = rs.getInt("checkout_id");
			int patron_id = rs.getInt("patron_id");
			String isbn = rs.getString("isbn");
			String checkoutdate = rs.getString("checkedout");
			String dueDate = rs.getString("due_date");
			String returned = rs.getString("returned");

			allcheckouts.add(new BookCheckout(checkout_id, patron_id, isbn, checkoutdate, dueDate, returned));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allcheckouts;
	}

	public static List<Librarian> getAllLibrarians() {
		List<Librarian> allLibrarians = new ArrayList<Librarian>();

		try (PreparedStatement pstmt = conn.prepareStatement(GET_ALL_LIBRARIANS); ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				int id = rs.getInt("librarian_id");
				String user = rs.getString("username");
				String pass = rs.getString("password");

				allLibrarians.add(new Librarian(id, user, pass));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allLibrarians;

	}

	public static boolean isBookAvail(String isbn) {
		boolean avail = true;
		try (
				PreparedStatement pstmt = conn.prepareStatement(IS_BOOK_AVAILABLE)) {

			pstmt.setString(1, isbn);
			ResultSet rs = pstmt.executeQuery();
			avail = rs.getBoolean(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return avail;
	}

	public static void UpdatePasswordPatron(String password, String first, String last) {
		try (
				PreparedStatement pstmt = conn.prepareStatement(UPDATE_PASSWORD_PATRON)) {

			pstmt.setString(1, password);
			pstmt.setString(2, first);
			pstmt.setString(3, last);
			pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void UpdatePasswordLibrarian(String user, int id) {
		try (
				PreparedStatement pstmt = conn.prepareStatement(UPDATE_PASSWORD_LIBRARIAN)) {
			pstmt.setString(1, user);
			pstmt.setInt(2, id);
			pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void UpdateUsernameLibrarian(String user, int id) {
		try (
				PreparedStatement pstmt = conn.prepareStatement(UPDATE_USERNAME_LIBRARIAN)) {

			pstmt.setString(1, user);
			pstmt.setInt(2, id);
			pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void UpdateUsernamePatron(String username, String fname, String lname) {

		try (
				PreparedStatement pstmt = conn.prepareStatement(UPDATE_USERNAME_PATRON)) {

			pstmt.setString(1, username);
			pstmt.setString(2, fname);
			pstmt.setString(3, lname);
			pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void FreezeAccount(int id) {
		try (
				PreparedStatement pstmt = conn.prepareStatement(FREEZE_ACCOUNT)) {

			
			pstmt.setInt(1, id);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void approveAccount(int id) {

		try (
				PreparedStatement pstmt = conn.prepareStatement(APPROVE_ACCOUNT)) {
			
			
			pstmt.setInt(1, id);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateDescription(String descr, String isbn) {
		try (
				PreparedStatement pstmt = conn.prepareStatement(UPDATE_DESCRIPTION)) {
			pstmt.setString(1, descr);
			pstmt.setString(2, isbn);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void updateTitle(String title, String isbn) {

		try (
				PreparedStatement pstmt = conn.prepareStatement(UPDATE_TITLE)) {

			pstmt.setString(1, title);
			pstmt.setString(2, isbn);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteBook(String isbn) {

		try (
				PreparedStatement pstmt = conn.prepareStatement(DELETE_BOOK)) {

			pstmt.setString(1, isbn);
			pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void SignUpPatron(String first_name, String last_name, String username, String password,
			boolean account_frozen) {

		try (
				PreparedStatement pstmt = conn.prepareStatement(SIGN_UP_PATRON)) {

			pstmt.setString(1, "first_name");
			pstmt.setString(2, "last_name");
			pstmt.setString(3, "username");
			pstmt.setString(4, "password");
			pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void CheckOutBook(String title) {
		try (
				PreparedStatement pstmt = conn.prepareStatement(CHECK_OUT_BOOK)) {

			boolean avail = isBookAvail(title);
			if (avail == true) {
				System.out.println("book is unavailable");
			} else {
				pstmt.setString(1, title);
				pstmt.executeQuery();
			}

		} catch (SQLException e) {
		}

	}

	public void UpdatePassword_Patron(String password, String first, String last) {
		try (PreparedStatement pstmt = conn.prepareStatement(UPDATE_PASSWORD_PATRON)) {

			pstmt.setString(1, password);
			pstmt.setString(2, first);
			pstmt.setString(3, last);
			pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


	public static List<Book> getBookList() {

		List<Book> bookList = new ArrayList<Book>();

		try (PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_BOOKS); ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				String isbn = rs.getString("isbn");
				String title = rs.getString("title");
				String descr = rs.getString("descr");
				boolean rented = rs.getBoolean("rented");
				String dateAdded = rs.getString("added_to_library");

				bookList.add(new Book(isbn, title, descr, rented, dateAdded));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookList;
	}// end getBookList

	public static List<Patron> getAllPatrons() {

		List<Patron> allPatrons = new ArrayList<Patron>();

		try (PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_PATRONS); ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				int id = rs.getInt("patron_id");
				String fname = rs.getString("first_name");
				String lname = rs.getString("last_name");
				String user = rs.getString("username");
				String pass = rs.getString("password");
				boolean frozen = rs.getBoolean("account_frozen");

				allPatrons.add(new Patron(id, fname, lname, user, pass, frozen));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allPatrons;
	}// end getAllPatrons()

	public static void addNewBook(String isbn, String title, String descr, Boolean rented, String added_to_library) {

		try (PreparedStatement pstmt = conn.prepareStatement(ADD_NEW_BOOK)) {

			pstmt.setString(1, isbn);
			pstmt.setString(2, title);
			pstmt.setString(3, descr);
			pstmt.setBoolean(4, rented);
			pstmt.setString(5, added_to_library);
			pstmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
