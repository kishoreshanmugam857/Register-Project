package com.abc.controlller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abc.model.customer;
import com.abc.model.customerCRUD;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public register() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd;
		try {
			customer newcustomer = new customer();
			newcustomer.setFname(request.getParameter("first_name"));
			newcustomer.setLname(request.getParameter("last_name"));
			newcustomer.setDname(request.getParameter("display_name"));
			newcustomer.setEmailid(request.getParameter("email"));
			String pass = request.getParameter("user_password").toString();
			String cpass = request.getParameter("password_confirmation").toString();

			if (pass.equals(cpass)) {
				newcustomer.setuser_Password(pass);
				customerCRUD c = new customerCRUD();
				if (c.addcustomer(newcustomer)) {

					request.setAttribute("status", true);
					request.setAttribute("message", "inserted successfully");
					request.setAttribute("newcustomer", new customer());
				} else {

					request.setAttribute("status", true);
					request.setAttribute("message", "not inserted successfully");
					request.setAttribute("newcustomer", newcustomer);

				}
			} else {

				request.setAttribute("status", true);
				request.setAttribute("message", "password and confirm password are not matched");
				request.setAttribute("newcustomer", newcustomer);
			}
		}

		catch (Exception e) {
			if (e.getMessage().contains("primary key")) {
				request.setAttribute("status", true);
				request.setAttribute("message", "emailid already exists");
				System.err.println(e.getMessage());
			} else {
				request.setAttribute("status", true);
				request.setAttribute("message", "server error");
				System.err.println(e.getMessage());
			}
		}
		rd = request.getRequestDispatcher("views/index.jsp");
		rd.forward(request, response);
	}
}
