<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
java.util.Date nowDate = new java.util.Date();
java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat("yyyy년 MM월 dd일");
%>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <a class="navbar-brand" href="#">배달원 현황</a>

  <div class="collapse navbar-collapse" id="navbarColor01">
  
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#"><%=sf.format(nowDate)%>
          <span class="sr-only">(current)</span>
        </a>
      </li>
    </ul>
    
    <form class="form-inline my-2 my-lg-0">
      <a class="form-control mr-sm-2 btn btn-dark" href="/driver/logout.do">로그아웃</a>
    </form>
    
  </div>
</nav>