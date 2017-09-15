<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2015/12/10
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html; charset=utf-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="templates/header.jsp"%>
<%@include file="templates/sidebar.jsp"%>
<link rel="stylesheet" href="/OCare/Assets/CSS/homepage.css">
<div class="main_content">


  <table class="ui table segment">
    <thead>
    <tr>
      <th>机构名称</th>
      <th>状态</th>
      <th>详细信息</th>
      <th>备注</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="var">
      <%--status = 101 代表未审核--%>
      <%--status = 102 代表审核通过--%>
      <%--status = 103 代表审核通过--%>
      <c:if test="${var.status == 101}">
        <tr class="negative">
          <td>${var.name}</td>
          <td><i class="icon question"></i>待审核</td>
          <td><a href="/OCare/company/name/${var.name}"><i class="icon search"></i>查看</a></td>
          <td>无</td>
        </tr>
      </c:if>
      <c:if test="${var.status == 102}">
        <tr class="positive">
          <td>${var.id}</td>
          <td><i class="icon checkmark"></i>已通过</td>
          <td><a href="/OCare/company/name/${var.name}"><i class="icon search"></i>查看</a></td>
          <td>无</td>
        </tr>
      </c:if>
      <c:if test="${var.status == 103}">
        <tr class="negative">
          <td>${var.name}</td>
          <td><i class="icon question"></i>已拒绝</td>
          <td><a href="/OCare/company/name/${var.name}"><i class="icon search"></i>查看</a></td>
          <td>无</td>
        </tr>
      </c:if>
    </c:forEach>
    </tbody>
  </table>
</div>
<script>
  document.getElementById('company_list').style.backgroundColor= "#ff7770"
  document.getElementById('company_list').style.color= "white"
</script>
<%@include file="templates/footer.jsp"%>
