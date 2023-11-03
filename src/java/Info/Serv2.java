package Propkg;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Serv2 extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PrintWriter p = response.getWriter();
            response.setContentType("text/html");
            PrintWriter out=response.getWriter();
            String uname=request.getParameter("username");
            String pass=request.getParameter("password");
            int state = Propkg.VerifyUser.verify(uname, pass);
            if(state>0)
            {
                HttpSession s =request.getSession();
                s.setAttribute("user", uname);
                RequestDispatcher rd=request.getRequestDispatcher("Home.html");
		rd.include(request, response);
            }
            else
                p.print("<br><br><body style=\"background-color:#90e0ef;\">\n" +
"    <center><br><div style=\"background: #ffffff;\n" +
"                                  width: 500px;\n" +
"                                  font-size : 16px;\n" +
"                                  padding: 10px; \n" +
"         	                border: 4px solid black; \n" +
"                                 margin: 45px;\"<B><font size=\"5\" style=\"color:red\">ERROR!! </font><br><br><font size=\"4\">"+info.VerifyUser.msg+"</font> <br><br> <center><a href = \"index.html\"> Go back to LogIn Page.</a></center></body>");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Serv2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}