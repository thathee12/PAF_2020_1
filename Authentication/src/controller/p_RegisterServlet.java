package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import been.p_registerBeen;
import dao.p_RegisterDao;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class p_RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public p_RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
	
		String firstname = request.getParameter("firstname");
		 String lastname = request.getParameter("lastname");
		 String email = request.getParameter("email");
		 String mobile = request.getParameter("mobile");
		 String Address = request.getParameter("Address");
		 String NIC = request.getParameter("NIC");
		 String password = request.getParameter("password");
		 String conpassword = request.getParameter("conpassword");
		 
		 p_registerBeen registerBean = new p_registerBeen();
		//Using Java Beans - An easiest way to play with group of related data
		 registerBean.setFirstname(firstname);
		 registerBean.setLastname(lastname);
		 registerBean.setEmail(email);
		 registerBean.setMobile(mobile);
		 registerBean.setAddress(Address);
		 registerBean.setnIC(NIC);
		 registerBean.setPassword(password); 
		 registerBean.setConpassword(conpassword); 
		 
		 p_RegisterDao p_RegisterDao = new p_RegisterDao();
		 

		//The core Logic of the Registration application is present here. We are going to insert user data in to the database.
		int userRegistered=p_RegisterDao.registerUser(registerBean);
		 
		 if(userRegistered>0)   //On success, you can display a message to user on Home page
		 {
			request.getRequestDispatcher("/Home.jsp").forward(request, response);
		 }
		 else   //On Failure, display a meaningful message to the User.
		 {
			request.setAttribute("errMessage", userRegistered);
			request.getRequestDispatcher("/Register.jsp").forward(request, response);
		 }
	}
	

}
