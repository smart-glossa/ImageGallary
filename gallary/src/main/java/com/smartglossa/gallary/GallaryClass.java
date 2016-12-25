package com.smartglossa.gallary;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.fileupload.FileItem;
import org.json.JSONArray;
import org.json.JSONObject;

public class GallaryClass {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	PreparedStatement ps = null;

	public GallaryClass() throws ClassNotFoundException, SQLException, IOException {
		openConnection();
	}

	public void addUser(String name, String uName, String pass, String address, String age, String mobNo, FileItem file)
			throws SQLException, ClassNotFoundException, IOException {
		try {
			String query = "Insert into user(name,uName,password,address,age,mobileNumber) values('" + name + "', '"
					+ uName + "', '" + pass + "','" + address + "','" + age + "','" + mobNo + "')";
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

	public Blob getAllImages() throws ClassNotFoundException, SQLException {
		JSONArray res = new JSONArray();
		try {
			String query = "select *from image";
			rs = stmt.executeQuery(query);
			Blob b = null;
			while (rs.next()) {
				b = rs.getBlob("image");
				String uname = rs.getString("uName");
				String query1 = "Select * from user where uName='" + uname;
				rs = stmt.executeQuery(query1);
				while (rs.next()) {
					JSONObject obj = new JSONObject();
					obj.put("name", rs.getString("name"));
					obj.put("address", rs.getString("address"));
					obj.put("age", rs.getString("age"));
					obj.put("mobileNo", rs.getString("mobileNumber"));
					res.put(obj);
				}
			}
			return b;
		} finally {
			closeConnection();
		}
	}

	public JSONObject login(String uname, String pass) throws ClassNotFoundException, SQLException {
		try {
			JSONObject obj = new JSONObject();
			String query = "select name from user where uname='" + uname + "'AND pass='" + pass + "'";
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				obj.put("name", rs.getString(1));
			}
			return obj;
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
