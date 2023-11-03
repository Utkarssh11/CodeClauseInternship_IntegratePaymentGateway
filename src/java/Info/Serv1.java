package Propkg;

import java.io.IOException;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.ServletException;
import java.sql.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Serv1 extends HttpServlet {
  
    public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
                    
                    response.setContentType("text/html");                
		PrintWriter out=response.getWriter();		
		String uname=request.getParameter("uname");
		String pass=request.getParameter("pass");
                String name=request.getParameter("name");
		String email=request.getParameter("email");
                String phno=request.getParameter("phno");
		String bank=request.getParameter("Bank");
                String accno=request.getParameter("accno");
                int stat = Propkg.AddUser.register(uname, pass, name, email, phno, bank, accno);
                   
		if(stat>0){
			out.print("<marquee width=\"80%\" direction=\"left\" height=\"5%\"><font size=\"5\">You have Registered as a user.Your wallet has a Balance of Rs.1000.</font></marquee> ");
                        RequestDispatcher rd=request.getRequestDispatcher("Home.html");
		        rd.include(request, response);
		}
		else{
                    HttpSession s =request.getSession();
                s.setAttribute("user", uname);
                    out.println("<body style=\"background-color:#90e0ef;\">\n" +
"    <center><br><br><br><br><div style=\"background: #ffffff;\n" +
"                                  width: 500px;\n" +
"                                  font-size : 16px;\n" +
"                                  padding: 10px; \n" +
"         	                border: 4px solid black; \n" +
"                                 margin: 45px;\"><br><B><font size =\"5\"> Sorry,Registration failed.</font></B><br>");
                    out.println("<br><font size = \"4\">"+info.AddUser.msg+"</font>");
                    out.println("<br><br><a href=\"NewUserReg.html\"><font size=\"4\">Go Back to Registration Page</a><br>"
                            + "<br> or <br><br><a href=\"index.html\"><font size=\"4\">Go Back to Login Page</a></center> <br><br></body>");
		}
                }
                catch (Exception e)
                {
                    System.out.println(e);
                }
		
		
	}  
    

}
