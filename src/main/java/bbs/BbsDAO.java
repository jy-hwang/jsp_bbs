package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BbsDAO {
  private Connection conn;

  private ResultSet rs;

  public BbsDAO() {
    try {
      String dbUrl = System.getProperty("db.url");
      String dbUsername = System.getProperty("db.username");
      String dbPassword = System.getProperty("db.password");
      System.out.println(dbUsername + dbPassword);
      Class.forName("org.mariadb.jdbc.Driver");

      conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);


    } catch (Exception e) {
      e.getMessage();
    }
  }
  
  public int write(String bbsTitle, String bbsContent, String userId) {
    
    String sqlQuery = " INSERT INTO bbs (bbs_title, bbs_content, created_by) VALUES (?, ?, ?); ";
    try {
      PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
      pstmt.setString(1, bbsTitle);
      pstmt.setString(2, bbsContent);
      pstmt.setString(3, userId);
      return pstmt.executeUpdate();

    } catch (Exception e) {
      e.getMessage();
    }
    return 0;
    
  }
}
