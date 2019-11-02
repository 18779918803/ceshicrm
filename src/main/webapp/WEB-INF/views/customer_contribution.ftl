<html>
<head>
<#include "common.ftl">
    <script type="text/javascript" src="${ctx}/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="${ctx}/js/common.js"></script>
    <script type="text/javascript" src="${ctx}/js/customer.contribution.js"></script>
</head>
<body>
<table id="dg" class="easyui-datagrid" pagination=true
       rownumbers=true toolbar="#tb" fit=true url="${ctx}/customer/queryCustomersContribution">
    <thead>
    <tr>
        <th field='name'>客户名称</th>
        <th field='total'>贡献金额</th>
    </tr>
    </thead>
</table>
<div id="tb">
    客户名称:<input name="customerName" id="customerName" type="text"/>
    <a href="javascript:searchCustomerContributions()" class="easyui-linkbutton" iconCls="icon-search" plain=true>查询</a>
</div>
</body>
</html>