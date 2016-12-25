package com.smartglossa.gallary;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.fileupload.FileItem;

public class GallaryClass {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement ps = null;

	public GallaryClass() throws ClassNotFoundException, SQLException, IOException {
		openConnection();
	}
	 public void addUser(String name, String uName, String pass,String address,String age, String mobNo,FileItem file)
	            throws SQLException, ClassNotFoundException, IOException {
	        try {
	            String query = "Insert into user(name,uName,password,address,age,mobileNumber) values('" + name + "', '" + uName + "', '" + pass + "','" +address+ "','"+ age +"','"+ mobNo +"')";
	            stmt.execute(query);

	            ps = conn.prepareStatement("insert into image(pimage,uName) values(?,?)");
	            ps.setString(2, uName);
	            // size must be converted to int otherwise it results in error
	            ps.setBinaryStream(1, file.getInputStream(), (int) file.getSize());
	            ps.executeUpdate();
	        } finally {
	            closeConnection();
	        } 

	    }

	private void openConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(
				"jdbc:mysql://" + GallaryConstants.MYSQL_SERVER + "/" + GallaryConstants.DATABASE,
				GallaryConstants.USERNAME, GallaryConstants.PASSWORD);
		stmt = conn.createStatement();
	}

	private void closeConnection() throws SQLException {
		if (conn != null) {
			conn.close();
		}
		if (stmt != null) {
			stmt.close();
		}
		if (ps != null) {
			ps.close();
		}
		if (rs != null) {
			rs.close();
		}
	}
}
