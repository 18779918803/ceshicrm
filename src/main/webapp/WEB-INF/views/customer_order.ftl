<html>
<head>
<#include "common.ftl">
    <script type="text/javascript" src="${ctx}/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="${ctx}/js/common.js"></script>
    <script type="text/javascript" src="${ctx}/js/base.js"></script>
    <script type="text/javascript" src="${ctx}/js/customer.order.js"></script>
</head>


<body style="margin: 15px">

<div id="p" class="easyui-panel" title="客户信息" style="height:250px">
    <table cellspacing="8px">
        <input type="hidden" id="cid" name="cid" value="${customer.id}" />
        <tr>
            <td>客户名称：</td>
            <td><input type="text" id="customerName" name="customerName"
                       readonly="readonly" value="${(customer.name)!}" /></td>
            <td></td>
            <td>客户编号</td>
            <td><input type="text" id="khno" readonly="readonly"
                       value="${(customer.khno)!}" /></td>
        </tr>
    </table>
</div>
<br />

<table id="dg" class="easyui-datagrid" title="订单列表"
       style="width:700px;height:250px" idField="id" pagination="true"
       rownumbers="true" singleSelect="true"
       url="${ctx}/customer_order/queryOrdersByCid?cid=${customer.id}">
    <thead>
    <tr>
        <th field="id" width="50">编号</th>
        <th field="orderNo" width="50">订单编号</th>
        <th field="orderDate" width="100">订单日期</th>
        <th field="address" width="100">地址</th>
        <th field="state" width="100" formatter="formatterState">支付状态</th>
        <th field="op" width="100" formatter="formatterOp">操作</th>
    </tr>
    </thead>
</table>


<!--客户订单详情信息-->
<div id="dlg" class="easyui-dialog"
     style="width:700px;height:450px;padding: 10px 20px" closed="true"
     title="订单详情查看" buttons="#dlg-buttons">
    <div id="p" class="easyui-panel" title="订单信息"
         style="width:700px;height: 150px;padding: 10px">
        <form id="fm">
            <table cellspacing="8px">
                <tr>
                    <td>订单编号：</td>
                    <td><input type="text" id="orderNo" name="orderNo"
                               readonly="readonly" /></td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>订购日期</td>
                    <td><input type="text" id="orderDate" name="orderDate"
                               readonly="readonly" value="" /></td>
                </tr>
                <tr>
                    <td>送货地址：</td>
                    <td><input type="text" id="address" name="address"
                               readonly="readonly" /></td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                    <td>状态</td>
                    <td><input type="text" id="state" name="state"
                               readonly="readonly" /></td>
                </tr>
                <tr>
                    <td>总金额：</td>
                    <td><input type="text" id="sum" name="total"
                               readonly="readonly" /></td>
                </tr>
            </table>
        </form>
    </div>
    <br />
    <table id="dg2" class="easyui-datagrid" title="客户订单详情"
           style="width:700px;height:250px" pagination="true" rownumbers="true"
           url="${ctx}/order_detail/queryOrderDetailsByOrderId"
           fit="true" fitColumns="true">
        <thead>
        <tr>
            <th field="id" width="50">商品编号</th>
            <th field="goodsName" name="goodsName" width="50">商品名称</th>
            <th field="goodsNum" name="goodsNum" width="100">订购数量</th>
            <th field="unit" name="unit" width="100">单位</th>
            <th field="price" name="price" width="100">单价</th>
            <th field="sum" name="sum" width="100">金额</th>
        </tr>
        </thead>
    </table>
</div>
<div id="dlg-buttons">
    <a href="javascript:closeOrderDetailDialog()"
       class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>


</body>