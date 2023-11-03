package Propkg;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Processes {

    public Processes() {
    }
    public static void main(String[] args)
    {
        
    }
    static String mesg,messg;
    public static int transaction(String Sname,String pass,String n,String to,double amt) throws ClassNotFoundException
    {       
        try{               
        
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bidhan","root","Bidhan@1");
        Statement st=con.createStatement();
        Statement st1=con.createStatement();
        double baldeb ,balcred;
        
        int i=0;
               
        ResultSet rsa = st1.executeQuery("select * from customer where username = '"+Sname+"'");

        while( rsa.next())
        {
            if(!pass.equals(rsa.getString("password")))
            {
                mesg="<B><font size=\"5\">Transaction Cannot be processed. </font></B><br><br><font size=\"4\">Your password doesn't match!!</font>";
                return -1;
            }     
            ResultSet rs = st.executeQuery("select * from customer where Phone = '"+to+"'");
        while(rs.next())
        {
            i=1;
           if(!(n.equalsIgnoreCase(rs.getString("username"))))
        {
             mesg = "<B><font size=\"5\">Transaction Cannot be processed. </font></B><br><br><font size=\"4\">The phone number and userame of the recepient does not match</font>";
             st.close();
             st1.close();
             return -1;
        }
        
        
        baldeb = rsa.getDouble("Acc_Balance");
        if(baldeb<amt)
        {
            mesg="<B><font size=\"4\">Insufficient Funds to process the transaction.</font></B><br>";
            st.close();
            st1.close();
            return -1;
        }
        baldeb-=amt;
           
        String debt1=rsa.getString("Transaction2");
        Double debtamt1 = rsa.getDouble("Transaction2amt");
        String debtcust1 = rsa.getString("Transaction2cust");
        String debt2=rsa.getString("Transaction3");
        Double debtamt2 = rsa.getDouble("Transaction3amt");
        String debtcust2 = rsa.getString("Transaction3cust");
        String debt3="Debited  from your account.";
        Double debtamt3 = amt;
        String debtcust3= rs.getString("username");
        
        
        String credt1=rs.getString("Transaction2");
        Double credamt1 = rs.getDouble("Transaction2amt");
        String credcust1 = rs.getString("Transaction2cust");
        String credt2=rs.getString("Transaction3");
        Double credamt2 = rs.getDouble("Transaction3amt");
        String credcust2 = rs.getString("Transaction3cust");
        String credt3="Credited to your account.";
        Double credamt3 = amt;
        String credcust3 = rsa.getString("username");
        
        balcred=rs.getDouble("Acc_Balance");       
        balcred+=amt;
        StringBuffer str = new StringBuffer("update customer set Acc_Balance = '"+baldeb+"' where username = '"+Sname+"'");
        System.out.println(str.toString());
        int a = st.executeUpdate(str.toString());
        str = new StringBuffer("update customer set Acc_Balance = '"+balcred+"' where Phone = '"+to+"'");
        int b = st.executeUpdate(str.toString());
        if(a==0 || b==0)
        {
            mesg = "<B><font size=\"5\">Transaction Failed ! Try Again</font>";
            st.close();
            st1.close();
            return -1;
        }
        else            

        str = new StringBuffer("update customer set Transaction1 = '"+debt1+"' ,Transaction1amt = '"+debtamt1+"' ,Transaction1cust = '"+debtcust1+"' ,Transaction2 = '"+debt2+"' ,Transaction2amt = '"+debtamt2+"' ,Transaction2cust = '"+debtcust2+"' ,Transaction3 = '"+debt3+"' ,Transaction3amt = '"+debtamt3+"' ,Transaction3cust = '"+debtcust3+"' where username = '"+Sname+"'");
        
        int c = st.executeUpdate(str.toString());
        str = new StringBuffer("update customer set Transaction1 = '"+credt1+"' ,Transaction1amt = '"+credamt1+"' ,Transaction1cust = '"+credcust1+"' ,Transaction2 = '"+credt2+"' ,Transaction2amt = '"+credamt2+"' ,Transaction2cust = '"+credcust2+"' ,Transaction3 = '"+credt3+"' ,Transaction3amt = '"+credamt3+"' ,Transaction3cust = '"+credcust3+"' where Phone = '"+to+"'");
        
        int d = st.executeUpdate(str.toString());
        if(c==0 || d==0)
        {
            mesg ="<B><font size=\"5\">Transaction was Successful but couldnot update Transaction History</font>";
        }
        else            
            mesg = "<B><font size=\"5\">Transaction Successful!!! </font>";
        st.close();
        st1.close();
        return 1;
        } 
            }
        if(i==0)
        {
            mesg = "<br><br><font size=\"4\">The given phone number does not belong to any registered user</font>";
            st.close();
            st1.close();
            return -1;
        }
        
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
	}    
        
        return -1;
    }
    
    public static int tran_History(String us)throws SQLException, ClassNotFoundException
    {
        
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bidhan","root","Bidhan@1");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from customer where username= '"+us+"'");
        int i=0;
        while(rs.next())
        {
            i=1;
            
            messg="<head> \n" +
"     <style> \n" +
"         table, th, td { \n" +
"            border: 1px solid black; \n" +
"            border-collapse: collapse; \n" +
"         }\n" +
"         th, td { \n" +
"            padding: 20px;\n" +
"         }\n" +
"         </style>\n" +
"</head> \n" +
"<body style=\"background-color:#90e0ef;\"> \n" +
"         <br><br>\n" +
"        <center> \n" +
"        <B><br><br><center><h1 style=\"color:black;\" ><i>Pass-Book</i></center></B>\n" +
"               <div style=\"background: #ffffff;\n" +
"                                  width: 500px;\n" +
"                                  height: 255px;\n" +
"                                  font-size : 16px; \n" +
"                                  padding: 20px; \n" +
"                        border: 4px solid black; \n" +
"                                 margin: 45px;\"> \n" +
"                <table style=\"width:100%\"> \n" +
"               <tr>\n" +
"                     <th><B><center>Type of Transaction</center></B></td> \n" +
"                     <th><B><center>Amount</center></B></td>\n" +
"                     <th><B><center>To/From</center></B></td> \n" +
"               </tr>\n" +
"               <tr> \n" +
"                      <td><center>"+rs.getString("Transaction3")+"</center></td>   \n" +
"                      <td><center>"+rs.getString("Transaction3amt")+"</center></td> \n" +
"                      <td><center>"+rs.getString("Transaction3cust")+"</center></td>  \n" +
"               </tr> \n" +
"                     <td><center>"+rs.getString("Transaction2")+"</center></td>\n" +
"                     <td><center>"+rs.getString("Transaction2amt")+"</center></td>\n" +
"                     <td><center>"+rs.getString("Transaction2cust")+"</center></td>   \n" +
"               </tr>\n" +
"               <tr> \n" +
"                      <td><center>"+rs.getString("Transaction1")+"</center></td>\n" +
"                      <td><center>"+rs.getString("Transaction1amt")+"</center></td>\n" +
"                      <td><center>"+rs.getString("Transaction1cust")+"</center></td>  \n" +
"               </tr>\n" +
"               </table> \n" +
"               </div>  \n" +
"               </table> \n" ;
        
        }
        if(i==0)
        {
            mesg = "Unable to upload History";
        }
        st.close();
       return 1; 
    }
    public static int Balance(String user)throws SQLException, ClassNotFoundException
    {
        
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bidhan","root","Bidhan@1");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from customer where username= '"+user+"'");
        while(rs.next())
        {
            messg = "<head> \n" +
"     <style> \n" +
"         table, th, td { \n" +
"            border: 1px solid black; \n" +
"            border-collapse: collapse; \n" +
"         }\n" +
"         th, td { \n" +
"            padding: 25px;\n" +
"         }\n" +
"         </style>\n" +
"</head> \n" +
"<body style=\"background-color:#90e0ef;\"> \n" +
"         <br><br>\n" +
"        <center> \n" +
"        <B><br><br><center><h1 style=\"color:black;\" ><i>Account-Balance</i></center></B>\n" +
"               <div style=\"background: #ffffff;\n" +
"                                  width: 500px;\n" +
"                                  height: 360px;\n" +
"                                  font-size : 16px; \n" +
"                                  padding: 20px; \n" +
"                        border: 4px solid black; \n" +
"                                 margin: 45px;\"> \n" +
"                <table style=\"width:100%\"> \n" +
"               <tr>\n" +
"                     <td><font size=\"4\"><B><center>Name :</center></B></font></td> \n" +
"                     <td><center>"+rs.getString("Name")+"</center></td> \n" +
"               </tr>\n" +
                  
"                    <tr> \n" +
"                      <td><font size=\"4\"><B><center>Email Id : </center></B></font></td>   \n" +
"                      <td><center>"+rs.getString("email_id")+"</center></td> \n" +
"               </tr> \n" +
"               <tr> \n" +
"                      <td><font size=\"4\"><B><center>Phone number : </center></B></font></td>   \n" +
"                      <td><center>"+rs.getString("Phone")+"</center></td> \n" +
"               </tr> \n" +

"                   <tr> \n" +
"                      <td><font size=\"4\"><B><center>Account number : </center></B></font></td>   \n" +
"                      <td><center>"+rs.getString("Accno")+"</center></td> \n" +
"               </tr> \n" +
                     
"               <tr> \n" +
"                      <td><font size=\"4\"><B><center>Balance Amount : </center></B></font></td>\n" +
"                      <td><center> Rs."+rs.getString("Acc_Balance")+"</center></td>  \n" +
"               </tr>\n" +
"               </table> \n" +
"               </div>  \n" +
"               </table> \n";      
            st.close();
            return 1;
        }
    return -1;
}
}
