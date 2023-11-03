package Propkg;

import java.sql.*;
import java.io.*;
public class AddUser {

    public AddUser() {
        
    }
      
    public static void main(String[] args) 
    {
        System.out.println("Main Method");
    }
    static String msg; 
    public static int register(String username,String password,String name,String email,String phno,String bank,String accno) throws SQLException
    {       
        int status=0;
        try {  
            double minbal=1000.0;
            Class.forName("com.mysql.jdbc.Driver");            
	    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bidhan","root","Bidhan@1");
            Statement st = con.createStatement();
            status = st.executeUpdate("insert into customer(username,password,Name,email_id,Phone,Bank,Accno,Acc_Balance) values('"+username+"','"+password+"','"+name+"','"+email+"','"+phno+"','"+bank+"','"+accno+"','"+minbal+"')");    
            
            st.close();
                
        } catch (Exception e) {
            msg=e.getMessage();
            e.printStackTrace();
                         
        }
        
        return status;
    } 
    
    
}
