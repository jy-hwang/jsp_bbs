package users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

  private Connection conn;

  private PreparedStatement pstmt;

  private ResultSet rs;

  public UserDAO() {
    try {
      String dbUrl = System.getProperty("db.url");
      String dbUsername = System.getProperty("db.username");
      String dbPassword = System.getProperty("db.password");
      System.out.println(dbUsername + dbPassword);
      Class.forName("org.mariadb.jdbc.Driver");

      conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);


    } catch (Exception e) {
      e.getStackTrace();
    }
  }

  public int login(String userId, String userPassword) {

    System.out.println("userId : " + userId);
    System.out.println("userPassword : " + userPassword);

    String sqlQuery = " SELECT user_password AS userPassword FROM users WHERE user_id = ? ";

    try {
      pstmt = conn.prepareStatement(sqlQuery);
      pstmt.setString(1, userId);
      rs = pstmt.executeQuery();

      if (rs.next()) {
        if (rs.getString(1).equals(userPassword)) {
          return 1; // login ok
        } else {
          return 0; // 비밀번호 오류
        }
      }
      return -1; // 아이디가 없음.
    } catch (Exception e) {
      e.getStackTrace();
    }
    return -2;// 데이터베이스 오류
  }

  public int join(User user) {
    String sqlQuery =
        " INSERT INTO users (user_id, user_password, user_name, user_gender, user_email) values(?, ?, ?, ?, ?); ";
    try {
      pstmt = conn.prepareStatement(sqlQuery);

      pstmt.setString(1, user.getUserId());
      pstmt.setString(2, user.getUserPassword());
      pstmt.setString(3, user.getUserName());
      pstmt.setString(4, user.getUserGender());
      pstmt.setString(5, user.getUserEmail());
      return pstmt.executeUpdate();

    } catch (Exception e) {
      e.getStackTrace();
    }
    return -1;// 데이터베이스 오류
  }

}
