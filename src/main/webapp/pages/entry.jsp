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
            <form class="ui form" id="jsForm">

                <h4 class="ui dividing header">入职信息</h4>


                <div class="field">
                    <label>姓名：</label>

                    <div class="ui input">
                        <input type="text" name="name" id="name" placeholder="请输入姓名" required data-msg-required="请输入姓名"/>

                    </div>
                    </br>
                </div>
                <div class="field">
                    <div class="ui input">
                        <label>地址：</label>
                        <input type="text" name="address" id="address" placeholder="请输入正确的地址"  required data-msg-required="请输入地址">
                    </div>
                    </br>
                </div>

                <div class="field">
                    <div class="ui input">
                        <label>电话：</label>
                        <input type="text" name="phone" id="phone" placeholder="请输入您的电话"  required data-msg-required="请输入电话" data-rule-mobile="true" data-msg-mobile="请输入正确格式">
                    </div>
                    </br>
                </div>

                <div class="field">
                    <div class="ui input">
                        <label>身份证号：</label>
                        <input type="text" name="identity" id="identity" placeholder="请输入身份证号"  required data-msg-required="请输入身份证号" data-rule-idCard="true" data-msg-idCard="请输入正确格式">
                    </div>
                </div>

                <label>部门：</label>

                <div class="field">
                    <div class="ui dropdown" name="department">
                        <div class="text" id="department">请选择</div>
                        <i class="dropdown icon"></i>

                        <div class="menu">
                            <div class="item">护工班组</div>
                            <div class="item">人事经理室</div>
                        </div>
                    </div>
                </div>
                <%--<label>部门：</label>--%>
                <%--<select class="ui fluid dropdown" name="department">--%>
                <%--<option value="">请选择</option>--%>
                <%--<option value="AL">胡工班组</option>--%>
                <%--<option value="AL">人事经理室</option>--%>
                <%--</select>--%>
                </br>
                </br>
                <div class="field">
                    <label>职位：</label>

                    <div class="ui dropdown" name="position">
                        <div class="text" id="position">请选择</div>
                        <i class="dropdown icon"></i>

                        <div class="menu">
                            <div class="item">护工班组</div>
                            <div class="item">人事经理室</div>
                        </div>
                    </div>
                </div>
                </br>
                </br>
                <div class="field">
                    <label>上级主管：</label>

                    <div class="ui dropdown">
                        <div class="text" id="superior">请选择</div>
                        <i class="dropdown icon"></i>

                        <div class="menu" name="superior">
                            <div class="item">张三</div>
                            <div class="item">李四</div>
                        </div>
                    </div>
                </div>
                </br>
                </br>

                <label>合同时间：</label>

                <div class="ui grid field">


                    <div class="ui input eight wide column">

                        <input id="start_time" type="text" name="start_time" placeholder="开始时间(2000.1.1)"  required  data-rule-date="true" data-msg-required="请输入开始时间"  data-msg-date="请输入正确时间">
                    </div>

                    <div class="ui input eight wide column">
                        <input id="finish_time" type="text" name="finish_time" placeholder="结束时间(2000.1.2)"  required data-rule-date="true" data-msg-required="请输入结束时间" date data-msg-date="请输入正确时间">
                    </div>
                </div>
                <%--</br>--%>
                <div class="field">
                    <div class="ui checkbox">
                        <input type="checkbox" name="checked" tabindex="0"   required data-msg-required="请勾选">
                        <label>I agree to the Terms and Conditions</label>
                    </div>
                </div>
                <%--</br>--%>
                <%--</br>--%>
                <div class="field" style="display: none">

                    <div class="three ui fluid buttons">
                        <div class="ui button">人事合同预览</div>
                        <div class="ui button">合同签订上传</div>
                        <div class="ui button">工作证打印</div>
                    </div>
                </div>
                </br>
                </br>


                <button class="ui fluid button" style="background-color: transparent;border: 0px;" >
                    <a class="ui huge fluid teal label"  id="save" onclick="save()">保存</a>
                </button>



                <div class="ui message" style="color:#cc0e01;background-color: transparent">
                    <div class="error error_msg" style="color:#cc0e01" for="name"></div>
                    <div class="error error_msg" style="color:#cc0e01" for="address"></div>
                    <div class="error error_msg" style="color:#cc0e01" for="phone"></div>
                    <div class="error error_msg" style="color:#cc0e01" for="identity"></div>
                    <div class="error error_msg" style="color:#cc0e01" for="start_time"></div>
                    <div class="error error_msg" style="color:#cc0e01" for="finish_time"></div>
                    <div class="error error_msg" style="color:#cc0e01" for="checked"></div>
                </div>
                <%--<div class="ui error message error_msg" for="phone" >--%>

                <%--</div>--%>


            </form>
        </div>
        <script type="text/javascript">
                $('.ui.dropdown')
                        .dropdown()
                ;
                $('.ui.checkbox')
                        .checkbox()
                ;


        </script>
        <div class="eight wide column" id="pic_info" style="display: none">
            <form class="ui form" action="/OCare/app/hr/upload" method="post" id="my-awesome-dropzone"
                  enctype="multipart/form-data">
                <div>
                    <input type="text" name="employeeid"  id="created_id"/>
                </div>
                <h5>身份证照片</h5>
                <div id="warp" class="two fields">

                    <div class="field">
                        <input type="file" id="up_img_WU_FILE_0" name="file"/>
                        <img id="imgShow_WU_FILE_0" width="200" height="180" />
                    </div>

                    <div  class="field">
                        <input type="file" id="up_img_WU_FILE_1" name="file1"/>
                        <img id="imgShow_WU_FILE_1" width="200" height="180" />
                    </div>
                    <h5>合同照片</h5>
                    <div class="field">
                        <input type="file" id="up_img_WU_FILE_2" name="file2"/>
                        <img id="imgShow_WU_FILE_2" width="200" height="180" />
                    </div>

                    <div  class="field">
                        <input type="file" id="up_img_WU_FILE_3" name="file3"/>
                        <img id="imgShow_WU_FILE_3" width="200" height="180" />
                    </div>
                    <h5>个人照片</h5>
                    <div class="field">
                        <input type="file" id="up_img_WU_FILE_4" name="file4"/>
                        <img id="imgShow_WU_FILE_4" width="200" height="180" />
                    </div>

                    <div  class="field">
                        <input type="file" id="up_img_WU_FILE_5" name="file5"/>
                        <img id="imgShow_WU_FILE_5" width="200" height="180" />
                    </div>

                </div>
                <button class="ui fluid button" style="background-color: transparent;border: 0px;" >
                    <a class="ui huge fluid teal label" onclick="processQueue()"  id="agree">提交图片资料</a>
                </button>
            </form>

        </div>
    </div>


    <script src="http://lib.sinaapp.com/js/jquery/1.10.2/jquery-1.10.2.min.js"></script>
    <script src="/OCare/Assets/JS/jquery.validate.js"></script>
    <script>
        function processQueue()
        {
            myDropzone.processQueue();
        }

        Dropzone.options.myAwesomeDropzone = {
            paramName:"file",// The name that will be used to transfer the file
            maxFilesize: 2, // MB
            dictRemoveFile:true,
            autoProcessQueue:false,
            accept: function(file, done) {
                if (file.name == "justinbieber.jpg") {
                    done("Naha, you don't.");
                }
                else { done(); }
            }
        };

        var myDropzone = new Dropzone("#my-awesome-dropzone");

        myDropzone.on("success", function(file,finished) {
            //TODO
            swal("上传成功", "success")
        });

        function save(){
            //jquery.validate
            $("#jsForm").validate({
                submitHandler: function() {
                    $.ajax({

                        type: "post",
                        url:"/OCare/hr/insert",
                        data: { name: $("#name").val(),address:$("#address").val(),phone:$("#phone").val(),identity:$("#identity").val(),position:$("#position").text(),department:$("#department").text(),superior:$("#superior").text(),start_time:$("#start_time").val(),finish_time:$("#finish_time").val()},
                        dataType:"json",
                        success: function (data) {
                            alert("success："+data.created_name);
                            $("#pic_info").show();
                            $("#created_id").replaceWith('<input id="created_id" type="text" name="employeeid"  value="'+data.created_name+'"/>');
                            <%--var temp = data.toString();--%>
                            <%--<jsp:setProperty name="userinfo" property="id" param="username"/>--%>
                            <%--<jsp:setProperty name="userinfo" property="nickname" param="temp"/>--%>
                            <%--<%--%>
                            <%--session.setAttribute("id",userinfo.getId());--%>
                            <%--session.setAttribute("nickname",userinfo.getNickname());--%>
                            <%--%>--%>
                        }, error: function(){
                            alert("失败");
                        }
                    });
                }
            })

        };


        //下面是一些常用的验证规则扩展

        /*-------------验证插件配置 懒人建站http://www.51xuediannao.com/-------------*/

        //配置错误提示的节点，默认为label，这里配置成 span （errorElement:'span'）
        $.validator.setDefaults({
            errorElement:'label'
        });

        //配置通用的默认提示语
        $.extend($.validator.messages, {
            required: '必填',
            equalTo: "请再次输入相同的值"
        });
        //电话验证规则
        jQuery.validator.addMethod("phone", function (value, element) {
            var phone = /^0\d{2,3}-\d{7,8}$/;
            return this.optional(element) || (phone.test(value));
        }, "电话格式如：0371-68787027");
        //手机验证规则
        jQuery.validator.addMethod("mobile", function (value, element) {
            var mobile = /^1[3|4|5|7|8]\d{9}$/;
            return this.optional(element) || (mobile.test(value));
        }, "手机格式不对");
        //身份证
        jQuery.validator.addMethod("idCard", function (value, element) {
            var isIDCard1=/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;//(15位)
            var isIDCard2=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;//(18位)

            return this.optional(element) || (isIDCard1.test(value)) || (isIDCard2.test(value));
        }, "格式不对");

    </script>





<%--//            $('.ui.form')--%>
<%--//                    .form({--%>
<%--//--%>
<%--//                        name: {--%>
<%--//                            identifier: 'name',--%>
<%--//                            rules: [--%>
<%--//                                {--%>
<%--//                                    type: 'empty',--%>
<%--//                                    prompt: 'Please enter your name'--%>
<%--//                                }--%>
<%--//                            ]--%>
<%--//                        },--%>
<%--//                        address: {--%>
<%--//                            identifier: 'address',--%>
<%--//                            rules: [--%>
<%--//                                {--%>
<%--//                                    type: 'empty',--%>
<%--//                                    prompt: 'Please enter your address'--%>
<%--//                                }--%>
<%--//                            ]--%>
<%--//                        },--%>
<%--//                        phone: {--%>
<%--//                            identifier: 'phone',--%>
<%--//                            rules: [--%>
<%--//                                {--%>
<%--//                                    type: 'empty',--%>
<%--//                                    prompt: 'Please enter your phone'--%>
<%--//                                }--%>
<%--//                            ]--%>
<%--//                        },--%>
<%--//                        ID: {--%>
<%--//                            identifier: 'ID',--%>
<%--//                            rules: [--%>
<%--//                                {--%>
<%--//                                    type: 'empty',--%>
<%--//                                    prompt: 'Please enter your ID'--%>
<%--//                                }--%>
<%--//                            ]--%>
<%--//                        },--%>
<%--//                        skills: {--%>
<%--//                            identifier: 'skills',--%>
<%--//                            rules: [--%>
<%--//                                {--%>
<%--//                                    type: 'minCount[2]',--%>
<%--//                                    prompt: 'Please select at least two skills'--%>
<%--//                                }--%>
<%--//                            ]--%>
<%--//                        },--%>
<%--//                        position: {--%>
<%--//                            identifier: 'position',--%>
<%--//                            rules: [--%>
<%--//                                {--%>
<%--//                                    type: 'empty',--%>
<%--//                                    prompt: 'Please select a position'--%>
<%--//                                }--%>
<%--//                            ]--%>
<%--//                        },--%>
<%--//                        superior: {--%>
<%--//                            identifier: 'superior',--%>
<%--//                            rules: [--%>
<%--//                                {--%>
<%--//                                    type: 'empty',--%>
<%--//                                    prompt: 'Please select a superior'--%>
<%--//                                }--%>
<%--//                            ]--%>
<%--//                        },--%>
<%--//                        department: {--%>
<%--//                            identifier: 'department',--%>
<%--//                            rules: [--%>
<%--//                                {--%>
<%--//                                    type: 'empty',--%>
<%--//                                    prompt: 'Please select a department'--%>
<%--//                                }--%>
<%--//                            ]--%>
<%--//                        },--%>
<%--//                        username: {--%>
<%--//                            identifier: 'username',--%>
<%--//                            rules: [--%>
<%--//                                {--%>
<%--//                                    type: 'empty',--%>
<%--//                                    prompt: 'Please enter a username'--%>
<%--//                                }--%>
<%--//                            ]--%>
<%--//                        },--%>
<%--//                        password: {--%>
<%--//                            identifier: 'password',--%>
<%--//                            rules: [--%>
<%--//                                {--%>
<%--//                                    type: 'empty',--%>
<%--//                                    prompt: 'Please enter a password'--%>
<%--//                                },--%>
<%--//                                {--%>
<%--//                                    type: 'minLength[6]',--%>
<%--//                                    prompt: 'Your password must be at least {ruleValue} characters'--%>
<%--//                                }--%>
<%--//                            ]--%>
<%--//                        },--%>
<%--//                        checked: {--%>
<%--//                            identifier: 'checked',--%>
<%--//                            rules: [--%>
<%--//                                {--%>
<%--//                                    type: 'checked',--%>
<%--//                                    prompt: 'You must agree to the terms and conditions'--%>
<%--//                                }--%>
<%--//                            ]--%>
<%--//                        },--%>
<%--//--%>
<%--//                        start_time: {--%>
<%--//                            identifier: 'start_time',--%>
<%--//                            rules: [--%>
<%--//                                {--%>
<%--//                                    type: 'empty',--%>
<%--//                                    prompt: 'Please enter '--%>
<%--//                                }--%>
<%--//                            ]--%>
<%--//                        },--%>
<%--//                        finish_time: {--%>
<%--//                            identifier: 'finish_time',--%>
<%--//                            rules: [--%>
<%--//                                {--%>
<%--//                                    type: 'empty',--%>
<%--//                                    prompt: 'Please enter '--%>
<%--//                                }--%>
<%--//                            ]--%>
<%--//                        }--%>
<%--//--%>
<%--//--%>
<%--//                    })--%>
<%--//            ;--%>
    <script>
        document.getElementById('entry_side').style.backgroundColor= "#ff7770"
        document.getElementById('entry_side').style.color= "white"
    </script>
<%@include file="templates/footer.jsp" %>