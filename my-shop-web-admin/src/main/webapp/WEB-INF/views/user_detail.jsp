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
<!DOCTYPE html>
<html>
<head>
    <title>我的商城 | 用户详情</title>
    <jsp:include page="../includes/headr.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="box-body">
    <table id="dataTable-detail" class="table table-hover">
        <tbody>
        <tr>
            <td>邮箱</td>
            <td>${tbUser.email}</td>
        </tr>
        <tr>
            <td>姓名</td>
            <td>${tbUser.username}</td>
        </tr>
        <tr>
            <td>手机</td>
            <td>${tbUser.phone}</td>
        </tr>
        </tbody>
    </table>
</div>
<!-- /.content-wrapper -->
<%--<jsp:include page="../includes/footer.jsp"/>--%>

<!-- jQuery 3 -->
<script src="/static/assets/bower_components/jquery/dist/jquery.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="/static/assets/bower_components/jquery-ui/jquery-ui.min.js"></script>
<!-- jQuery Validation Plugin v1.14.0-->
<script src="/static/assets/plugins/jquery-validation/js/jquery.validate.js"></script>
<script src="/static/assets/plugins/jquery-validation/js/additional-methods.js"></script>
<script src="/static/assets/plugins/jquery-validation/js/localization/messages_zh.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
    $.widget.bridge('uibutton', $.ui.button);
</script>
<!-- Bootstrap 3.3.7 -->
<script src="/static/assets/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Slimscroll -->
<script src="/static/assets/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="/static/assets/bower_components/fastclick/lib/fastclick.js"></script>
<!-- iCheck 1.0.1 -->
<script src="/static/assets/plugins/iCheck/icheck.min.js"></script>
<!-- DataTables -->
<script src="/static/assets/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="/static/assets/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="/static/assets/js/adminlte.min.js"></script>

<script>

</script>
</body>
</html>