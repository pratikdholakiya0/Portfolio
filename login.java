package in.sp.backend;

import java.awt.Color;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.Session;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/login")
public class login extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String input[] = new String[2];
		input[0] = req.getParameter("username");
		input[1] = req.getParameter("password");
		
		RequestDispatcher rd = null;

		PrintWriter out = resp.getWriter();
		try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webproject", "root", "root");
	        
	        PreparedStatement ps = connection.prepareStatement("select uemail,upass,uname from register where uemail = ? and upass = ?;");
	        ps.setString(1, input[0]);
	        ps.setString(2, input[1]);
	        
	        ResultSet rs = ps.executeQuery();

	        String info[] = new String[3];
	        while (rs.next()) {
	            info[0] = rs.getString("uemail");
	            info[1] = rs.getString("upass");
	            info[2] = rs.getString("uname");
			}

			resp.setContentType("text/html");
	        if (input[0].equals(info[0]) && input[1].equals(info[1])){
	        	HttpSession session = req.getSession();
	        	resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, proxy-revalidate");
	            resp.setDateHeader("Expires", 0);
	        	session.setAttribute("name", info[2]);
	        	resp.sendRedirect("index.jsp");
	        }else {
				out.print("<h3 style='color:red; text-align:center;'>Incorrect username or password!!</h3>");
	        	rd = req.getRequestDispatcher("login.jsp");
	            rd.include(req, resp);
	        }	        
		} catch (Exception e) {
			out.print("Error");
			rd = req.getRequestDispatcher("login.jsp");
			rd.include(req, resp);
		}
	}
}
