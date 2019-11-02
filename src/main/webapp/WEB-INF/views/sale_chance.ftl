<html>
<head>
<#include "common.ftl">
    <script type="text/javascript" src="${ctx}/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="${ctx}/js/common.js"></script>
    <script type="text/javascript" src="${ctx}/js/sale.chance.js"></script>
</head>
<body>
<table id="dg" class="easyui-datagrid"  pagination=true
       rownumbers=true    toolbar="#tb" fit=true url="${ctx}/sale_chance/querySaleChancesByParams">
    <thead>
    <tr>
        <th field='id' checkbox=true >id</th>
        <th field='chanceSource'>机会来源</th>
        <th field='customerName'>客户名称</th>
        <th field='cgjl'  >成功几率(%)</th>
        <th field='overview'>概要</th>
        <th field='linkMan'>联系人</th>
        <th field='linkPhone'>联系电话</th>
        <th field='description'>描述</th>
        <th field='createMan'>创建人</th>
        <th field='state' formatter="formatterState">分配状态</th>
        <th field='createDate'>创建时间</th>
        <th field='updateDate'>更新时间</th>
    </tr>
    </thead>
</table>

<div id="tb">
    <a href="javascript:openAddAccountDialog()" class="easyui-linkbutton" iconCLs="icon-add" plain=true>添加</a>
    <a href="javascript:openModifyAccountDialog()" class="easyui-linkbutton" iconCLs="icon-edit" plain=true>修改</a>
    <#if userPermission?seq_contains('101003')>
    <a href="javascript:deleteAccount()" class="easyui-linkbutton" iconCLs="icon-remove" plain=true>删除</a>
    </#if>
    <br/>
    创建人:<input name="createMan" id="createMan" type="text"/>
    客户名称:<input name="customerName" id="customerName" type="text"/>
    创建时间:<input id="createDate" name="createDate" type="text" class="easyui-datebox"/>
    分配状态:
    <select id="state" class="easyui-combobox" name="state" panelHeight=auto >
        <option value="">全部</option>
        <option value="0">未分配</option>
        <option value="1">已分配</option>
    </select>
    <a href="javascript:searchSaleChances()" class="easyui-linkbutton" iconCls="icon-search" plain=true>查询</a>
</div>

<div id='dlg' class="easyui-dialog"  style="width: 500px;height: 400px" title="添加营销机会记录" closed=true buttons="#bt">
    <form  id="fm" method="post">
        客户名称:<input id="cc"  class="easyui-combobox" name="customerName"
                    valueField='name'  textField='name' url='${ctx}/customer/queryAllCustomers' panelHeight="auto" />  <br/><br/>

        机会来源:<input name="chanceSource" type="text"/><br/><br/>
        成功几率:<input name="cgjl" required=true class="easyui-validatebox" type="text"/><br/><br/>

        概要:<input name="overview" type="text"/><br/>
        联系人:<input name="linkMan" required=true  class="easyui-validatebox" type="text"/><br/>
        联系电话:<input name="linkPhone" required=true class="easyui-validatebox" type="text"/><br/>
        机会描述:<input name="description" type="text"/><br/>
        分配人:<input id="cc"  class="easyui-combobox" name="assignMan"
                   valueField='trueName'  textField='trueName' url='${ctx}/user/queryAllCustomerManager'panelHeight="auto"/><br/><br/>
        <input name="id" id="id" type="hidden"/>
    </form>

</div>

<div id="bt">
    <a href="javascript:saveAccount()" class="easyui-linkbutton" iconCls="icon-save">保存</a>
    <a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>

</body>
</html>