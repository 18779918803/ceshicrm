<html>
<head>
<#include "common.ftl">
    <script type="text/javascript" src="${ctx}/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="${ctx}/js/common.js"></script>
    <script type="text/javascript" src="${ctx}/js/customer.loss.js"></script>
</head>
<body>
<table id="dg" class="easyui-datagrid" pagination=true
       rownumbers=true toolbar="#tb" singeSelect="true" fit=true url="${ctx}/customer_loss/queryCustomerLossesByParams">
    <thead>
    <tr>
        <th field='id'>id</th>
        <th field='cusName'>客户名称</th>
        <th field='cusManager'>客户经理</th>
        <th field='cusNo'>客户编号</th>
        <th field='lastOrderTime'>上次下单时间</th>
        <th field='confirmLossTime'>确认流失时间</th>
        <th field='state' formatter="formatterState">流失状态</th>
        <th field='lossReason'>流失原因</th>
        <th field='createDate'>创建时间</th>
        <th field='updateDate'>更新时间</th>
        <th field='op' formatter="formatterOp">操作</th>
    </tr>
    </thead>
</table>


<div id="tb">
    客户名称:<input name="customerName" id="customerName" type="text"/>
    客户编号:<input name="customerNum" id="customerNum" type="text"/>
    客户经理:<input class="easyui-combobox" id="customerManager"
                name="customerManager"
                data-options="panelHeight:'auto',editable:false,valueField:'trueName',textField:'trueName',url:'${ctx}/user/queryAllCustomerManager'"/>
    创建时间:
    <input id="time" type="text" class="easyui-datebox"></input>
    <a href="javascript:searchCustomerLosses()" class="easyui-linkbutton" iconCls="icon-search" plain=true>查询</a>
</div>

</body>
</html>