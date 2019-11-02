<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>My JSP 'tree.jsp' starting page</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" type="text/css" href="/crm/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/crm/jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="/crm/jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="/crm/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/crm/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <SCRIPT LANGUAGE="JavaScript">
        function deleteAccount() {
            $.messager.confirm("crm", "确定要偷偷的删掉数据?", function (r) {
                if (r) {
                    $.messager.prompt("crm", "请输入要删除的数据ID", function (msg) {
                        if (msg) {
                            $.ajax({
                                type: "post",
                                url: "/crm/sale_chance/delete",
                                data: "id=" + msg,
                                dataType: "json",
                                success: function (data) {
                                    $.messager.alert("来自crm系统", data.msg, "info");

                                }
                            })
                        } else {
                            $.messager.alert("来自crm", "流失原因不能为空!!", "info");
                        }
                    })
                }
            })
        }
    </SCRIPT>
</head>
<body>
<a href="javascript:deleteAccount()" class="easyui-linkbutton" iconCLs="icon-remove" plain=true>删除数据</a>
</body>
</html>
