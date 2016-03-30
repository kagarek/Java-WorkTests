package msAccessConnection;import java.sql.*;

/**
 * Created by Igor_Makarychev on 27.01.2016.
 */
public class Solution {

   public static void main(String[] args) throws SQLException {
        try {
            String url = "jdbc:ucanaccess://D:\\Access2010DB\\Northwind.accdb";
            Connection conn = DriverManager.getConnection(url, "username", "password");
            System.out.println("Connection Succesfull");
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM [Customer Address Book]");
            while (rs.next()) {
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    System.out.print(rs.getString(i) + "    ");
                }
                System.out.println();
            }
        }
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());

        }
    }
}
