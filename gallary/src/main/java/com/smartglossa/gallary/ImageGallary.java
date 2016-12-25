package com.smartglossa.gallary;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

public class ImageGallary extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload sfu = new ServletFileUpload(factory);
		String op = request.getParameter("operation");
		if (op.equals("addUser")) {
			// int userId = Integer.parseInt(request.getParameter("uId"));
			String name = request.getParameter("name");
			String uName = request.getParameter("uname");
			String pass = request.getParameter("pass");
			String address = request.getParameter("add");
			String age = request.getParameter("age");
			String mobNo = request.getParameter("mobileNumber");
			JSONObject obj = new JSONObject();
			try {
				List<FileItem> items = sfu.parseRequest(request);
				FileItem file = (FileItem) items.get(0);
				GallaryClass gall = new GallaryClass();
				gall.addUser(name, uName, pass, address, age, mobNo, file);
				obj.put("status", 1);
			} catch (Exception e) {
				e.printStackTrace();
				obj.put("status", 0);
				obj.put("message", e.getMessage());
			}
			response.getWriter().print(obj);
		} else if (op.equals("getAllImages")) {

		} else if (op.equals("login")) {
			String uname = request.getParameter("user");
			String pass = request.getParameter("passw");
			JSONObject obj = new JSONObject();
			try {
				GallaryClass gall = new GallaryClass();
				gall.login(uname, pass);
				obj.put("status", "1");
			} catch (Exception e) {
				obj.put("status", "0");
				e.printStackTrace();
			}
			response.getWriter().print(obj);
		}
	}

}
