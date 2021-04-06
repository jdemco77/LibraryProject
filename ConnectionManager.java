package FInalPackageConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

		private static Connection connection = null;
		private static final String URL = "jdbc:mysql://localhost:3306/Library_Database";
		private static final String USERNAME = "root";
		private static final String PASSWORD = "root";
		
		
		private static void makeConnection(){
		
			try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			}catch(SQLException e ) {
				e.printStackTrace();
			}catch(ClassNotFoundException e) {
				e.printStackTrace();​
			}
		}
		
		public static Connection getConnection() throws ClassNotFoundException, SQLException {
	
			if (connection == null) {
				makeConnection();
			}

			return connection;
		}//end getConnection
	
	}//end connection manager
	
