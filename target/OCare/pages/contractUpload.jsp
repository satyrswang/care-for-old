<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2015/7/31
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html; charset=utf-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="templates/header.jsp"%>
<%@include file="templates/sidebar.jsp"%>
<link rel="stylesheet" href="/OCare/Assets/CSS/homepage.css">
<link rel="stylesheet" href="/OCare/dist/dropzone.css">
<link rel="stylesheet" href="/OCare/dist/style.css">
<link rel="stylesheet" type="text/css" href="/OCare/dist/sweetalert.css">


<div class="main_content">

    <form action="/OCare/app/contract/upload"
          class="dropzone"
          id="my-awesome-dropzone"
          enctype="multipart/form-data">

        <div class="dz-message">
            <h2>将文件拖至此处或点击上传.</h2><br>
            <label>放入文件后点击<strong>上传</strong>按钮</label>

        </div>

    </form>

    <a class="ui huge teal label" onclick="processQueue()"  id="agree">
        <i class=""></i>     上传
    </a>

</div>




<script src="/OCare/Assets/JS/jquery-2.1.4.js"></script>
<script src="/OCare/dist/dropzone.js"></script>
<script src="/OCare/dist/sweetalert.min.js"></script>

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

<%@include file="templates/footer.jsp"%>