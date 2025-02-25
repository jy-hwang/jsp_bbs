package bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class BbsDAO {
  private Connection conn;

  private ResultSet rs;

  public BbsDAO() {
    try {
      String dbUrl = System.getProperty("db.url");
      String dbUsername = System.getProperty("db.username");
      String dbPassword = System.getProperty("db.password");
      Class.forName("org.mariadb.jdbc.Driver");

      conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);


    } catch (Exception e) {
      e.getStackTrace();
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
      e.getStackTrace();
    }
    return 0;

  }

  public int getNext(){
    String sqlQuery = " SELECT bbs_no AS bbsNo FROM bbs ORDER BY bbs_no DESC ";
    
    try{
      PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
      
      rs = pstmt.executeQuery();
      
      if (rs.next()){
        return rs.getInt(1) + 1;
      }
    } catch(Exception e){
      e.getMessage();
    
    }
    return -1;

  }
  
  public ArrayList<Bbs> getList(int pageNumber) {


    String sqlQuery =
        " SELECT bbs_no AS bbsNo, bbs_title AS bbsTitle, bbs_content AS bbsContent, created_date AS createdDate, created_by AS createdBy FROM bbs WHERE bbs_no < ? AND bbs_available = 1 ORDER BY bbs_no DESC LIMIT 10";

    ArrayList<Bbs> list = new ArrayList<Bbs>();


    try {
      PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
      pstmt.setInt(1, getNext() - (  pageNumber - 1) * 10);
      rs = pstmt.executeQuery();

      while (rs.next()) {
        Bbs bbs = new Bbs();
        bbs.setBbsNo(rs.getInt(1));
        bbs.setBbsTitle(rs.getString(2));
        bbs.setBbsContent(rs.getString(3));
        bbs.setCreatedDate(rs.getString(4).substring(0,11));
        bbs.setCreatedBy(rs.getString(5));
        //System.out.println("bbsNo : " + bbs.getBbsNo());
        list.add(bbs);

      }


    } catch (Exception e) {
      e.getStackTrace();

    }
    return list;

  }


  public boolean nextPage(int pageNumber) {
    String sqlQuery =
        " SELECT bbs_no AS bbsNo FROM bbs WHERE bbs_no < ? AND bbs_available = 1";

    ArrayList<Bbs> list = new ArrayList<Bbs>();


    try {
      PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
      pstmt.setInt(1, getNext() - (  pageNumber - 1) * 10);
      rs = pstmt.executeQuery();

      if (rs.next()) {
        return true;
      }


    } catch (Exception e) {
      e.getStackTrace();

    }
    return false;
  }

}
