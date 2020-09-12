package com;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHandler {
	
	public Connection establishConnection() throws ClassNotFoundException, SQLException, IOException {
		FileReader reader = new FileReader("db.properties");
		Properties p = new Properties();
		p.load(reader);
		String driver = p.getProperty("Driver");
		String url = p.getProperty("url");
		String username = p.getProperty("username");
		String password = p.getProperty("password");
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url,username,password);
		return con;
	}
	
}
