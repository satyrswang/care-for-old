
<%--
  Created by IntelliJ IDEA.
  User: douyutong
  Date: 2015/11/18
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html; charset=utf-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="/OCare/Assets/CSS/agentManagement.css">
<%@include file="templates/header.jsp"%>
<c:if test="${errMsg != null}">

    <input type="hidden" value="${errMsg}" id="errMessage"/>
</c:if>

<script>
    function test(){
        var aa = $("#errMessage").val();
        if(aa!=null){
            alert(aa);
        }

    }
    window.onload = test();
</script>
<div style="width:1250px;margin:30px auto;border-radius: 10px;background-color: rgb(237, 237, 237);padding: 20px;">
    <h2 style="text-align: center;margin-bottom: -20px;font-family: monospace;color:darkcyan;" >申请机构:</h2>
    <form class="ui agent-submit form" method="post"
          id="agentForm"
          action="/OCare/app/company/register"
          enctype="multipart/form-data">
        <table style="margin: 30px 100px;">
            <tr style="line-height: 80px;">
                <td style="width: 200px;">机构名称：</td>
                <td>
                    <div class="ui input"><input type="text" id="agent_name" name="agent_name" style="width:300px"></div>
                </td>
                <td>
                    <div class="ui teal button" style="width: 200px;background-color: #78D6CC;margin-left: 30px;">营业执照上传
                    <input type="file" style="opacity:0;margin-top:-23px" accept="image/jpeg,image/bmp,image/png,image/gif" onchange="licence_pic.value=this.value">
                    </div>
                    <input type="file" name="pic1">

                </td>
                <td>
                   <div class="ui input"><input type="text" id="licence_pic" style="width:300px"></div>
                </td>
            </tr>
            <tr style="line-height: 80px;">
                <td>组织结构代码证号：</td>
                <td>
                    <div class="ui input"><input type="text" id="agent_code" name="agent_code" style="width:300px"></div>
                </td>
                <td>
                <div class="ui teal button" style="width: 200px;background-color: #78D6CC;margin-left: 30px;">
                    组织代码证上传
                    <input type="file" style="opacity: 0;margin-top:-23px" accept="image/jpeg,image/bmp,image/png,image/gif" onchange="code_pic.value=this.value" />
                </div>
                    <input type="file" name="pic2">
                </td>
                <td>
                    <div class="ui input"><input type="text" id="code_pic" style="width:300px"></div>
                </td>
          <tr style="line-height: 80px;">
            <td>公司电话：</td>
            <td>
              <div class="ui input"><input type="text" id="company_phone" name="company_phone" style="width:300px"></div>
            </td>
          </tr>
            <tr style="line-height: 80px;">
                <td>公司地址：</td>
                <td>
                    <div class="ui input"><input type="text" id="company_address" name="company_address" style="width:300px"></div>
                </td>
            </tr>
        </table>

        <div class="ui checkbox" style="margin-left: 130px;">
            <input name="isAgree" type="checkbox"/>
            <label>我同意相关协议</label>
        </div>
        <div class="ui fluid positive large submit button" style="margin: 30px 100px;width:80%">
            提交
        </div>
        <div class="ui error message" style="width: 80%;margin-left: 100px;"></div>
        <div id="apply_errorMsg" style="  margin-top: -20px;text-align: left;border-radius: 5px;font-style: inherit;font-size: 15px;color: rgb(169, 82, 82);width: 77.21%;margin-left: 100px;padding: 4px 5px;margin-bottom: -2em;background-color: rgb(241, 215, 215);padding-left: 30px;padding-bottom: 20px;display:none"></div>
    </form>
</div>

<script>
  $('.ui.form')
          .form({
            agent_name:{
              identifier:'agent_name',
              rules:[{
                type:'empty',
                prompt:"请输入机构名称"
              }]
            },
            legal_id:{
              identifier:'legalperson_id',
              rules:[{
                type:'empty',
                prompt:"请输入身份证号"
              }]
            },
            code :{
              identifier:'agent_code',
              rules:[{
                type:'empty',
                prompt:"请输入组织机构代码证号"
              }]
            },
            isAgree: {
              identifier: 'isAgree',
              rules: [{
                type: 'checked',
                prompt: "您必须先同意相关条例"
              }]
            }
          });

  $('.ui.radio.checkbox')
          .checkbox()
  ;
  $('.ui.checkbox')
          .checkbox()
  ;
//  $('.agent-submit').on('submit',function(e){
//    e.preventDefault();
//            $.ajax({
//              url: '/OCare/app/company/register',
//              type: 'get',
//              async: false,
//              contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
//              processData: false,
//              data: 'companyName='+$('#agent_name').val()+'&companyLegalPersonId='+$('#legalperson_id').val()+'&companyPhone='+$('#company_phone').val()+'&companyAddress='+$('#company_address').val(),
//              dataType: 'JSON',
//              success: function(data) {
//                if(data.error == true){
//                    document.getElementById("apply_errorMsg").style.display="";
//                    document.getElementById('apply_errorMsg').innerText = data.errorMsg;
//                }else{
//                  document.getElementById("apply_errorMsg").style.display="none";
//                  document.forms[0].action = "/OCare/pages/WaitPermition.jsp";
//                  document.forms[0].submit();
//                }
//              },
//              error: function(data){
//                  alert("submit failed!");
//              }
//            })
//          })
</script>
<%@include file="templates/footer.jsp"%>