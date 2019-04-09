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
<!DOCTYPE html>
<html>
<head>
    <title>我的商城 | 用户管理</title>
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
                用户管理
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
                        <h3 class="box-title">${tbUsers.id == null ? "新增":"编辑"}用户</h3>
                    </div>
                    <!-- /.box-header -->
                    <form:form id="inputForm" cssClass="form-horizontal" action="/user/save" method="post" modelAttribute="tbUser">
                        <form:hidden path="id"/>
                        <div class="box-body">
                            <div class="form-group">
                                <label for="email" class="col-sm-2 control-label">邮箱</label>

                                <div class="col-sm-10">
                                    <form:input cssClass="form-control required email" path="email" placeholder="请输入邮箱地址" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="col-sm-2 control-label">密码</label>

                                <div class="col-sm-10">
                                    <form:password cssClass="form-control required" path="password" placeholder="请输入登录密码" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="username" class="col-sm-2 control-label">用户名</label>

                                <div class="col-sm-10">
                                    <form:input cssClass="form-control required" path="username" placeholder="请输入用户名" />
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="phone" class="col-sm-2 control-label">手机号</label>

                                <div class="col-sm-10">
                                    <form:input cssClass="form-control required mobile" path="phone" placeholder="请输入手机号" />
                                </div>
                            </div>
                        </div>
                        <!-- /.box-body -->
                        <div class="box-footer">
                            <button type="button" class="btn btn-default" onclick="history.go(-1)">返回</button>
                            <button type="submit" class="btn btn-info pull-right">提交</button>
                        </div>
                        <!-- /.box-footer -->
                    </form:form>
                </div>
                <!-- /.box -->
            </div>
        </section>
    </div>
    <!-- /.content-wrapper -->
    <jsp:include page="../includes/copyright.jsp" />

    <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
    <div class="control-sidebar-bg"></div>
</div>
<!-- /.content-wrapper -->
<jsp:include page="../includes/footer.jsp"/>
<script>

</script>
</body>
</html>