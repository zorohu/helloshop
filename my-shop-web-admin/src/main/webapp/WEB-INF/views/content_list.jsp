<%--
  Created by IntelliJ IDEA.
  User: Author
  Date: 2019/3/14
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的商城 | 内容管理</title>
    <jsp:include page="../includes/headr.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../includes/nav.jsp" />
    <jsp:include page="../includes/menu.jsp" />
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                内容管理
                <small>Control panel</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
                <li class="active">控制面板</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${baseResult != null}">
                        <div id="alert-message" class="alert alert-${baseResult.status==200 ? "success" : "danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${baseResult.message}
                        </div>
                    </c:if>
                    <!-- Horizontal Form -->
                    <div class="box box-info box-info-search" style="display: none">
                        <div class="box-header with-border">
                            <h3 class="box-title">高级搜索</h3>
                        </div>

                        <div class="box-body">
                            <div class="row form-horizontal" style="padding-left: 12px">
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="title" class="col-sm-3 control-label">标题</label>

                                        <div class="col-sm-8">
                                            <input id="title" class="form-control" placeholder="标题"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="subTitle" class="col-sm-3 control-label">子标题</label>

                                        <div class="col-sm-8">
                                            <input id="subTitle" class="form-control" placeholder="子标题"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-3">
                                    <div class="form-group">
                                        <label for="titleDesc" class="col-sm-4 control-label">标题描述</label>

                                        <div class="col-sm-8">
                                            <input id="titleDesc" class="form-control" placeholder="标题描述"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="input-group">
                                    <button type="button" name="search" id="search-btn" class="btn btn-flat" onclick="search()"><i class="fa fa-search"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                        <!-- /.box-header -->
                    </div>
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">内容列表</h3>
                        </div>
                        <div class="box-boby" style="padding-left: 12px">
                            <a href="/content/form" type="button" class="btn btn-default btn-sm"><i class="fa fa-plus-square"> 新增</i></a>
                            <button class="btn btn-default btn-sm" onclick="App.deleteMulti('/content/delete')"><i class="fa fa-trash"> 删除</i></button>
                            <a href="#" type="button" class="btn btn-default btn-sm"><i class="fa fa-download"> 导入</i></a>
                            <a href="#" type="button" class="btn btn-default btn-sm"><i class="fa fa-upload"> 导出</i></a>
                            <button type="button" class="btn btn-primary btn-sm"
                                    onclick="$('.box-info-search').css('display') == 'none' ? $('.box-info-search').show('fast') : $('.box-info-search').hide('fast')">
                                <i class="fa fa-search"> 搜索</i>
                            </button>
                        </div>

                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <table id="dataTable" class="table table-hover">
                                <thead>
                                <tr>
                                    <th><input type="checkbox" class="minimal icheck_master" checked></th>
                                    <th>ID</th>
                                    <th>所属分类</th>
                                    <th>标题</th>
                                    <th>子标题</th>
                                    <th>标题描述</th>
                                    <th>链接</th>
                                    <th>图片1</th>
                                    <th>图片2</th>
                                    <th>更新时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
            </div>
            <!-- /.row -->
        </section>
    </div>
    <!-- /.content-wrapper -->
    <jsp:include page="../includes/copyright.jsp" />

    <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
    <div class="control-sidebar-bg"></div>
</div>

<jsp:include page="../includes/footer.jsp"/>

<sys:modal ></sys:modal>
<script>
    var _dataTable;
    $(function () {
        var _columns =
            [
                {
                    "data": function (row,type,val,meta) {
                        return '<input id="'+row.id+'" type="checkbox" class="minimal">';
                    }
                },
                { "data": "id" },
                { "data": "tbContentCategory.name" },
                { "data": "title" },
                { "data": "subTitle" },
                { "data": "titleDesc" },
                { "data": function (row,type,val,meta) {
                        return '<a href="'+row.url+'" target="_blank">查看</a> ';
                    } },
                { "data": function (row,type,val,meta) {
                        if (row.pic == null) {
                            return '';
                        }
                        return '<a href="'+row.pic+'" target="_blank">查看</a> ';
                    } },
                { "data": function (row,type,val,meta) {
                        if (row.pic2 == null) {
                            return '';
                        }
                        return '<a href="'+row.pic2+'" target="_blank">查看</a> ';
                    } },
                { "data": function (row,type,val,meta) {
                        return DateTime.format(row.updated,"yyyy-MM-dd HH:mm:ss");
                    }},
                { "data": function (row,type,val,meta) {
                        var dataURL = "/content/detail?id=" + row.id;
                        return  '<button type="button" class="btn btn-info" onclick="App.showDetail(\''+dataURL+'\')"><i class="fa fa-search">查看</i></button>\n' +
                            '<a href="/content/form?id='+row.id+'" type="button" class="btn btn-warning"><i class="fa fa-edit">编辑</i></a>\n' +
                            '<button class="btn btn-danger" onclick="App.delete(\'/content/delete\','+row.id+')"><i class="fa fa-trash">删除</i></button>';
                    }
                }
            ];
        _dataTable = App.initDataTables("/content/page",_columns);

    });

    function search() {
        var title = $("#title").val();
        var subTitle = $("#subTitle").val();
        var titleDesc = $("#titleDesc").val();

        var param = {
            "title":title,
            "subTitle": subTitle,
            "titleDesc": titleDesc
        };
        _dataTable.settings()[0].ajax.data = param;
        _dataTable.ajax.reload();
    }

</script>
</body>
</html>