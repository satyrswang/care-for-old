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
    <div class="ui grid">
        <div class="eight wide column">
            <form class="ui form" method="post">

                <h4 class="ui dividing header">离职信息</h4>
                <label>员工编号：</label>

                <div class="ui grid">

                    <div class="twelve wide column field">


                        <div class="ui input">
                            <input type="text" id="employeeid" placeholder="请输入员工编号">
                        </div>
                        </br>
                    </div>
                    <div class="four wide column">
                        <a class="ui huge teal label" id="confirm" onclick="confirm_employee()">确认</a>
                    </div>
                </div>
                <h3>员工详细信息：</h3>
                <div id="info">



                </div>
                </br>
                </br>
                </br>
                </br>
                <div class="field">
                    <label>离职原因：</label>
                    <textarea name="reason"></textarea>
                </div>
                <div>
                    <a class="ui huge red label" id="remove" onclick="remove_employee()">注销</a>
                </div>


                <div class="ui error message"></div>


            </form>
        </div>
        <div class="eight wide column">
           <div id="pic_info">

           </div>

        </div>
    </div>
</div>

<script  type="text/javascript">
    function confirm_employee(){
            $.ajax({

                type: "get",
                url:"/OCare/hr/confirm",
                data: { employeeid: $("#employeeid").val()},
                dataType:"json",
                success: function (data) {
                    //alert(data.employee_department);
                    $("#info").replaceWith('<div id="info"><div id="employee_id">'
                    + data.employee_id+'</div>'+"  "+data.employee_name+"  " +data.employee_phone+"  "+data.employee_position +"  " +data.employee_superior +"  "+ '</br></div>');

                    <%--var temp = data.toString();--%>
                    <%--<jsp:setProperty name="userinfo" property="id" param="username"/>--%>
                    <%--<jsp:setProperty name="userinfo" property="nickname" param="temp"/>--%>
                    <%--<%--%>
                    <%--session.setAttribute("id",userinfo.getId());--%>
                    <%--session.setAttribute("nickname",userinfo.getNickname());--%>
                    <%--%>--%>
                }
                ,
                error:function(data){
                    alert("查无此员工");

                }
            });
        };
    function remove_employee(){
        $.ajax({

            type: "get",
            url:"/OCare/app/employee/delete",
            data: { id: $("#employee_id").text()},
            dataType:"json",
            success: function (data) {
                alert("注销成功");
//                $("#info").replaceWith('<div id="info"><div id="employee_id">'
//                + data.employee_id+'</div>'+"  "+data.employee_name+"  " +data.employee_phone+"  "+data.employee_position +"  " +data.employee_superior +"  "+ '</br></div>');

                <%--var temp = data.toString();--%>
                <%--<jsp:setProperty name="userinfo" property="id" param="username"/>--%>
                <%--<jsp:setProperty name="userinfo" property="nickname" param="temp"/>--%>
                <%--<%--%>
                <%--session.setAttribute("id",userinfo.getId());--%>
                <%--session.setAttribute("nickname",userinfo.getNickname());--%>
                <%--%>--%>
            }
            ,
            error:function(data){
                alert("注销失败");

            }
        });
    };
</script>

<script type="text/javascript">
    $(document).ready(function () {
        $('.ui.dropdown')
                .dropdown()
        ;
        $('.ui.checkbox')
                .checkbox()
        ;

        $('.ui.form')
                .form({

                    name: {
                        identifier: 'name',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Please enter your name'
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
                                type: 'empty',
                                prompt: 'Please enter your phone'
                            }
                        ]
                    },
                    ID: {
                        identifier: 'ID',
                        rules: [
                            {
                                type: 'empty',
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
                    },

                    start_time: {
                        identifier: 'start_time',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Please enter '
                            }
                        ]
                    },
                    finish_time: {
                        identifier: 'finish_time',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Please enter '
                            }
                        ]
                    }


                })
        ;
    })
</script>
<script>
    document.getElementById('leave_side').style.backgroundColor= "#ff7770"
    document.getElementById('leave_side').style.color= "white"
</script>
<%@include file="templates/footer.jsp" %>