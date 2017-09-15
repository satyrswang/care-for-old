<%--
  Created by IntelliJ IDEA.
  User: 重书
  Date: 2015/10/20
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html; charset=utf-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="templates/header.jsp" %>
<%@include file="templates/sidebar.jsp" %>
<link rel="stylesheet" href="/OCare/Assets/CSS/homepage.css">
<div class="ui container Entry">
    <div class="ui form">
        <div class="inline fields">
            <div class="three wide field"><label>请输入查找内容：</label></div>
            <%--<div class="three wide field">--%>
                <%--<div class="ui dropdown">--%>
                    <%--<div class="text">请选择</div>--%>
                    <%--<i class="dropdown icon"></i>--%>

                    <%--<div class="menu">--%>
                        <%--<div class="item">列出所有在职人员</div>--%>
                        <%--<div class="item">身份证号</div>--%>
                        <%--<div class="item">合同编号</div>--%>
                        <%--<div class="item">电话号码</div>--%>
                        <%--<div class="item">联系地址</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>

            <div class="six wide field">
                <div class="ui icon input">
                    <input type="text" id="input1" placeholder="请输入查询内容..."/>

                </div>
            </div>
            <div class="three wide field">
            <div class="ui positive submit button" id ="search">
                搜索
            </div>
              </div>
        </div>
    </div>
    <div class="inline fields">
        <table class="ui striped table" id="info">
            <thead id="title">
            <tr >
                <th>工号</th>
                <th>合同编号</th>
                <th class="one wide">职员姓名</th>
                <th>职员身份证号</th>
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
            <c:forEach items="${employee}" var="var">
                <tr class="left aligned">
                    <td>
                            ${var.id}
                    </td>
                    <td>
                            T432252
                    </td>
                    <td>
                            ${var.name}
                    </td>
                    <td>
                            352372387
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

        <div style="display: none;">
        <table class="ui striped table" id="info0" >
            <thead id="title0">
            <tr >
                <th>工号</th>
                <th>合同编号</th>
                <th class="one wide">职员姓名</th>
                <th>职员身份证号</th>
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
            <tr class="left aligned">
                <td>

                </td>
            </tr>
            </tbody>
        </table>
        </div>
        </br>
        <div class="ui basic green button" id="export"><i class="icon cloud"></i><a data-type="xls" href="javascript:;">信息导出</a></div>
    </div>
</div>
<script src="/OCare/Assets/JS/Blob.js"></script>

<script src="/OCare/Assets/JS/FileSaver.js"></script>

<script src="/OCare/Assets/JS/tableExport.js"></script>

<script>



    var $exportLink = document.getElementById('export');

    $exportLink.addEventListener('click', function(e){

        e.preventDefault();

        if(e.target.nodeName === "A"){

            tableExport('info0', '员工信息', e.target.getAttribute('data-type'));

        }



    }, false);



</script>

<script type="text/javascript">
    jQuery (function ($)
    {
        var title=$('#title');
        var table = $ ('#info'), text = $ ('#input1');
        var table0=$('#info0');
        $ ('#search').click (function ()
        {
            if(text.val()== ""){
                table.find('tr').show();

            }
            else {
                table0.find('tr').remove();
                table.find('tr').hide();
                title.find('tr').show();
                table.find('tr:contains("' + text.val() + '")').show();
                var t=title.find('tr').clone();
                t.appendTo('#info0');
                var tr=table.find('tr:contains("' + text.val() + '")').clone();
                tr.appendTo('#info0');
            }
        });
    });
</script>

<script type="text/javascript">

    $('.ui.dropdown')
            .dropdown()
    ;
    $('.ui.checkbox')
            .checkbox()
    ;


    $('.ui.form')
            .form({
                field: {
                    name: {
                        identifier: 'name',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Please enter your name'
                            }
                        ]
                    },
                    reason: {
                        identifier: 'reason',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Please enter your reason'
                            }
                        ]
                    },
                    address: {
                        identifier: 'address',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Please enter your address'
                            }
                        ]
                    },
                    phone: {
                        identifier: 'phone',
                        rules: [
                            {
                                type: 'integer',
                                prompt: 'Please enter your phone'
                            }
                        ]
                    },
                    ID: {
                        identifier: 'ID',
                        rules: [
                            {
                                type: 'integer',
                                prompt: 'Please enter your ID'
                            }
                        ]
                    },
                    skills: {
                        identifier: 'skills',
                        rules: [
                            {
                                type: 'minCount[2]',
                                prompt: 'Please select at least two skills'
                            }
                        ]
                    },
                    position: {
                        identifier: 'position',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Please select a position'
                            }
                        ]
                    },
                    superior: {
                        identifier: 'superior',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Please select a superior'
                            }
                        ]
                    },
                    department: {
                        identifier: 'department',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Please select a department'
                            }
                        ]
                    },
                    username: {
                        identifier: 'username',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Please enter a username'
                            }
                        ]
                    },
                    password: {
                        identifier: 'password',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Please enter a password'
                            },
                            {
                                type: 'minLength[6]',
                                prompt: 'Your password must be at least {ruleValue} characters'
                            }
                        ]
                    },
                    checked: {
                        identifier: 'checked',
                        rules: [
                            {
                                type: 'checked',
                                prompt: 'You must agree to the terms and conditions'
                            }
                        ]
                    }
                }
            })
    ;
</script>
<script>
    document.getElementById('info_side').style.backgroundColor= "#ff7770"
    document.getElementById('info_side').style.color= "white"
</script>
<%@include file="templates/footer.jsp" %>