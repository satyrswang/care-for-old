<%--
  Created by IntelliJ IDEA.
  User: 重书
  Date: 2015/10/20
  Time: 10:33
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
            <th class="one wide">职位</th>
            <th class="one wide">工号</th>
            <th class="one wide">姓名</th>
            <th class="one wide">性别</th>
            <th class="one wide">身份证号</th>
            <th class="one wide">联系电话</th>
            <th class="one wide">地址</th>
            <th class="one wide">照片</th>
            <th class="one wide">权限管理</th>
        </tr>
        </thead>
        <tbody>
        <%--<tr>--%>
        <%--<td>小明</td>--%>
        <%--<td><i class="question icon"></i>待审核</td>--%>
        <%--<td><i class="icon search"></i>查看</td>--%>
        <%--<td>无</td>--%>
        <%--</tr>--%>
        <%--<tr class="positive">--%>
        <%--<td>张三</td>--%>
        <%--<td><i class="icon checkmark"></i> 已通过</td>--%>
        <%--<td><i class="icon search"></i>查看</td>--%>
        <%--<td>无</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
        <%--<td>李四</td>--%>
        <%--<td><i class="question icon"></i>待审核</td>--%>
        <%--<td><i class="icon search"></i>查看</td>--%>
        <%--<td>需电话联系</td>--%>
        <%--</tr>--%>
        <%--<tr class="negative">--%>
        <%--<td>王五</td>--%>
        <%--<td><i class="icon Ban Circle"></i>已拒绝</td>--%>
        <%--<td><i class="icon search"></i>查看</td>--%>
        <%--<td>图片不合格</td>--%>
        <%--</tr>--%>

        <c:forEach items="${employee}" var="var">
            <tr class="left aligned">
                <td>
                    ${var.position}
                </td>
                <td>
                    ${var.id}
                </td>
                <td>
                   ${var.name}
                </td>
                <td>
                    female
                </td>
                <td>
                    ${var.phone}
                </td>
                <td>
                    12345
                </td>
                <td>${var.address}
                </td>
                <td>
                    <img src="/OCare/Assets/Images/testIcon.jpg"/>
                </td>
                <td>

                    <div class="ui toggle checkbox">
                        <input type="checkbox" name="public">
                        <label>权限1</label>
                    </div>
                    <div class="ui toggle checkbox">
                        <input type="checkbox" name="public">
                        <label>权限2</label>
                    </div>
                    <div class="ui toggle checkbox">
                        <input type="checkbox" name="public">
                        <label>权限3</label>
                    </div>

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
    <script>
        var app = angular.module("app", []);
        app.controller("appController", function ($scope, $window) {
            $scope.items = [];
            //$scope.items = $window.data;
        });

        app.controller("PaginationController", function ($scope) {
            //当前页索引
            $scope.currentPage = 0;
            //总页数
            $scope.pageCount = 20;
            //每页显示条数
            $scope.items_per_page = 3;

            //连续分页主体部分分页条目数
            $scope.num_display_entries = 6;
            //两侧首尾分页条目数
            $scope.num_edge_entries = 2;

            $scope.items = [];

            $scope.init = function (pageCount) {
                $scope.currentPage = 0;
                $scope.pageCount = pageCount;
                $scope.items = $scope.getRange($scope.currentPage, $scope.pageCount);
            }

            $scope.prevPage = function () {
                if ($scope.currentPage > 0) {
                    $scope.currentPage--;
                }
            };

            $scope.nextPage = function () {
                if ($scope.currentPage < $scope.pageCount - 1) {
                    $scope.currentPage++;
                }
            };

            $scope.setPage = function (n) {
                if (n >= 0) {
                    $scope.currentPage = n;
                }
            };

            $scope.$watch('pageCount', function () {
                if ($scope.currentPage == 0) {
                    $scope.items = $scope.getRange($scope.currentPage, $scope.pageCount);
                }
                else {
                    $scope.currentPage = 0;
                }
            });

            $scope.$watch('currentPage', function () {
                $scope.items = $scope.getRange($scope.currentPage, $scope.pageCount);
            });

            $scope.getRange = function (currentPage, pageCount) {
                var ret = [];
                var np = pageCount;
                var interval = $scope.getInterval(currentPage, pageCount);

                // Generate starting points
                if (interval[0] > 0 && $scope.num_edge_entries > 0) {
                    var end = Math.min($scope.num_edge_entries, interval[0]);
                    for (var i = 0; i < end; i++) {
                        ret.push(i);
                    }
                    if ($scope.num_edge_entries < interval[0]) {
                        ret.push(-1);
                    }
                }
                // Generate interval links
                for (var i = interval[0]; i < interval[1]; i++) {
                    ret.push(i);
                }
                // Generate ending points
                if (interval[1] < np && $scope.num_edge_entries > 0) {
                    if (np - $scope.num_edge_entries > interval[1]) {
                        ret.push(-1);
                    }
                    var begin = Math.max(np - $scope.num_edge_entries, interval[1]);
                    for (var i = begin; i < np; i++) {
                        ret.push(i);
                    }
                }
                return ret;
            };

            /**
             * Calculate start and end point of pagination links depending on
             * currentPage and num_display_entries.
             * @return {Array}
             */
            $scope.getInterval = function (currentPage, pageCount) {
                var ne_half = Math.ceil($scope.num_display_entries / 2);
                var np = pageCount;
                var upper_limit = np - $scope.num_display_entries;
                var start = currentPage > ne_half ? Math.max(Math.min(currentPage - ne_half, upper_limit), 0) : 0;
                var end = currentPage > ne_half ? Math.min(currentPage + ne_half, np) : Math.min($scope.num_display_entries, np);
                return [start, end];
            }
        });
    </script>
</div>

<script type="text/javascript">
    $('.ui.dropdown')
            .dropdown()
    ;
    $('.ui.checkbox')
            .checkbox()
    ;


</script>
<script>
    document.getElementById('hr_side').style.backgroundColor= "#ff7770"
    document.getElementById('hr_side').style.color= "white"
</script>

<%@include file="templates/footer.jsp" %>