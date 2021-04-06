package FInalPackageDao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import FInalPackageConnection.ConnectionManager;
import FInalPackageModel.Book;
import FInalPackageModel.Patron;


public class LibraryDao {

	  private static final String SELECT_ALL_BOOKS= "select title,isbn from Book;";
	  private static final String DELETE_BOOK ="delete title from book where isbn=?; ";
	  private static final String UPDATE_TITLE="update book set title = ? where isbn=?;";
	  private static final String UPDATE_DESCRIPTION="update book set descr=? where isbn=?;";
	  private static final String APPROVE_ACCOUNT="update patron set account_frozen = false;";
	  private static final String FREEZE_ACCOUNT="update patron set account_frozen=true;";
	  private static final String UPDATE_USERNAME_PATRON="update patron set username =? where first_name = ? and last_name =?;";
	  private static final String UPDATE_USERNAME_LIBRARIAN="update librarian set username = ? where librarian_id=?;";
	  private static final String UPDATE_PASSWORD_LIBRARIAN="update librarian set password = ? where librarian_id=?;";
	  private static final String UPDATE_PASSWORD_PATRON="update patron set password=? where first_name=? and last_name=?;";
	  private static final String SIGN_UP_PATRON="insert into patron(first_name,last_name,username,password,account_frozen) values(?,?,?,?,true);";
	  private static final String CHECK_OUT_BOOK="update book set rented= true where title=?;";
	  private static final String SELECT_ALL_PATRONS="select patron_id,first_name,last_name from Patron";
	  private static final String ADD_NEW_BOOK="insert into book(isbn,title,descr,rented,added_to_library) values (?,?,?,?,?);";
	  private static final String IS_BOOK_AVAILABLE="select rented from book where isbn=?;";
	  
	  public boolean isBookAvail(String isbn) {
		  boolean avail=true;
		  try(Connection conn= ConnectionManager.getConnection();
				  PreparedStatement pstmt = conn.prepareStatement(IS_BOOK_AVAILABLE) ) 
			  {
			  
			  pstmt.setString(1, isbn );
			  ResultSet rs = pstmt.executeQuery();
			  avail = rs.getBoolean(1);
			  
			  
			  
			  }catch(SQLException e) {
				  e.printStackTrace();
			  }catch(ClassNotFoundException e) {
				  e.printStackTrace();
			  }
		  return avail;
	  }
	  
	  
	  public void UpdatePassword_Patron(String password,String first, String last ) {
		  try(Connection conn= ConnectionManager.getConnection();
		  PreparedStatement pstmt = conn.prepareStatement(UPDATE_PASSWORD_PATRON) ) 
	  {
		
			pstmt.setString(1, password);
			pstmt.setString(2, first);
			pstmt.setString(3, last);
			pstmt.executeQuery(); 
			 
	  }catch(SQLException e) {
		  e.printStackTrace();
	  }catch(ClassNotFoundException e) {
		  e.printStackTrace();
	  }
			  
	  }
	  
	  public void UpdatePasswordLibrarian(String user, int id) {
		  try(Connection conn= ConnectionManager.getConnection();
				  PreparedStatement pstmt = conn.prepareStatement(UPDATE_PASSWORD_LIBRARIAN) ) 
			  {
			  pstmt.setString(1, user );
			  pstmt.setInt(2, id);
			  pstmt.executeQuery();
			  
			  }catch(SQLException e) {
				  e.printStackTrace();
			  }catch(ClassNotFoundException e) {
				  e.printStackTrace();
			  }
	  }
	  
	  public void UpdateUsernameLibrarian(String user, int id) {
		  try(Connection conn= ConnectionManager.getConnection();
				  PreparedStatement pstmt = conn.prepareStatement(UPDATE_USERNAME_LIBRARIAN) ) 
			  {
		  
			  pstmt.setString(1, user);
			  pstmt.setInt(2, id);
			  pstmt.executeQuery();
			  
			  }catch(SQLException e) {
				  e.printStackTrace();
			  }catch(ClassNotFoundException e) {
				  e.printStackTrace();
			  }
	  }
	  
	  public void UpdateUsernamePatron(String username,String fname,String lname) {
		  
		  try(Connection conn= ConnectionManager.getConnection();
				  PreparedStatement pstmt = conn.prepareStatement(UPDATE_USERNAME_PATRON) ) 
			  {
			   
			  pstmt.setString(1, username);
			  pstmt.setString(2, fname);
			  pstmt.setString(3, lname);
			  pstmt.executeQuery();
			  
			  }catch(SQLException e) {
				  e.printStackTrace();
			  }catch(ClassNotFoundException e) {
				  e.printStackTrace();
			  }
		  
	  }
	  
	  public void FreezeAccount() {
		  try(Connection conn= ConnectionManager.getConnection();
				  PreparedStatement pstmt = conn.prepareStatement(FREEZE_ACCOUNT) ) 
			  {
			   
			  pstmt.setBoolean(1, true);
			  pstmt.executeQuery();
			  
	  	 }catch(SQLException e) {
			  e.printStackTrace();
		  }catch(ClassNotFoundException e) {
			  e.printStackTrace();
		  }
	  }
	  
	  
	  
	  public void approveAccount() {
		  
		  try(Connection conn= ConnectionManager.getConnection();
		  PreparedStatement pstmt = conn.prepareStatement(APPROVE_ACCOUNT) ) 
	  {
			  boolean val=false;
			  pstmt.setBoolean(1,val);
			  pstmt.executeQuery();
			  
	  }catch(SQLException e) {
		  e.printStackTrace();
	  }catch(ClassNotFoundException e) {
		  e.printStackTrace();
	  }
	  }
	  
	  public void updateDescription(String descr, String isbn) {
		  try(Connection conn= ConnectionManager.getConnection();
		  PreparedStatement pstmt = conn.prepareStatement(UPDATE_DESCRIPTION) ) 
	  {
			  pstmt.setString(1, descr);
			  pstmt.setString(2, isbn);
			  pstmt.executeQuery();
			  			  
	  }catch(SQLException e) {
		  e.printStackTrace();
	  }catch(ClassNotFoundException e) {
		  e.printStackTrace();
	  }
	  }
	  
	  
	  
	  public void updateTitle(String title, String isbn) {
		  
		  try(Connection conn= ConnectionManager.getConnection();
				  PreparedStatement pstmt = conn.prepareStatement(UPDATE_TITLE) ) 
			  {
			  
			  pstmt.setString(1, title);
			  pstmt.setString(2, isbn);
			  pstmt.executeQuery();
			  
			  }catch(SQLException e) {
				  e.printStackTrace();
			  }catch(ClassNotFoundException e) {
				  e.printStackTrace();
			  }
	  }
	  
	  
	  
	  public void deleteBook(String isbn) {
		  
		  try(Connection conn= ConnectionManager.getConnection();
				  PreparedStatement pstmt = conn.prepareStatement(DELETE_BOOK) ) 
			  {
			  
			  pstmt.setString(1, isbn );
			  pstmt.executeQuery();
			  
			  
			  }catch(SQLException e) {
				  e.printStackTrace();
			  }catch(ClassNotFoundException e) {
				  e.printStackTrace();
			  }
		  
	  }
	  
	  
	  public void SignUpPatron(String first_name,String last_name,String username,String password,boolean account_frozen) {
		  
		  try(Connection conn= ConnectionManager.getConnection();
				  PreparedStatement pstmt = conn.prepareStatement(SIGN_UP_PATRON) ) 
			  {
		  
			 pstmt.setString(1,"first_name");
			 pstmt.setString(2,"last_name");
			 pstmt.setString(3,"username");
			 pstmt.setString(4,"password");
			 pstmt.executeQuery();
			  
			  
			  }catch(SQLException e) {
				  e.printStackTrace();
			  }catch(ClassNotFoundException e) {
				  e.printStackTrace();
			  }
		  
	  }
	  
	  
	  public void CheckOutBook(String title) {
			 try(Connection conn= ConnectionManager.getConnection();
					  PreparedStatement pstmt = conn.prepareStatement(CHECK_OUT_BOOK) ) 
				  {
				 
				 boolean avail= isBookAvail(title);
				 if(avail==true) {
					 System.out.println("book is unavailable");
				 }else {
					 pstmt.setString(1, title);
					 pstmt.executeQuery();
				 }
				  
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		}
	  
	  public List<Book> getBookList(){
		 
		  List<Book> bookList = new ArrayList<Book>();
	
		  try(Connection conn= ConnectionManager.getConnection();
		  PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_BOOKS);
          ResultSet rs = pstmt.executeQuery() ) 
	  { 
		  while(rs.next())
		  {
			  String isbn = rs.getString("isbn");
			  String title= rs.getString("title");
			  String descr = rs.getString("descr");
			  boolean rented=rs.getBoolean("rented");
			  String dateAdded=rs.getString("added_to_library");
			  
			  bookList.add(new Book(isbn,title,descr,rented,dateAdded));   
		  }
		  
	  }catch(SQLException e){
		  e.printStackTrace();
	  } catch (ClassNotFoundException e1) {
		e1.printStackTrace();
	}
	return bookList;
	  }//end getBookList
	  

	  public List<Patron> getAllPatrons(){
		  
		  List<Patron> allPatrons= new ArrayList<Patron>();
		  
		  try(Connection conn= ConnectionManager.getConnection();
				  PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_PATRONS);
		          ResultSet rs = pstmt.executeQuery() ) 
			  {
		  while(rs.next())
		  {
			  int id = rs.getInt("patron_id");
			  String fname= rs.getString("first_name");
			  String lname = rs.getString("last_name");
			  String user=rs.getString("username");
			  String pass=rs.getString("password");
			  boolean frozen=rs.getBoolean("account_frozen");
			  
			  allPatrons.add(new Patron(id,fname,lname,user,pass,frozen)); 
		  }
 
	  }catch(SQLException e){
		  e.printStackTrace();
		  } catch (ClassNotFoundException e1) {
		e1.printStackTrace();
	}
		  return allPatrons;
	}//end getAllPatrons()
	  
	  
	public void addNewBook(String isbn,String title, String descr,Boolean rented,String added_to_library) {
		
		 try(Connection conn= ConnectionManager.getConnection();
				  PreparedStatement pstmt = conn.prepareStatement(ADD_NEW_BOOK) ) 
			  {
			 
			 	pstmt.setString(1, isbn);
			 	pstmt.setString(2, title);
			 	pstmt.setString(3, descr);
			 	pstmt.setBoolean(4, rented);
			 	pstmt.setString(5, added_to_library);
			 	pstmt.executeQuery();
			 			
			  } catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
	}
	  
	

	

}
