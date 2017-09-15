<%--
  Created by IntelliJ IDEA.
  User: 重书
  Date: 2015/11/24
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
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
    <label>导入成功的员工：</label>
    <table class="ui striped table" id="info">
        <thead id="title">
        <tr >
            <th>工号</th>
            <th>公司编号</th>
            <th class="one wide">职员姓名</th>

            <th>职员电话</th>
            <th>职员部门</th>
            <th>部门主管</th>
            <th>职员岗位</th>
            <th>合同执行情况</th>
            <th>合同起止日期</th>
            <th>联系地址</th>
            <th>最新考核情况</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${new_employee}" var="var">
            <tr class="left aligned">
                <td>
                        ${var.id}
                </td>
                <td>
                    ${var.company_id}
                </td>
                <td>
                        ${var.name}
                </td>

                <td>
                        ${var.phone}
                </td>
                <td>
                        ${var.department}
                </td>
                <td>${var.superior}
                </td>
                <td>
                        ${var.position}
                </td>
                <td>
                        ${var.status}
                </td>
                <td>
                        ${var.contract_start}
                    ~ ${var.contract_end}
                </td>
                <td>
                        ${var.address}
                </td>
            </tr>
        </c:forEach>
        </tbody>
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


<%@include file="templates/footer.jsp" %>