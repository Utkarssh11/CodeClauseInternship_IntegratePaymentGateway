package Propkg;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VerifyUser {
    public VerifyUser() {
    }      
    public static void main(String[] args) 
    {
        System.out.println("Main Method");
    }
    static String msg;
    public static int verify(String userid,String passw) throws ClassNotFoundException
    {
       
        try{
            Class.forName("com.mysql.jdbc.Driver");            
	    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bidhan","root","Bidhan@1");
            Statement st=con.createStatement();
            try{
            ResultSet r =st.executeQuery("Select * from customer where username = '"+userid+"'");
            int i=0;
            
           while(r.next())
           {
               i=1;
               if(passw.equals(r.getString("password")))
            {
                return 1;
            }     
           }
           if(i==0)
           {
               msg="The username doesn't exist!";
               st.close();
                return -1;
           }
            }
            finally
            {
                st.close();
            }
          	
	} catch (SQLException e) {
		e.printStackTrace();
	}
         msg="The password and username doesn't match";
                return -1;
	
    }
    
    
}
