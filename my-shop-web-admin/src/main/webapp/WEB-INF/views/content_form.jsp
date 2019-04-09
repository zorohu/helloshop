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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>

<!DOCTYPE html>
<html>
<head>
    <title>我的商城 | 内容管理</title>
    <jsp:include page="../includes/headr.jsp"/>
    <link rel="stylesheet" href="/static/assets/plugins/jquery-ztree/css/zTreeStyle/zTreeStyle.min.css">
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/dropzone.css">
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/min/basic.min.css">
    <link rel="stylesheet" href="/static/assets/plugins/wangEidtor/wangEditor.min.css">
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
            <!-- right column -->
            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${baseResult != null}">
                        <div class="alert alert-${baseResult.status==200 ? "success" : "danger"} alert-dismissible">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${baseResult.message}
                        </div>
                    </c:if>
                    <!-- Horizontal Form -->
                    <div class="box box-info">
                        <div class="box-header with-border">
                            <h3 class="box-title">${tbContent.id == null ? "新增":"编辑"}新增内容</h3>
                        </div>
                        <!-- /.box-header -->
                        <form:form id="inputForm" cssClass="form-horizontal" action="/content/save" method="post" modelAttribute="tbContent">
                            <form:hidden path="id" />
                            <div class="box-body">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label">父级类目</label>

                                    <div class="col-sm-10">
                                        <form:hidden id="categoryId" path="tbContentCategory.id"/>
                                        <%--<input id="categoryName" class="form-control required" placeholder="${tbContentCategory == null ? "请选择":tbContentCategory.name}" readonly="true" data-toggle="modal" data-target="#modal-default" />--%>
                                        <input id="categoryName" class="form-control required" placeholder="请选择" value="${tbContent.tbContentCategory.name}" readonly="true" data-toggle="modal" data-target="#modal-default" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="title" class="col-sm-2 control-label">标题</label>

                                    <div class="col-sm-10">
                                            <%--<form:input id="title" class="form-control required" placeholder="标题" />--%>
                                        <form:input cssClass="form-control required" path="title" placeholder="标题" />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="subTitle" class="col-sm-2 control-label">子标题</label>

                                    <div class="col-sm-10">
                                        <form:input cssClass="form-control required" path="subTitle" placeholder="子标题" />
                                            <%--<input id="subTitle" class="form-control required" placeholder="子标题" />--%>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="titleDesc" class="col-sm-2 control-label">标题描述</label>

                                    <div class="col-sm-10">
                                        <form:input cssClass="form-control required" path="titleDesc" placeholder="标题描述" />
                                            <%--<input id="titleDesc" class="form-control required" placeholder="标题描述" />--%>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="url" class="col-sm-2 control-label">链接</label>

                                    <div class="col-sm-10">
                                        <form:input cssClass="form-control required" path="url" placeholder="链接" />
                                            <%--<input id="url" class="form-control required" placeholder="链接" />--%>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="pic" class="col-sm-2 control-label">图片1</label>

                                    <div class="col-sm-10">
                                        <form:input cssClass="form-control" path="pic" placeholder="图片1" />
                                        <div id="dropz" class="dropzone"></div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="pic2" class="col-sm-2 control-label">图片2</label>

                                    <div class="col-sm-10">
                                        <form:input cssClass="form-control" path="pic2" placeholder="图片2" />
                                        <div id="dropz2" class="dropzone"></div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-sm-2 control-label">详情</label>
                                    <div class="col-sm-10">
                                        <form:hidden path="content"/>
                                    <%--<form:textarea path="content" cssClass="form-control" rows="3" placeholder="详情"/>--%>
                                        <div id="editor">${tbContent.content}</div>
                                    </div>
                                </div>

                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="button" class="btn btn-default" onclick="history.go(-1)">返回</button>
                                <button id="btnSubmit" type="submit" class="btn btn-info pull-right">提交</button>
                            </div>
                            <!-- /.box-footer -->
                        </form:form>
                    </div>
                    <!-- /.box -->
                </div>
            </div>
        </section>
    </div>
    <!-- /.content-wrapper -->
    <jsp:include page="../includes/copyright.jsp" />
</div>

<!-- /.content-wrapper -->
<jsp:include page="../includes/footer.jsp"/>
<script src="/static/assets/plugins/jquery-ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="/static/assets/plugins/dropzone/min/dropzone.min.js"></script>
<script src="/static/assets/plugins/wangEidtor/wangEditor.min.js"></script>
<sys:modal title="请选择" message="<ul id='myTree' class='ztree'></ul>"> </sys:modal>

<script>
    $(function () {
        App.initZTree("/content/category/tree/data",["id"],function (nodes) {
            var node = nodes[0];
            $("#categoryName").val(node.name);
            $("#categoryId").val(node.id);
            $("#modal-default").modal("hide");
        });
        initWangEditor();
    });

    function initWangEditor() {
        var E = window.wangEditor;
        var editor = new E('#editor');
        //配置服务端地址
        editor.customConfig.uploadImgServer = '/upload';
        editor.customConfig.uploadFileName = 'editorFiles';

        editor.create();

        $("#btnSubmit").bind("click",function () {
            var contentHtml = editor.txt.html();
            $("#content").val(contentHtml);
        });
    }
    Dropzone.autoDiscover = false;
    App.initDropzone("#dropz",{
        url: "/upload",
        init: function () {
            this.on("success", function (file, data) {
                $("#pic").val(data.fileName);
            });
        }
    });

    App.initDropzone("#dropz2",{
        url: "/upload",
        init: function () {
            this.on("success", function (file, data) {
                $("#pic2").val(data.fileName);
            });
        }
    });
</script>
</body>
</html>