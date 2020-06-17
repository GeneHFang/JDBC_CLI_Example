import java.sql.*;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class ExampleQuery {

    //Command line example 
    //IN CLI: java ExampleQuery <url> <user> <pw> <driver> <SQL query as string>
    public static void main(String[] args) {
        
        ResultSet rs = null;
        Statement stmt = null;
        Connection conn = null;

        String _url = args[0];

        try {
            //Step 1) Load driver 
            Class.forName(args[3]);

            //Step 2) make connection 
            conn = DriverManager.getConnection(_url, args[1], args[2]);

            //Step 3) create statement
            stmt = conn.createStatement();

            //Step 4) Make query
            rs = stmt.executeQuery(args[4]);
           
            //Step 5) Display results
            while (rs.next()){
                System.out.print(rs.getString(1)); // Columns are 1-indexed, so we start at 1
                //and other sql getters : getInt(), getDate()
                System.out.println(rs.getDate(4));
            }  
        }
        //handle any exceptions
        catch (Exception e) {
            e.printStackTrace();
        }
        //clean up resources
        finally{
            try {
                if (rs!=null) rs.close();
                if (stmt!=null) stmt.close();
                if (conn!=null) conn.close();
            }
            catch (SQLException se){
                se.printStackTrace();
            }
        }

    }

}