# LibraryProject
	Assignment: 
	Library Project
	Project will use JDBC, Servlets, JSPs, and Maven
	Create website for a library where you can login as...
	◆A librarian who will manage books and approve patron accounts
	◆A patron who can checkout and return books

	Patron User

	Sign up for an account
	◆Pass info for account in a form
	◆Account will be “frozen” once created and will not be able to checkout any books
	◆Librarian will have to unfreeze account➔Log into their account to... 
	◆Checkout books (as long as they’re available)
	◆Return books
	◆View all books previously checkout out as well as current books checkout out
	◆List of books at the library➔Update their name, username, and password

	Librarian User

	Add in new books
	◆Must have false set for rented column

	◆Must have today’s date for added_to_library column
	➔Update only the book’s title and description
	➔Approve accounts for patrons

	◆Change their accounts from frozen to unfrozen so they can checkout books
	➔Update their username and password
