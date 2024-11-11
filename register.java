package in.sp.backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.naming.ldap.Rdn;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/register")
public class register extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String pass = req.getParameter("pass");
		String contact = req.getParameter("contact");
		

		RequestDispatcher rd = req.getRequestDispatcher("registration.jsp");

		PrintWriter out = resp.getWriter();
		try {
	        Class.forName("com.mysql.cj.jdbc.Driver");

	        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webproject", "root", "root");
	        PreparedStatement ps = connection.prepareStatement("insert into register values(?,?,?,?)");
			
	        ps.setString(1, name);
	        ps.setString(2, email);
	        ps.setString(3, pass);
	        ps.setString(4, contact);
	        
	        int i = ps.executeUpdate();
	        
	        if (i > 0) {
	        	req.setAttribute("status", "Success");
			}else {
	        	req.setAttribute("status", "Failed");
			}
	        rd = req.getRequestDispatcher("registration.jsp");
	        rd.forward(req, resp);
		} catch (Exception e) {
			resp.setContentType("type/html");
			out.print("Error occure");
			rd.include(req, resp);
		}
	}
}
