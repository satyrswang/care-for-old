<%--
  Created by IntelliJ IDEA.
  User: douyutong
  Date: 2015/12/1
  Time: 23:00
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html; charset=utf-8" language="java" pageEncoding="UTF-8" %>
<%@include file="templates/header.jsp"%>
<link rel="stylesheet" href="/OCare/Assets/CSS/wait.css">
<div>
  <div>
    <form class="ui mainbox main_form form" style="min-height: 450px;" >
      <%--<div class="imgbox">--%>
        <%--<img src="/OCare/Assets/Images/message1.png" style="margin-top: -70px;width: 330px;height: 350px;display: inline-block;margin-left: -30px;  opacity: 0.3;padding:0 0 0 0">--%>
      <%--</div>--%>
      <div class="textbox" style="width: 500px">
        <h2 style="font-size: 25px;font-weight: bold;color: #4D536F;margin:0 0 0 0;text-align: center;margin-bottom: 10px;">忘记密码</h2>
        <div class="ui input" style="display: inline">
          <input type="text" id="phoneNum" placeholder="手机号..." style="margin: 10px 0px; width: 358px;display: inline-block">
        </div>
        <div class="ui selection dropdown" style="margin: 10px 0px;display: inline-block">
          <input type="hidden" name="role" id="role_choice">
          <div class="default text">选择角色</div>
          <i class="dropdown icon"></i>
          <div class="menu">
            <div class="item" data-value= 1>老人或亲属</div>
            <div class="item" data-value= 2>法人   </div>
            <div class="item" data-value= 3>公司员工 </div>
            <div class="item" data-value= 4>Admin</div>
            <div class="item" data-value= 5>志愿者 </div>
          </div>
        </div>
        <div class="ui input" style="display: inline">
          <input type="text" id="code" placeholder="验证码..."style="margin: 10px 0px;  width: 362px;">
        </div>
        <button class="ui inverted teal code_button button" id="the_button" style="display: inline-block;margin: 10px 0;">获取验证码</button>
        <div class="ui input" style="display: inline">
          <input type="text" id="id" placeholder="身份证号..."style="margin: 10px 0px;  width: 362px;">
        </div>
        <div class="ui input" style="display: inline">
          <input type="password" id="password" placeholder="新密码..."style="margin: 10px 0px;  width: 362px;">
        </div>
        <div class="ui input" style="display: inline">
          <input type="password" id="confirm_password" placeholder="密码确认..."style="margin: 10px 0px;  width: 362px;">
        </div>
        <div class="ui fluid positive large main-submit submit button" style="margin-top: 20px">确定</div>
        <div class="ui error message" style="  width: 100%;margin-left: 0px;"></div>
        <div id="reset_errorMsg" style="text-align: center;border-radius: 5px;background-color: #F1D7D7;font-style: inherit;font-size: 15px;color: #A95252;width: 90%;margin-left: 20px;padding: 4px 5px;margin-bottom: -2em;display:none"></div>
      </div>
    </form>
  </div>
</div>
<div class="footer">
  @ copyright Tongji University
</div>
<script>
  $('.ui.dropdown')
          .dropdown()
  ;
  var code;
  $('.code_button').on('click', function(e){
    e.preventDefault();
    $.ajax({
      url:'/OCare/app/code',
      cache: false,
      async: false,
      type: 'get',
      data: 'phoneNum='+$('#phoneNum').val(),
      success:function(data){
        alert("verify code has been sent");
        code = data.code;
        console.log(code);

      },
      error: function(error){
        alert("failed to send the verify code");
      }
    })
  });


  $('.main_form').on('submit',function(e){
    e.preventDefault();
    $.ajax({
      url:'/OCare/app/lostPasswordHandle',
      type:'post',
      async:false,
      data:{
        id: $('#id').val(),
        code: $('#code').val(),
        role:$('#role_choice').val(),
        password:$('#password').val(),
        phoneNum:$('#phoneNum').val(),
        sessionId: null
      },
      datatype:'JSONP',
      success:function(data){
        if(data.error == true){
          document.getElementById("reset_errorMsg").style.display="";
          document.getElementById('reset_errorMsg').innerText = data.errorMsg;
        }else{
          alert("You can use new password to logon now!");
          document.getElementById("reset_errorMsg").style.display="none";
          document.forms[0].action = "/OCare/pages/index.jsp";
          document.forms[0].submit();
        }
      },
      error:function(data){
        alert("failed to reset")
      }
    })
  })
  $('.ui.form')
          .form({
            code:{
              identifier:'code',
              rules:[{
                type:'empty',
                prompt:"请输入验证码"
              }]
            },
            password: {
              identifier: 'password',
              rules: [{
                type: 'empty',
                prompt: "请设置您的密码"
              }, {
                type: 'length[3]',
                prompt: "密码最少长度为3个字符"
              }]
            },
            confirmPassword: {
              identifier: 'confirm_Password',
              rules: [{
                type: 'match[legalperson_password]',
                prompt: "密码不符"
              }]
            },
            role: {
              identifier: 'role_choice',
              rules:[{
                type:'empty',
                prompt:"请选择您的角色"
              }]
            }
          })
</script>
</body>
</html>