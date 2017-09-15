<%@ page import="java.util.List" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="templates/header.jsp"%>
<%@include file="templates/sidebar.jsp"%>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.2&ak=ZFra3LYWoS5BRU11s8DFw1GG"></script>

<link rel="stylesheet" href="/OCare/Assets/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="/OCare/Assets/bootstrap-table/src/bootstrap-table.css">
<link rel="stylesheet" href="/OCare/Assets/examples.css">

<link rel="stylesheet" href="/OCare/Assets/CSS/homepage1.css">

<style>
    .monitor{
        width: 1140px;
        height: 600px;
        overflow: hidden;
        position: relative;
        z-index: 0;
        color: #000;
        text-align: left;
        border-radius: 5px;
        border: 4px solid #26BED7;
        margin: 10px;
    }
    .monitor1{
        width: 850px;
        height: 600px;
        overflow: hidden;
        position: relative;
        z-index: 0;
        color: #000;
        text-align: left;
        border-radius: 5px;
        border: 4px solid #26BED7;
        margin: 10px;
    }
</style>

<div class="container main_content">




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
               data-url="/OCare/app/contract/listAllEldersContractAndMonitorsYu"
               data-response-handler="responseHandler">
        </table>






        <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-lg">Large modal</button>

        <div id="motai" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">


                    <div class="ui top attached tabular menu">
                        <a class="active item" data-tab="first">位置</a>
                        <a class="item" data-tab="second">合同</a>
                    </div>
                    <div class="ui bottom attached active tab segment" data-tab="first">

                        <div class="monitor1" id="allmap"></div>

                    </div>
                    <div class="ui bottom attached tab segment" data-tab="second"><a id="download" href="#">下载</a> </div>




                </div>
            </div>
        </div>































</div>






    <script>

        var folder;
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
                        title: '合同编号',
                        field: 'contract_id',
                        rowspan: 2,
                        align: 'center',
                        valign: 'middle',
                        sortable: true,
                    }, {
                        title: '合同信息',
                        colspan: 11,
                        align: 'center'
                    }
                    ],
                    [
                        {
                            field: 'old_name',
                            title: '参养老年人',
                            sortable: true,
                            align: 'center'
                        }, {
                        field: 'old_id',
                        title: '参养老年人身份证号',
                        sortable: true,
                        align: 'center'
                    },
                        {
                            field: 'keeper1_name',
                            title: '监护人1姓名',
                            sortable: true,
                            align: 'center'
                        },
                        {
                            field: 'keeper1_id',
                            title: '监护人1身份证号',
                            sortable: true,
                            align: 'center'
                        },{
                        field: 'keeper2_name',
                        title: '监护人2姓名',
                        sortable: true,
                        align: 'center'
                    },
                        {
                            field: 'keeper2_id',
                            title: '监护人2身份证号',
                            sortable: true,
                            align: 'center'
                        },{
                        field: 'execution',
                        title: '合同执行情况',
                        sortable: true,
                        align: 'center'
                    },{
                        field: 'date',
                        title: '合同起止日期',
                        sortable: true,
                        align: 'center'
                    },{
                        field: 'service',
                        title: '合同约定服务',
                        sortable: true,
                        align: 'center'
                    },{
                        field: 'payment',
                        title: '合同付费情况',
                        sortable: true,
                        align: 'center'
                    },

                        {
                            field: 'operate',
                            title: '位置',
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
                '</a>  '
            ].join('');
        }

        window.operateEvents = {
            'click .like': function (e, value, row, index) {

                $('#motai')
                        .modal('show')
                ;








                $.ajax({
                    url:"/OCare/app/map/getElderPresentLocationById",
                    data:{elderId:row.old_id},
                    dataType:"json",
                    success:function(data){

                        map.clearOverlays();
                        var p = new BMap.Point(data.latitude, data.longtitude);

                        //定义标注物
                        var m = new BMap.Marker(p);
                        //将标注物加入地图
                        map.addOverlay(m);

                        map.centerAndZoom(p, 15);

                    }
                });

                $.ajax({
                    url:"http://localhost:8080/OCare/app/contract/id",
                    data:{id:row.contract_id},
                    dataType:"json",
                    success:function(data){
                        folder = data.contracts.folder_name;
                        document.getElementById("download").href="ftp://ocare:ocare@202.120.163.167/Contracts/"+folder+"/"+folder+".doc";
                    }
                });


























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









<script>
    $('.menu .item')
            .tab()
    ;
</script>

<script>

    var map = new BMap.Map("allmap");    // 创建Map实例
    map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);  // 初始化地图,设置中心点坐标和地图级别
    map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
    map.setCurrentCity("北京");          // 设置地图显示的城市 此项是必须设置的
    map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放

</script>


<script src="/OCare/Assets/bootstrap/js/bootstrap.min.js"></script>
<script src="/OCare/Assets/bootstrap-table/src/bootstrap-table.js"></script>
<script src="/OCare/Assets/bootstrap-table/src/extensions/toolbar/bootstrap-table-toolbar.js"></script>
<script src="/OCare/ga.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.9.1/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="/OCare/dist/sweetalert.min.js"></script>


<%@include file="templates/footer.jsp"%>