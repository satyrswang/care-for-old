<%--
  Created by IntelliJ IDEA.
  User: douyutong
  Date: 2015/12/2
  Time: 8:57
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html; charset=utf-8" language="java" pageEncoding="UTF-8" %>
<%@include file="templates/header.jsp"%>
<link rel="stylesheet" href="/OCare/Assets/CSS/wait.css">
<script src="/OCare/Assets/JS/jquery-2.1.4.js"></script>
<script src="/OCare/Assets/JS/semantic.js"></script>
<script src="/OCare/Assets/JS/jquery.address.js"></script>
<div>
  <div class="mainbox" id="context1" style="  margin: 60px auto;width: 600px;padding: 30px;-webkit-box-shadow: 0px 0px 25px rgba(165, 173, 177, 0.5);-moz-box-shadow: 0px 0px 25px rgba(165, 173, 177, 0.5));box-shadow: 0px 0px 25px rgba(165, 173, 177, 0.5);">
    <div class="ui top attached tabular menu" style="margin-left: 30px;">
      <a class="active item " data-tab="ChangePhoneNum">
        修改手机号
      </a>
      <a class="item" data-tab="ChangePassword">
        修改密码
      </a>
      <a class="item" data-tab="ChangePicture">
        修改头像
      </a>
    </div>
    <div class="ui bottom attached active tab segment" data-tab="ChangePhoneNum">
        <div class="ui ChangePhoneNum_form form" style="  margin-top: 30px;">
          <label style="margin: 20px 0;">输入新的手机号：</label>
          <div class="ui input"><input type="text" id="new_tel" style="margin: 20px 0;"></div>
          <div class="ui input" style="display: inline"><input type="text" id="verify_code" style="margin: 20px 0;width:429px; display: inline"></div></td>
          <button class="ui teal verify-code button"  style="background-color: #78D6CC;">获取验证码</button>
          <div class="ui fluid positive large  phoneNum_submit button">
          确认修改
          </div>
        </div>
    </div>
    <div class="ui bottom attached tab segment" data-tab="ChangePassword">
      <div class="ui ChangePassword_form form" style="margin-top: 30px;">
        <label style="margin: 20px 0;">输入旧密码：</label>
        <div class="ui input"><input type="password" id="old_password" name="old_password" style="margin: 20px 0;"></div>

        <label style="margin: 20px 0;">输入新的密码：</label>
        <div class="ui input"><input type="password" id="new_password" name="new_password" style="margin: 20px 0;"></div>

        <label style="margin: 20px 0;">再次输入：</label>
        <div class="ui input"><input type="password" id="confirm_new_confirm" name="confirmPassword" style="margin: 20px 0;"></div>

        <div class="ui fluid positive large password_submit button" >
        确认修改
        </div>
      </div>
    </div>
    <div class="ui bottom attached tab segment" data-tab="ChangePicture">
      <div class="ui ChangePicture_form form">
        <table>
            <tr>
                <td rowspan="3" height="301" align="center">
                <div id="localImag1">
                <img id="preview1" src="" width="300" height="300" style="display: block;border-radius: 10px;  width: 230px;height: 250px;" />
                </div>
                </td>
              <td>
                <form method="get" action="xznetwork" name="textfile">
                  <div class="ui teal button" style="width: 200px;background-color: #78D6CC;">上传头像
                    <input type="file" name="file" id="doc1" multiple="multiple" style="opacity: 0;margin-top: -23px;" onchange="javascript:setImagePreview1();">
                  </div>
                </form>
              </td>
            </tr>
        </table>
        <div class="ui fluid positive large picture_submit button" >
          确认修改
        </div>
      </div>
    </div>
  </div>
</div>
<div class="footer" style="position: absolute;">
  <i class="Copyright icon"></i> copyright Tongji University
</div>
<script>
  $('.ui.dropdown')
          .dropdown()
  ;
  $('.menu.item')
          .tab()
  ;
  $('#context1 .menu .item')
          .tab({
            context: $('#context1')
          })
  ;
</script>
<script>
  function setImagePreview(avalue) {
    //input
    var docObj = document.getElementById("doc");
    //img
    var imgObjPreview = document.getElementById("preview");
    //div
    var divs = document.getElementById("localImag");

    if (docObj.files && docObj.files[0]) {

      //火狐下，直接设img属性

      imgObjPreview.style.display = 'block';

      imgObjPreview.style.width = '300px';

      imgObjPreview.style.height = '300px';

      //imgObjPreview.src = docObj.files[0].getAsDataURL();

      //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式

      imgObjPreview.src = window.URL.createObjectURL(docObj.files[0]);

    } else {

      //IE下，使用滤镜

      docObj.select();

      var imgSrc = document.selection.createRange().text;

      var localImagId = document.getElementById("localImag");

      //必须设置初始大小

      localImagId.style.width = "300px";

      localImagId.style.height = "300px";

      //图片异常的捕捉，防止用户修改后缀来伪造图片

      try {

        localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";

        localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;

      } catch(e) {

        alert("您上传的图片格式不正确，请重新选择!");

        return false;

      }

      imgObjPreview.style.display = 'none';

      document.selection.empty();

    }

    return true;

  }</script>
<script>
  function setImagePreview1(avalue) {
    //input
    var docObj1 = document.getElementById("doc1");
    //img
    var imgObjPreview1 = document.getElementById("preview1");
    //div
    var divs1 = document.getElementById("localImag1");

    if (docObj1.files && docObj1.files[0]) {

      //火狐下，直接设img属性

      imgObjPreview1.style.display = 'block';

      imgObjPreview1.style.width = '300px';

      imgObjPreview1.style.height = '300px';

      //imgObjPreview1.src = docObj1.files[0].getAsDataURL();

      //火狐7以上版本不能用上面的getAsDataURL()方式获取，需要一下方式

      imgObjPreview1.src = window.URL.createObjectURL(docObj1.files[0]);

    } else {

      //IE下，使用滤镜

      docObj1.select();

      var imgSrc = document.selection.createRange().text;

      var localImagId = document.getElementById("localImag");

      //必须设置初始大小

      localImagId.style.width = "300px";

      localImagId.style.height = "300px";

      //图片异常的捕捉，防止用户修改后缀来伪造图片

      try {

        localImagId.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";

        localImagId.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgSrc;

      } catch(e) {

        alert("您上传的图片格式不正确，请重新选择!");

        return false;

      }

      imgObjPreview1.style.display = 'none';

      document.selection.empty();
    }
    return true;
  }</script>
<script src="/OCare/Assets/JS/Modification.js"></script>
</body>
</html>
