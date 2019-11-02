<html>
<head>
    <#include "common.ftl" >
    <script type="text/javascript" src="${ctx}/jquery-easyui-1.3.3/jquery.edatagrid.js"></script>
    <script type="text/javascript" src="${ctx}/js/cus.dev.plan.detail.js"></script>

</head>
<body style="margin: 15px">
<#--营销机会详情展示-->
<div id="p" class="easyui-panel" title="销售机会信息" style="height:250px">
    <table cellspacing="8px">
        <input type="hidden" id="saleChanceId" name="saleChanceId" value="${saleChance.id!}"/>
        <input type="hidden" id="devResult"  value="${saleChance.devResult!}"/>
        <tr>
            <td>客户名称：</td>
            <td><input type="text" id="customerName" name="customerName" readonly="readonly" value="${(saleChance.customerName)!}" /></td>
            <td>    </td>
            <td>机会来源</td>
            <td><input type="text" id="chanceSource" name="chanceSource" readonly="readonly" value="${(saleChance.chanceSource)!}"/></td>
        </tr>
        <tr>
            <td>联系人：</td>
            <td><input type="text" id="linkMan" name="linkMan" readonly="readonly" value="${(saleChance.linkMan)!}"/></td>
            <td>    </td>
            <td>联系电话：</td>
            <td><input type="text" id="linkPhone" name="linkPhone" readonly="readonly" value="${(saleChance.linkPhone)!}"/></td>
        </tr>
        <tr>
            <td>成功几率(%)：</td>
            <td><input type="text" id="cgjl" name="cgjl" readonly="readonly" value="${(saleChance.cgjl)!}"/></td>
            <td colspan="3">    </td>
        </tr>
        <tr>
            <td>概要：</td>
            <td colspan="4"><input type="text" id="overView" name="overView" style="width: 420px" readonly="readonly" value="${(saleChance.overView)!}"/></td>
        </tr>
        <tr>
            <td>机会描述：</td>
            <td colspan="4">
                <textarea rows="5" cols="50" id="description" name="description" readonly="readonly" value="${(saleChance.description)!}"></textarea>
            </td>
        </tr>
        <tr>
            <td>创建人：</td>
            <td><input type="text" readonly="readonly" id="createMan" name="createMan" value="${(saleChance.createMan)!}" /></td>
            <td>    </td>
            <td>创建时间：</td>
            <td><input type="text" id="createTime" name="createDate" readonly="readonly" value="${saleChance.createDate?string("yyyy-MM-dd HH:mm:ss")}" /></td>
        </tr>
        <tr>
            <td>指派给：</td>
            <td>
                <input type="text" readonly="readonly" id="assignMan" name="assignMan" value="${(saleChance.assignMan)!}"  />
            </td>
            <td>    </td>
            <td>指派时间：</td>
            <td><input type="text" id="assignTime" name="assignTime" readonly="readonly" value="${saleChance.assignTime?string("yyyy-MM-dd HH:mm:ss")}" /></td>
        </tr>
    </table>
</div>
<br/>

<#--开发计划详情记录-->
<table id="dg" title="开发计划项" style="width:700px;height:250px"
       toolbar="#toolbar" idField="id" pagination="true" rownumbers="true" fitColumns="true" singleSelect="true">
    <thead>
    <tr>
        <th field="id" width="50">编号</th>
        <th field="planDate" name="planDateStr" width="50"   editor="{type:'datebox',options:{required:true,editable:false}}">日期</th>
        <th field="planItem" width="100" editor="{type:'validatebox',options:{required:true}}">计划内容</th>
        <th field="exeAffect" width="100" editor="{type:'validatebox',options:{required:true}}">执行效果</th>
    </tr>
    </thead>
</table>

<div id="toolbar">
        <a href="javascript:$('#dg').edatagrid('addRow')" class="easyui-linkbutton" iconCls="icon-add" plain="true" >添加计划</a>
        <a href="javascript:delCusDevPlan()" class="easyui-linkbutton" iconCls="icon-remove" plain="true" >删除计划</a>
        <a href="javascript:saveCusDevPlan()" class="easyui-linkbutton" iconCls="icon-save" plain="true" >保存计划</a>
        <a href="javascript:updateCusDevPlan()" class="easyui-linkbutton" iconCls="icon-save" plain="true" >更新计划</a>
        <a href="javascript:$('#dg').edatagrid('cancelRow')" class="easyui-linkbutton" iconCls="icon-undo" plain="true" >撤销行</a>
        <a href="javascript:updateSaleChanceDevResult(2)" class="easyui-linkbutton" iconCls="icon-kfcg" plain="true" >开发成功</a>
        <a href="javascript:updateSaleChanceDevResult(3)" class="easyui-linkbutton" iconCls="icon-zzkf" plain="true" >终止开发</a>
</div>
</body>
