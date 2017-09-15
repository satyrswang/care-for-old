<%--
  Created by IntelliJ IDEA.
  User: douyutong
  Date: 2015/11/26
  Time: 0:07
  To change this template use File | Settings | File Templates.
--%><%@page contentType="text/html; charset=utf-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="templates/header.jsp"%>
<%@include file="templates/sidebar.jsp"%>
<link rel="stylesheet" href="/OCare/Assets/CSS/verify.css">

<div class="main_content">
  <div class="ui teal ribbon label">机构申请审核:</div>
  <div class="ui divided selection list">
    <a class="item">
      <div class="ui red horizontal label">机构名称: </div> ${company.name}
    </a>
    <a class="item">
      <div class="ui red horizontal label">营业执照: </div> <c:out value="${company.id}"/>
    </a>
    <a class="item">
      <div class="ui horizontal label">法人ID:&nbsp;</div> <c:out value="${company.legal_person_id}"/>
    </a>

    <a class="item">
      <div class="ui horizontal label">当前状态:</div>
      <c:if test="${company.status == 102 || company.status == 103}">
        已通过该机构注册申请
      </c:if>
      <c:if test="${company.status == 101}">
        未审核该机构注册申请
      </c:if>
    </a>
  </div>
  <%--<c:out value="${request.togetherImg}"/>  数据库里没有图片 只能显示以下样图--%>
  <img class="ui rounded large image" src="/OCare/Assets/Images/licence.jpg" pagespeed_url_hash="481064615" onload="pagespeed.CriticalImages.checkImageForCriticality(this);">
  <div class="elderID-relativeID">
    <a class="ui huge red label" href="<c:url value="/company/reject/${company.id}"></c:url> " id="reject">
      <i class="icon remove circle"></i>拒绝
    </a>
    <a class="ui huge label" href="<c:url value="/company/list"></c:url> " id="undesided">
      <i class="icon adjust"></i>暂定
    </a>
    <a class="ui huge teal label" href="<c:url value="/company/agree/${company.id}"></c:url> " id="agree">
      <i class="icon add sign"></i>通过
    </a>
  </div>
</div>
<%@include file="templates/footer.jsp"%>
