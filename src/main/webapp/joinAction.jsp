<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ page import="users.UserDAO"%>
<%@ page import="java.io.PrintWriter"%>

<%
request.setCharacterEncoding("UTF-8");
%>

<jsp:useBean id="user" class="users.User" scope="page" />
<jsp:setProperty name="user" property="userId" />
<jsp:setProperty name="user" property="userPassword" />
<jsp:setProperty name="user" property="userName" />
<jsp:setProperty name="user" property="userGender" />
<jsp:setProperty name="user" property="userEmail" />

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8" />
<title>JSP 게시판 웹 사이트</title>
</head>
<body>

  <%
  if (user.getUserId() == null || user.getUserPassword() == null || user.getUserName() == null
      || user.getUserGender() == null || user.getUserEmail() == null) {
    PrintWriter script = response.getWriter();
    script.println("<script>");
    script.println("alert('입력이 안된 사항이 있습니다..')");
    script.println("history.back()");
    script.println("</script>");
  } else {
    UserDAO userDAO = new UserDAO();

    int result = userDAO.join(user);

    if (result == -1) {
      PrintWriter script = response.getWriter();
      script.println("<script>");
      script.println("alert('이미 존대하는 아이디입니다..')");
      script.println("history.back()");
      script.println("</script>");

    } else {
      PrintWriter script = response.getWriter();
      script.println("<script>");
      script.println("location.href = 'main.jsp'");
      script.println("</script>");
    }
  }
  %>

</body>
</html>
