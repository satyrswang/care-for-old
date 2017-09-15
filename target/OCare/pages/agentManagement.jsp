<%--
  Created by IntelliJ IDEA.
  User: douyutong
  Date: 2015/10/26
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html; charset=utf-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="templates/header.jsp"%>
<link rel="stylesheet" href="/OCare/Assets/CSS/agentManagement.css">
<div>
  <div>
    <div class="mainbox">
        <h2>请选择您要进入的机构</h2>
        <input type="text" id="get_status" style="display:none"/>
        <table class="ui stripe table" >
          <thead>
            <th>机构ID</th><th>机构名称</th><th id="address">机构地址</th><th>状态</th>
          </thead>
          <tbody id="company_list">
          </tbody>
        </table>

        <a href="/OCare/pages/applyCompany.jsp" style="float:right;margin-top: 20px">
          <button class="ui inverted teal button"><i class="add icon"></i>注册机构</button>
        </a>
      </div>

    </div>
</div>
<script>

    $( document ).ready(function get_list(){
                $.ajax({
                    url:'/OCare/app/getCompanyByLegalPersonId',
                    type:'POST',
                    async: false,
//                    data:{
//                        sessionId: null
//                    },
                    success:function(data){
                        if(data.error == false){
                            var table = document.getElementById("company_list");
                            for (var i = 0; i < data.companyList.length;i++){
                                var row = table.insertRow(0);
                                var com_id = data.companyList[i].id;
                                row.id = "tr_" + com_id;
                                console.log(row.id);
                                row.setAttribute('data-index', com_id);
                                var cell1 = row.insertCell(0);
                                var cell2 = row.insertCell(1);
                                var cell3 = row.insertCell(2);
                                var cell4 = row.insertCell(3);
                                cell1.innerHTML = data.companyList[i].id;
                                cell2.innerHTML = data.companyList[i].name;
                                cell3.innerHTML = data.companyList[i].address;
                                if(data.companyList[i].status == 101){
                                    cell4.innerHTML = "待审核";
                                }else if(data.companyList[i].status == 102){
                                    cell4.innerHTML = "已通过";
                                }else if(data.companyList[i].status == 103){
                                    cell4.innerHTML = "已拒绝"
                                }else{
                                    cell4.innerHTML = "未知状态"
                                }

                            }
                        }else{
                            alert(data.errorMsg);
                        }

                    },
                    error:function(data){
                        alert("can not get the data");
                    }
                })
//        事件冒泡处理
        $(document).on('click', '#company_list tr', function() {
            var index = this.getAttribute('data-index');
            $.ajax({
                url:'/OCare/company/sessionCompanyId',
                type:'get',
                data:"&id="+index,
                success:function(data){
                    window.location.href="/OCare/pages/homepage.jsp"
                },
                error:function(data){
                    alert("sth wrong");
                    console.log(index);
                }
            });
        });
    });

</script>

<div class="footer" style="position: absolute;">
    @ copyright Tongji University
</div>

</body>
</html>
