<%@ page import="java.util.List" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="templates/header.jsp"%>
<%@include file="templates/sidebar.jsp"%>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.2&ak=ZFra3LYWoS5BRU11s8DFw1GG"></script>
<link rel="stylesheet" href="/OCare/Assets/CSS/homepage1.css">
<link rel="stylesheet" href="/OCare/Assets/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="/OCare/Assets/bootstrap-table/src/bootstrap-table.css">
<link rel="stylesheet" href="/OCare/Assets/examples.css">
<link rel="stylesheet" type="text/css" href="/OCare/dist/sweetalert.css">


<div class="main_content">



    <div class="ui top attached tabular menu">
        <a class="active item" data-tab="first" id="mapinfo">地图</a>
        <a class="item" data-tab="second">警报信息</a>
    </div>
    <div class="ui bottom attached active tab segment" data-tab="first" >


        <div class="search">
            <div class="ui input" style="display: inline-block;">
                <input placeholder="查找老人..." type="text" style="width:500px" id="input_name">
            </div>
            <button class="ui teal button" onclick="FindOlder()" style="display: inline-block;"><i class="search icon"></i></button>
        </div>

        <div class="monitor" id="container"></div>


    </div>


    <div class="ui bottom attached tab segment" data-tab="second">



        <div id="toolbar">
            <button id="remove" class="btn btn-danger" disabled>
                <i class="glyphicon glyphicon-remove"></i> Delete
            </button>
        </div>
        <table id="table"
               data-toolbar="#toolbar"
               data-search="true"
               data-show-refresh="true"
               data-show-toggle="true"
               data-show-columns="true"
               data-detail-formatter="detailFormatter"
               data-minimum-count-columns="2"
               data-show-pagination-switch="true"
               data-pagination="true"
               data-id-field="id"
               data-page-list="[10, 25, 50, 100, ALL]"
               data-side-pagination="server"
               data-url="/OCare/app/alarm/get/set"
               data-response-handler="responseHandler">
        </table>
    </div>

    <script>
        var $table = $('#table'),
                $remove = $('#remove'),
                selections = [];

        $(function () {
            $table.bootstrapTable({
                height: getHeight(),
                columns: [
                    [
                        {
                            field: 'state',
                            checkbox: true,
                            rowspan: 2,
                            align: 'center',
                            valign: 'middle'
                        }, {
                        title: '警报id',
                        field: 'generateid',
                        rowspan: 2,
                        align: 'center',
                        valign: 'middle',
                        sortable: true,
                    }, {
                        title: '警报信息',
                        colspan: 6,
                        align: 'center'
                    }
                    ],
                    [
                        {
                            field: 'messagefrom',
                            title: '报警人id',
                            sortable: true,
                            align: 'center'
                        }, {
                        field: 'price',
                        title: '报警时间',
                        sortable: true,
                        align: 'center'
                    },
                        {
                            field: 'talk_alarm',
                            title: '警报类型',
                            sortable: true,
                            align: 'center'
                        },
                        {
                            field: 'who_loc_log',
                            title: '报警经度',
                            sortable: true,
                            align: 'center'
                        },
                        {
                            field: 'who_loc_lat',
                            title: '报警纬度',
                            sortable: true,
                            align: 'center'
                        },

                        {
                            field: 'operate',
                            title: '操作',
                            align: 'center',
                            events: operateEvents,
                            formatter: operateFormatter
                        }
                    ]
                ]
            });
            // sometimes footer render error.
            setTimeout(function () {
                $table.bootstrapTable('resetView');
            }, 200);
            $table.on('check.bs.table uncheck.bs.table ' +
                    'check-all.bs.table uncheck-all.bs.table', function () {
                $remove.prop('disabled', !$table.bootstrapTable('getSelections').length);

                // save your data, here just save the current page
                selections = getIdSelections();
                // push or splice the selections if you want to save all data selections
            });
            $table.on('expand-row.bs.table', function (e, index, row, $detail) {
                if (index % 2 == 1) {
                    $detail.html('Loading from ajax request...');
                    $.get('LICENSE', function (res) {
                        $detail.html(res.replace(/\n/g, '<br>'));
                    });
                }
            });
            $table.on('all.bs.table', function (e, name, args) {
                console.log(name, args);
            });


            $remove.click(function () {

                swal({
                    title: "确定删除?",
                    text: "删除后将无法恢复！",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "是，我要删除!",
                    cancelButtonText: "取消",
                    closeOnConfirm: false,
                    closeOnCancel: false
                }, function (isConfirm) {
                    if (isConfirm) {

                        var ids = getIdSelections();
                        $table.bootstrapTable('remove', {
                            field: 'id',
                            values: ids
                        });
                        $remove.prop('disabled', true);

                        swal("已删除!", "已永久删除。", "success");
                    } else {
                        swal("取消", "未删除", "error");
                    }
                });



            });





            $(window).resize(function () {
                $table.bootstrapTable('resetView', {
                    height: getHeight()
                });
            });
        });








        function getIdSelections() {
            return $.map($table.bootstrapTable('getSelections'), function (row) {
                return row.id
            });
        }

        function responseHandler(res) {
            $.each(res.rows, function (i, row) {
                row.state = $.inArray(row.id, selections) !== -1;
            });
            return res;
        }

        function detailFormatter(index, row) {
            var html = [];
            $.each(row, function (key, value) {
                html.push('<p><b>' + key + ':</b> ' + value + '</p>');
            });
            return html.join('');
        }

        function operateFormatter(value, row, index) {
            return [
                '<a class="like" href="javascript:void(0)" title="Like">',
                '<i class="glyphicon glyphicon-screenshot"></i>',
                '</a>  ',
                '<a class="remove" href="javascript:void(0)" title="Remove">',
                '<i class="glyphicon glyphicon-remove"></i>',
                '</a>'
            ].join('');
        }

        window.operateEvents = {
            'click .like': function (e, value, row, index) {

                $('#mapinfo').click();
                map.centerAndZoom(new BMap.Point(row.who_loc_log,row.who_loc_lat),15);
//                alert('You click like action, row: ' + JSON.stringify(row));
            },
            'click .remove': function (e, value, row, index) {


                swal({
                    title: "确定删除?",
                    text: "删除后将无法恢复！",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "是，我要删除!",
                    cancelButtonText: "取消",
                    closeOnConfirm: false,
                    closeOnCancel: false
                }, function (isConfirm) {
                    if (isConfirm) {

                        $table.bootstrapTable('remove', {
                            field: 'id',
                            values: [row.id]
                        });

                        swal("已删除!", "已永久删除。", "success");
                    } else {
                        swal("取消", "未删除", "error");
                    }
                });







            }
        };



        function getHeight() {
            return $(window).height() - $('h1').outerHeight(true);
        }



    </script>





</div>





</div>

<script>
    $('.menu .item')
            .tab()
    ;
</script>

<script type="text/javascript">
    //***********************地图初始化****************************
    var map, geoc, point1;
    //    function loadScript() {
    //        var script = document.createElement("script");
    //        script.type = "text/javascript";
    //        script.src = "http://api.map.baidu.com/api?v=1.2&ak=ZFra3LYWoS5BRU11s8DFw1GG&callback=init";
    //        document.head.appendChild(script);
    //    }
    map = new BMap.Map("container");
    point1 = new BMap.Point(116.404, 39.915);
    map.centerAndZoom(point1, 14);
    map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
    map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
    geoc = new BMap.Geocoder();
    var option = {
        type: BMAP_NAVIGATION_CONTROL_LARGE,
        anchor: BMAP_ANCHOR_BOTTOM_RIGHT,
        offset: new BMap.Size(0, 5)
    };
    map.addControl(new BMap.NavigationControl(option));
    //RefreshCondition();

    <%--var points = [];--%>
    <%--var markers = [];--%>
    <%--var infoWindows = [];--%>

    <%--<c:forEach items="${Elders}" var="elder" varStatus="status">--%>
    <%--var point = new BMap.Point(${elder.getLatitude()}, ${elder.getLongtitude()});--%>
    <%--points.push(point);--%>
    <%--var marker = new BMap.Marker(points[${status.index}]);--%>
    <%--markers.push(marker);--%>
    <%--markers[0].setAnimation(BMAP_ANIMATION_BOUNCE);--%>
    <%--map.addOverlay(markers[${status.index}]);--%>
    <%--var infoWindow = new BMap.InfoWindow("<div style='width: 320px'><h4 style='margin:0 0 5px 0;padding:0.2em 0'>于子涵</h4>" +--%>
    <%--"<img style='float:right;margin:4px' id='imgDemo' src='./images/testIcon.jpg' width='90' height='100' title='老人头像'/>" +--%>
    <%--"<p style='margin:0;line-height:1.5;font-size:13px'>老人联系电话:<span>13500000000000</span></p>" +--%>
    <%--"<p style='margin:0;line-height:1.5;font-size:13px'>老人监护人联系电话:<span>13500000000000</span></p>" +--%>
    <%--"<p style='margin:0;line-height:1.5;font-size:13px'>老人状态:<span>" + "${elder.getStatus()}" + "</span></p>" +--%>
    <%--"<p style='margin:0;line-height:1.5;font-size:13px'>老人当前地址:<span>上海XXX区XXX路XX号</span></p>" +--%>
    <%--"<p style='margin:0;line-height:1.5;font-size:13px'>老人外出原因:<span>看病</span></p>" +--%>
    <%--"<p style='margin:0;line-height:1.5;font-size:13px'><a href='#' style='text-decoration: none'>查看详情 >>></a> </p>" +--%>
    <%--"</div>");--%>
    <%--infoWindows.push(infoWindow);--%>
    <%--markers[${status.index}].addEventListener("mouseover", function () {--%>
    <%--this.openInfoWindow(infoWindows[${status.index}]);--%>
    <%--});--%>
    <%--</c:forEach>--%>
    <%--map.setViewport(points);--%>

    <%----%>
    <%--window.onload = loadScript;--%>

    // ******************实时刷新老人位置********************

    //定义一个点集
    var new_points = [];
    //var new_markers = [];
    //var new_infoWindows = [];


    //根据老人身份证号查找老人并且显示zai地图中心
    function FindOlder(){
        //alert($("#input_name").val());
        $.ajax({
            url:"/OCare/app/map/getElderPresentLocationById",
            data:{elderId:$("#input_name").val()},
            dataType:"json",
            success:function(data){
                // alert(data.latitude);//测试pass
                var result_point = new BMap.Point(data.latitude, data.longtitude);
                map.centerAndZoom(result_point, 15);
                clearInterval(getIntervalId);

            }
        })
    }

    //var getIntervalId = setInterval("RefreshCondition()", 5000);
    //    $.ajax({
    //        url: "/OCare/app/map/allEldersPresentLocationInfo",
    //        type: "GET",
    //        // async: false, //同步执行
    //        dataType: "json",
    //        success: function (data) {
    //            //定义一个点的集合
    //            new_points = [];
    //            //清楚所有覆盖物
    //            map.clearOverlays();
    //
    //            for (var i = 0; i < data.resultNum; i++) {
    //                //alert(data[i].elder_status);
    //                //获取点
    //                var p = new BMap.Point(data.result[i].latitude, data.result[i].longtitude);
    //                //定义一个表示点的实际地址的变量
    //                var adress = "暂时无法获得";
    //                //将点加入点集
    //                new_points.push(p);
    //                //定义标注物
    //                var m = new BMap.Marker(p);
    //                //将标注物加入地图
    //                map.addOverlay(m);
    //                //设置老人有紧急情况时的状态表示
    //                if (data.result[i].status == 1) {
    //                    m.setAnimation(BMAP_ANIMATION_BOUNCE);
    //                }
    //
    //                //？逆地址解析(无法修改外部变量)
    //                geoc.getLocation(p, function (rs) {
    //                    var addComp = rs.addressComponents;
    //                    adress = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
    //                    // alert(adress);
    //                    // alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
    //
    //                });
    //                //初始化信息窗口内容
    //                var info = new BMap.InfoWindow("<div style='width: 320px'><h4 style='margin:0 0 5px 0;padding:0.2em 0'>"+data.result[i].elder_id+"</h4>" +
    //                        "<img style='float:right;margin:4px' id='imgDemo' src='../Assets/Images/testIcon.jpg' width='90' height='100' title='老人头像'/>" +
    //                        "<p style='margin:0;line-height:1.5;font-size:13px'>老人联系电话:<span>-</span></p>" +
    //                        "<p style='margin:0;line-height:1.5;font-size:13px'>老人监护人联系电话:<span>-</span></p>" +
    //                        "<p style='margin:0;line-height:1.5;font-size:13px'>老人状态:<span>" + data.result[i].status + "</span></p>" +
    //                        "<p style='margin:0;line-height:1.5;font-size:13px'>老人当前地址:<span>" + adress + "</span></p>" +
    //                        "<p style='margin:0;line-height:1.5;font-size:13px'>老人外出原因:<span>-</span></p>" +
    //                        "<p style='margin:0;line-height:1.5;font-size:13px'><a href='#' style='text-decoration: none'>查看详情 >>></a> </p>" +
    //                        "</div>");
    //
    //                //给标注物增加信息窗口
    //                m.addEventListener("mouseover", function () {
    //                    this.openInfoWindow(info);
    //                });
    //            }
    //            map.setViewport(new_points);
    //
    //        }
    //
    //    })



    $.ajax({
        url: "/OCare/app/map/allEldersPresentLocationInfo",
        type: "GET",
        // async: false, //同步执行
        dataType: "json",
        success: function (data) {
            //定义一个点的集合
            new_points = [];
            //清楚所有覆盖物
            map.clearOverlays();

            for (var i = 0; i < data.resultNum; i++) {

                addAction(data.result[i]);
            }
            map.setViewport(new_points);

        }

    })
















    var data1;
    var mark;

    function addAction(data){

        var p = new BMap.Point(data.latitude, data.longtitude);
        new_points.push(p);
        geoc.getLocation(p, function (rs) {

            //获取点

            //定义一个表示点的实际地址的变量
            var addComp = rs.addressComponents;
            var adress = addComp.province + addComp.city + addComp.district + addComp.street + addComp.streetNumber;
            //将点加入点集

            //定义标注物
            var m = new BMap.Marker(p);
            //将标注物加入地图
            map.addOverlay(m);


            //设置老人有紧急情况时的状态表示
            if (data.status == 1) {
                m.setAnimation(BMAP_ANIMATION_BOUNCE);
            }
            var s = "";
            for(var i = 0;i < data.elderByElderId.elderMonitorsById.length;i++)
            {

                s = s + "<p style='margin:0;line-height:1.5;font-size:13px'>老人监护人:<span>"+data.elderByElderId.elderMonitorsById[i].relativeByRelativeId.name+"</span>&nbsp监护人联系电话:<span>"+data.elderByElderId.elderMonitorsById[i].relativeByRelativeId.phone+"</span></p>";
            }

            data1=s;
            //初始化信息窗口内容
            var info = new BMap.InfoWindow("<div style='width: 320px'><h4 style='margin:0 0 5px 0;padding:0.2em 0'>"+data.elder_id+"</h4>" +
                    "<img style='float:right;margin:4px' id='imgDemo' src='../Assets/Images/testIcon.jpg' width='90' height='100' title='老人头像'/>" +
                        //"<p style='margin:0;line-height:1.5;font-size:13px'>老人联系电话:<span>-</span></p>" +
                    s +
                    "<p style='margin:0;line-height:1.5;font-size:13px'>老人状态:<span>" + data.status + "</span></p>" +
                    "<p style='margin:0;line-height:1.5;font-size:13px'>老人当前地址:<span>" + adress + "</span></p>" +
                        //"<p style='margin:0;line-height:1.5;font-size:13px'><a href='#' style='text-decoration: none'>查看详情 >>></a> </p>" +
                    "</div>");

            //给标注物增加信息窗口
            addClickHandler(info,m);

            map.setViewport(new_points);

        });




    }



    function addClickHandler(info,marker){
        marker.addEventListener("mouseover",function(e){
                    openInfo(info,e)}
        );
    }
    function openInfo(infoWindow,e){
        var p = e.target;
        var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
        map.openInfoWindow(infoWindow,point); //开启信息窗口
    }

    function RefreshCondition() {



        $.ajax({
            url: "/OCare/app/map/allEldersPresentLocationInfo",
            type: "GET",
            // async: false, //同步执行
            dataType: "json",
            success: function (data) {
                //定义一个点的集合
                new_points = [];
                //清楚所有覆盖物
                map.clearOverlays();

                for (var i = 0; i < data.resultNum; i++) {
                    //alert(data[i].elder_status);
                    //获取点
                    var p = new BMap.Point(data.result[i].latitude, data.result[i].longtitude);
                    //定义一个表示点的实际地址的变量
                    var adress = "暂时无法获得";
                    //将点加入点集
                    new_points.push(p);
                    //定义标注物
                    var m = new BMap.Marker(p);
                    //将标注物加入地图
                    map.addOverlay(m);
                    //设置老人有紧急情况时的状态表示
                    if (data.result[i].status == 1) {
                        m.setAnimation(BMAP_ANIMATION_BOUNCE);
                    }





                    //给标注物增加信息窗口
                    addAction(p,m,data[i]);

                }

            }

        })
    }
</script>



<script src="/OCare/Assets/bootstrap/js/bootstrap.min.js"></script>
<script src="/OCare/Assets/bootstrap-table/src/bootstrap-table.js"></script>
<script src="/OCare/Assets/bootstrap-table/src/extensions/toolbar/bootstrap-table-toolbar.js"></script>
<script src="/OCare/ga.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.9.1/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="/OCare/dist/sweetalert.min.js"></script>


<%@include file="templates/footer.jsp"%>