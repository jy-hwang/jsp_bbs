package bbs;

import java.time.format.DateTimeFormatter;

public class Bbs {

  private int bbsNo;

  private String bbsTitle;

  private String bbsContent;

  private String createdDate;

  private String createdBy;

  private String updatedDate;

  private int bbsAvailable;

  public int getBbsNo() {
    return bbsNo;
  }

  public void setBbsNo(int bbsNo) {
    this.bbsNo = bbsNo;
  }

  public String getBbsTitle() {
    return bbsTitle;
  }

  public void setBbsTitle(String bbsTitle) {
    this.bbsTitle = bbsTitle;
  }

  public String getBbsContent() {
    return bbsContent;
  }

  public void setBbsContent(String bbsContent) {
    this.bbsContent = bbsContent;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public String getCreatedDate() {
    return createdDate.formatted(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
  }

  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }

  public String getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(String updatedDate) {
    this.updatedDate = updatedDate;
  }

  public int getBbsAvailable() {
    return bbsAvailable;
  }

  public void setBbsAvailable(int bbsAvailable) {
    this.bbsAvailable = bbsAvailable;
  }



}
