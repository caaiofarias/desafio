package com.desafio.rest.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class H2Connection {
	
	   static final String JDBC_DRIVER = "org.h2.Driver";
	   static final String INIT_DB_URL = "jdbc:h2:mem:test_db;INIT=RUNSCRIPT FROM '/home/caio/Desktop/JerseyDemos/src/resources/script.sql';DB_CLOSE_DELAY=-1\n";
	   static final String DB_URL = "jdbc:h2:mem:teste"; 
	   static final String USER = "sa"; 
	   static final String PASS = ""; 
	   
	   public static Connection getConnection() { 
		      Connection conn = null; 
		      Statement stmt = null; 
		      try { 
		         Class.forName(JDBC_DRIVER);
		         conn = DriverManager.getConnection(DB_URL,USER,PASS);  
		         stmt = conn.createStatement();
		         String sqlPhone =  "CREATE TABLE IF NOT EXISTS PHONE " + 
				            "(id INTEGER not NULL AUTO_INCREMENT, " + 
				            " ddd INTEGER, " +
				            " fk INTEGER, "  +
				            " number VARCHAR(9), " +  
				            " type VARCHAR(255), " +  
				            " PRIMARY KEY ( id ), " +
				            " FOREIGN KEY(fk) REFERENCES USER(id)" +
				            ")";  
		         String sql =  "CREATE TABLE IF NOT EXISTS USER " + 
		            "(id INTEGER not NULL AUTO_INCREMENT, " +
		            " name VARCHAR(255), " +  
		            " email VARCHAR(255) unique, " +  
		            " password VARCHAR(255), " +
		            " token VARCHAR(255), " +
		            " PRIMARY KEY ( id ), " +
		            ")";
		         String insert = "MERGE INTO USER \n"
		         		+ "  KEY(ID) \n"
		         		+ "VALUES (1, 'caio', 'caiofariaspe@gmail.com', 'admin', 'admin');";
		         stmt.executeUpdate(sql);
		         stmt.executeUpdate(sqlPhone);
		         stmt.executeUpdate(insert);
		         stmt.close(); 
		      } catch(SQLException se) { 
		         se.printStackTrace(); 
		      } catch(Exception e) { 
		         e.printStackTrace(); 
		      } finally { 
		         try{
		            if(stmt!=null) stmt.close(); 
		         } catch(SQLException se2) { 
		         } 
		      }
			return conn;
	   }
	   public static void main(String[] args) {
		   H2Connection.getConnection();
	}
}


