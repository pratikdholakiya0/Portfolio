package in.sp.backend;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.PseudoColumnUsage;

import javax.naming.ldap.Rdn;

import org.apache.tomcat.util.http.fileupload.MultipartStream;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;


@WebServlet("/profile")
@MultipartConfig
public class profile extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException  {
		
		
//		PrintWriter outPrintWriter = resp.getWriter();
//		outPrintWriter.print("yayaya");
//		outPrintWriter.print(path);
		PrintWriter out = resp.getWriter();
		
		String name = req.getParameter("name");
		String about = req.getParameter("about");	
		
		Part part = req.getPart("image");
        if (part != null) {
            try {
            	Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/webproject","root","root");

                PreparedStatement ps = connection.prepareStatement("insert into profiledata(img_name, profilepic,about, name) values(?, ?, ?, ?)");
                InputStream is = part.getInputStream();
                ps.setString(1, part.getName());
                ps.setBlob(2, is);
                ps.setString(3, about);
                ps.setString(4, name);
                
                RequestDispatcher rd = req.getRequestDispatcher("profile.jsp");
                resp.setContentType("text/html");
                int result = ps.executeUpdate();
                if (result > 0) {
    	        	req.setAttribute("data", "updated");
                    rd.forward(req, resp);
                } else {
    	        	req.setAttribute("data", "fail");
                    rd.forward(req, resp);
                }
            } catch (Exception e) {
                out.println(e);
            }
        }
	}
}
