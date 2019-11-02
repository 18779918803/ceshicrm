<html>
<head>
    <#include "common.ftl" >
    <script type="text/javascript" src="${ctx}/jquery-easyui-1.3.3/jquery.edatagrid.js"></script>
    <script type="text/javascript" src="${ctx}/js/customer.repri.js"></script>
</head>

<body style="margin: 15px">
<div id="p" class="easyui-panel" title="客户流失信息" style="height:250px">
    <table cellspacing="8px">
        <input type="hidden" id="lossId" name="lossId" value="${customerLoss['id']}"/>
        <input type="hidden" id="state"  value="${customerLoss['state']}"/>
        <tr>
            <td>客户名称：</td>
            <td><input type="text" id="customerName" name="cusName" readonly="readonly" value="${(customerLoss['cusName'])!}" /></td>
            <td>    </td>
            <td>客户编号</td>
            <td><input type="text" id="cusNo" name="cusNo" readonly="readonly" value="${(customerLoss['cusNo'])!}"/></td>
        </tr>
        <tr>
            <td>客户经理：</td>
            <td><input type="text" id="cusManager" name="cusManager" readonly="readonly" value="${(customerLoss['cusManager'])!}"/></td>
            <td>    </td>
            <td>上次下单时间：</td>
            <td><input type="text" id="lastOrderTime" name="lastOrderTime" readonly="readonly" value="${(customerLoss['lastOrderTime'])!}"/></td>
        </tr>
        <tr>
            <td>确认流失时间：</td>
            <td><input type="text" id="confirmLossTime" name="confirmLossTime" readonly="readonly" value="${(customerLoss['confirmLossTime'])!}"/></td>
            <td colspan="3">    </td>
        </tr>
        <tr>
            <td>流失原因：</td>
            <td colspan="4"><input type="text" id="lossReason" name="lossReason" style="width: 420px" readonly="readonly" value="${(customerLoss['lossReason'])!}"/></td>
        </tr>
    </table>
</div>
<br/>

<#--流失暂缓数据列表-->
<table id="dg" title="暂缓流失列表" style="width:700px;height:250px"
       toolbar="#toolbar" idField="id" pagination="true" rownumbers="true" fitColumns="true" singleSelect="true">
    <thead>
    <tr>
        <th field="id" width="50">编号</th>
        <th field="measure" width="100" editor="{type:'validatebox',options:{required:true}}">暂缓措施</th>
    </tr>
    </thead>
</table>


<div id="toolbar">
        <a href="javascript:$('#dg').edatagrid('addRow')" class="easyui-linkbutton" iconCls="icon-add" plain="true" >添加暂缓措施</a>
        <a href="javascript:delCustomerRepri()" class="easyui-linkbutton" iconCls="icon-remove" plain="true" >删除暂缓措施</a>
        <a href="javascript:saveCustomerRepri()" class="easyui-linkbutton" iconCls="icon-save" plain="true" >保存暂缓措施</a>
       
        <a href="javascript:$('#dg').edatagrid('cancelRow')" class="easyui-linkbutton" iconCls="icon-undo" plain="true" >撤销行</a>
        <a href="javascript:updateCustomerLossState()" class="easyui-linkbutton" iconCls="icon-kfcg" plain="true" >确认流失</a>
</div>
</body>
