package Propkg;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomePage extends HttpServlet { 
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter pr = response.getWriter();
            HttpSession s = request.getSession();
            String Sname = (String)s.getAttribute("user");
            String process=request.getParameter("Process");
            
            if(process.equals("Submit"))
            {
               String Rname=request.getParameter("Rname");
            String Rphn=request.getParameter("Rphn");
            String pass=request.getParameter("pass");
            
            String amnt = request.getParameter("amnt");
            double amt=Double.parseDouble(amnt); 
            
            int sta = info.Processes.transaction(Sname,pass,Rname,Rphn,amt);
            pr.print("<body style=\"background-color:#90e0ef;\">\n" +
"    <center><br><div style=\"background: #ffffff;\n" +
"                                  width: 500px;\n" +
"                                  font-size : 16px;\n" +
"                                  padding: 10px; \n" +
"         	                border: 4px solid black; \n" +
"                                 margin: 45px;\"</center> ");
            if(sta<0)
            {
                pr.println("<br><B><font size=\"5\" style=\"color:red\">ERROR!!</font><br><br>"+info.Processes.mesg);
                pr.print("<br><br><br><center><a href = \"Transaction.html\"><font size=\"4\"> Go back to Transaction Page.</a></center></body><br> or <br><br>"
                        + " <center><a href = \"Home.html\"><font size=\"4\"> Go back to Home Page.</a></center></body><br>");
            }
            else
            {
                pr.println("<br><font style=\"color:green\">"+info.Processes.mesg+"</font>");
                pr.print("<br><br> <center><a href = \"Home.html\"> <font size=\"4\">Go back to Home Page.</a></center></body><br>");
                
            } 
            }
            if(process.equals("View Transaction History"))
            {
            
            int sts=Propkg.Processes.tran_History(Sname);
            if(sts<0)
            {
                pr.print("<body style=\"background-color:#90e0ef;\">\n" +
"    <center><br><div style=\"background: #ffffff;\n" +
"                                  width: 500px;\n" +
"                                  font-size : 16px;\n" +
"                                  padding: 10px; \n" +
"         	                border: 4px solid black; \n" +
"                                 margin: 45px;\"</center> ");
            }
            else
            {
            pr.print(info.Processes.messg);
            pr.print("<form action=\"Home.html\"><input type=\"submit\" value= \"Go back to Home Page.\" ><br></div></center> </body>");
            }
            }
            if(process.equals("View Account Balance"))
            {
                int status = info.Processes.Balance(Sname);
                if(status>0)
                {
                    pr.print(info.Processes.messg);
                    pr.print("<form action=\"Home.html\"><input type=\"submit\" value= \"Go back to Home Page.\" ><br></div></center> </body>");
                }
                else
                {
                    pr.print("<body style=\"background-color:#90e0ef;\">\n" +
"    <center><br><div style=\"background: #ffffff;\n" +
"                                  width: 500px;\n" +
"                                  font-size : 16px;\n" +
"                                  padding: 10px; \n" +
"         	                border: 4px solid black; \n" +
"                                 margin: 45px;\"</center> ");
                   pr.print("Sorry!!!The process failed. Cannot display Balance"); 
                   pr.print("<form action=\"Home.html\"><input type=\"submit\" value= \"Go back to Home Page.\" ><br>");
                }
            }
            
            } catch (Exception ex) {
            System.out.print(ex.getMessage());
        } 
        
            
        
        
    }

}
