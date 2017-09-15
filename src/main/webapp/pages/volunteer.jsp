<%--
  Created by IntelliJ IDEA.
  User: 重书
  Date: 2015/12/10
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html; charset=utf-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="templates/header.jsp" %>
<%@include file="templates/sidebar.jsp" %>
<link rel="stylesheet" href="/OCare/Assets/CSS/homepage.css">
<div class="ui container HR" data-ng-app="app">
    <table class="ui blue striped celled table" data-ng-controller="appController">
        <thead>
        <tr>
            <th class="one wide">志愿者编号</th>
            <th class="one wide">姓名</th>

            <th class="one wide">联系电话</th>
            <th class="one wide">地址</th>
            <th class="one wide">Email</th>
            <th class="one wide">公司编号</th>
            <th class="one wide">老人编号</th>
        </tr>
        </thead>
        <tbody>


        <c:forEach items="${volunteers}" var="var">
            <tr class="left aligned">
                <td>
                        ${var.id}
                </td>
                <td>
                        ${var.name}
                </td>

                <td>
                        ${var.phone}
                </td>

                <td>${var.address}
                </td>

                <td>${var.email}
                </td>
                <td>${var.company_id}
                </td>
                <td>${var.elder_id}
                </td>

            </tr>
        </c:forEach>


        </tbody>
        <tfoot>
        <tr>
            <th colspan="9">
                <div class="ui pagination menu" data-ng-controller="PaginationController" data-ng-init="init(10)">
                    <a class="icon item" data-ng-show="currentPage != 0" data-ng-click="prevPage()">
                        <i class="icon left arrow"></i>
                    </a>
                    <a class="item" data-ng-repeat="n in items track by $index"
                       data-ng-class="{'active': n == currentPage, 'disabled' : n == -1}"
                       data-ng-bind="n == -1 ? '...' : n + 1"
                       data-ng-click="setPage(n)">
                    </a>
                    <a class="icon item" data-ng-show="currentPage != pageCount - 1" data-ng-click="nextPage()">
                        <i class="icon right arrow"></i>
                    </a>
                </div>
            </th>
        </tr>
        </tfoot>
    </table>

</div>

<script type="text/javascript">
    $('.ui.dropdown')
            .dropdown()
    ;
    $('.ui.checkbox')
            .checkbox()
    ;


</script>


<%@include file="templates/footer.jsp" %>
