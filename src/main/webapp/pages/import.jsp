<%--
  Created by IntelliJ IDEA.
  User: 重书
  Date: 2015/10/20
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html; charset=utf-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="templates/header.jsp"%>
<%@include file="templates/sidebar.jsp"%>
<link rel="stylesheet" href="/OCare/Assets/CSS/homepage.css">
<div class="ui container Entry">
    <div class="ui three column grid">
        <div class="column">
            <form action="/OCare/hr/upload"
                  method="post"
                  class="dropzone"
                  id="my-awesome-dropzone"
                  enctype="multipart/form-data">
              <div class="ui button">
              <input id="File1" runat="server" name="file" type="file" />
              </div>

              <button class="ui fluid button" style="background-color: transparent;border: 0px;" >
                  <a class="ui huge fluid teal label" onclick="processQueue()"  id="agree">职工信息导入</a>
              </button>
            </form>

        </div>
    </div>
</div>
<script>
    function processQueue()
    {
        myDropzone.processQueue();
    }

    Dropzone.options.myAwesomeDropzone = {
        paramName: "file", // The name that will be used to transfer the file
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


</script>


<script>
    document.getElementById('import_side').style.backgroundColor= "#ff7770"
    document.getElementById('import_side').style.color= "white"
</script>
<%@include file="templates/footer.jsp"%>