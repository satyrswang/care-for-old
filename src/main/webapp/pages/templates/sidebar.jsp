<%@page contentType="text/html; charset=utf-8" language="java" pageEncoding="UTF-8" %>
<div class="sidebar">
  <div class="ui vertical menu">
    <a class="item" href="/OCare/map/home" id="map_side">
      &nbsp;&nbsp;&nbsp;&nbsp;实时地图
      <i class="map icon"></i>
    </a>
    <a class="item" href="/OCare/monitor/list" style="width: 20rem;" id="elder_monitor_side">
      &nbsp;&nbsp;&nbsp;&nbsp;监护人申请审核
      <i class="checkmark icon"></i>
    </a>
    <div id="employee">
        <div class="header item">
            <i class="legal icon"></i>
            人事管理
        </div>
        <a class="item" href="/OCare/hr" id="hr_side">
            &nbsp;&nbsp;&nbsp;&nbsp;职员信息
        </a>
        <a class="item" href="/OCare/hr/entry" id="entry_side">
            &nbsp;&nbsp;&nbsp;&nbsp;入职管理
        </a>
        <a class="item" href="/OCare/hr/leave" id="leave_side">
            &nbsp;&nbsp;&nbsp;&nbsp;离职管理
        </a>
        <a class="item" href="/OCare/hr/table" id="info_side">
            &nbsp;&nbsp;&nbsp;&nbsp;查询
        </a>
        <a class="item" href="/OCare/hr/import" id="import_side">
            &nbsp;&nbsp;&nbsp;&nbsp;导入
        </a>
    </div>
    <div id=contract">
        <div class="header item">
          <i class="legal icon"></i>
          合同管理
        </div>
    </div>

    <a class="item" href="/OCare/ht/table">
      &nbsp;&nbsp;&nbsp;&nbsp;合同列表
    </a>
    <a class="item" href="/OCare/ht/upload">
      &nbsp;&nbsp;&nbsp;&nbsp;上传合同
    </a>

    <div id="company">
        <div class="header item">
          <i class="book icon "></i>
          机构信息管理
        </div>
        <a class="item company_list" href="/OCare/company/companyList" id="company_list">
          &nbsp;&nbsp;&nbsp;&nbsp;机构列表
        </a>
        <a class="item" href="/OCare/company/list" id="apply_list">
          &nbsp;&nbsp;&nbsp;&nbsp;机构申请审核
        </a>

    </div>
    <div id="personal">
        <div class="header item">
          <i class="child icon "></i>
          个人管理
        </div>
        <a class="item" href="/OCare/relative">
            &nbsp;&nbsp;&nbsp;&nbsp;监护人
        </a>
        <a class="item" href="/OCare/volunteer">
            &nbsp;&nbsp;&nbsp;&nbsp;志愿者
        </a>
    </div>



  </div>
</div>

<script>
  var type = "<%= session.getAttribute("sessionType") %>";
  if(type==1||type==0||type==2||type==3){
    document.getElementById("company").style.display="none";
    document.getElementById("personal").style.display="none";
  }

  $('.company_list').click(function(){
      $.ajax({
          url:'/OCare/company/companyList',
          type:'post',
          data:{
              sessionId:null
          }
      })
  })
</script>