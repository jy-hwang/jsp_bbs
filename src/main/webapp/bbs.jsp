<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="bbs.BbsDAO"%>
<%@ page import="bbs.Bbs"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width" initial-scale="1" />
    <link rel="stylesheet" href="css/bootstrap.css" />
    <title>JSP 게시판 웹 사이트</title>
    <style type="text/css">
      a, a:hover{
        color:#000;
        text-decoration: none
      }
    
    </style>
  </head>
  <body>
<%
String userId = null;
if (session.getAttribute("userId") != null){
  userId = (String) session.getAttribute("userId"); 
}

int pageNumber = 1;

if(request.getParameter("pageNumber") != null){
  pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
}
//out.println("pageNumber"+pageNumber);

%>
    <nav class="navbar navbar-default">
      <div class="navbar-header">
        <button
          type="button"
          class="navbar-toggle collapsed"
          data-toggle="collapse"
          data-target="#bs-example-navbar-collapse-1"
          aria-expanded="false"
        >
          <span class="icon-bar"></span> <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="main.jsp">JSP 게시판</a>
      </div>
      <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
          <li><a href="main.jsp">메인</a></li>
          <li class="active"><a href="bbs.jsp">게시판</a></li>
        </ul>
<% if(userId == null){ %>
        <ul class="nav navbar-nav navbar-right">
          <li class="dropdown"
            ><a
              href="#"
              class="dropdown-toggle"
              data-toggle="dropdown"
              role="button"
              aria-haspopup="true"
              aria-expanded="false"
              >접속하기 <span class="caret"></span>
            </a>
            <ul class="dropdown-menu">
              <li><a href="login.jsp">로그인</a></li>
              <li><a href="join.jsp">회원가입</a></li>
            </ul></li
          >
        </ul>
<% } else { %>
        <ul class="nav navbar-nav navbar-right">
          <li class="dropdown"
            ><a
              href="#"
              class="dropdown-toggle"
              data-toggle="dropdown"
              role="button"
              aria-haspopup="true"
              aria-expanded="false"
              >회원관리 <span class="caret"></span>
            </a>
            <ul class="dropdown-menu">
              <li><a href="logoutAction.jsp">로그아웃</a></li>
            </ul></li
          >
        </ul>
<% } %>
      </div>
    </nav>
    <div class="container">
      <div class="row">
        <table
          class="table table-striped"
          style="text-align: center; border: 1px solid #ddd"
        >
          <thead>
            <tr>
              <th style="background-color: #eee; text-align: center">번호</th>
              <th style="background-color: #eee; text-align: center">제목</th>
              <th style="background-color: #eee; text-align: center">작성자</th>
              <th style="background-color: #eee; text-align: center">작성일</th>
            </tr>
          </thead>
          <tbody>
<%
BbsDAO bbsDAO = new BbsDAO();

ArrayList<Bbs> list = bbsDAO.getList(pageNumber);

for( int i = 0 ; i< list.size(); i++){
%>
            <tr>
              <td><%= list.get(i).getBbsNo() %></td>
              <td><a href="view.jsp?bbsNo=<%= list.get(i).getBbsNo() %>"><%= list.get(i).getBbsTitle() %></a></td>
              <td><%= list.get(i).getCreatedBy() %></td>
              <td><%= list.get(i).getCreatedDate() %></td>
            </tr>
            
            <%
}
            %>
          </tbody>
        </table>
<%
if(pageNumber != 1){
%>
        <a href="bbs.jsp?pageNumber=<%=pageNumber - 1%>" class="btn btn-success btn-arrow-left">이전</a>
<%
} if(bbsDAO.nextPage(pageNumber)){
  
%>

<a href="bbs.jsp?pageNumber=<%=pageNumber + 1%>" class="btn btn-success btn-arrow-right">다음</a>


<%
}
%>
        <a href="write.jsp" class="btn btn-primary pull-right">글쓰기</a>
      </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="js/bootstrap.js"></script>
  </body>
</html>
