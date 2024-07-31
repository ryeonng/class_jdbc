package ch03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateExample {

   public static void main(String[] args) {
      
      Connection con = null;
      PreparedStatement preparedStatement = null;
      
      String url = "jdbc:mysql://localhost:3306/mydb2?serverTimezone=Asia/Seoul";
      String user = "root";
      String password = "asd123" ;
      
      
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
         
         con = DriverManager.getConnection(url , user , password);
         
         String query = "update employee set name = ? where id= ? \r\n";
         
         preparedStatement = con.prepareStatement(query);
         preparedStatement.setString(1, "바보");
         preparedStatement.setInt(2,6);
         
         int rowCount = preparedStatement.executeUpdate();
         System.out.println("rowCount : " + rowCount);
         
         
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

}
