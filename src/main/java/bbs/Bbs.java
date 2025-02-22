package bbs;

import java.time.LocalDateTime;

public class Bbs {

  private int bbsNo;

  private String bbsTitle;

  private String bbsContent;

  private LocalDateTime createdDate;

  private String createdBy;

  private LocalDateTime updatedDate;

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

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public LocalDateTime getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(LocalDateTime updatedDate) {
    this.updatedDate = updatedDate;
  }

  public int getBbsAvailable() {
    return bbsAvailable;
  }

  public void setBbsAvailable(int bbsAvailable) {
    this.bbsAvailable = bbsAvailable;
  }



}
