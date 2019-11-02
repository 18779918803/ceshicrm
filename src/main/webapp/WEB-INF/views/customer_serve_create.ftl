<html>
<head>
<#include "common.ftl">
<script type="text/javascript" src="${ctx}/js/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/js/customer.serve.create.js"></script>
</head>

<body>
   <div id="p" class="easyui-panel" title="客服服务创建"
		style="width:700px;height: 350px;padding: 10px">
		<form id="fm" method="post">
			<table cellspacing="15px">
				<tr>
					<td>服务类型：</td>
					<td><input class="easyui-combobox" id="serveType"
						name="serveType"
						data-options="panelHeight:'auto',editable:false,valueField:'dataDicValue',textField:'dataDicValue',url:'${ctx}/data_dic/queryDataDicValueByDataDicName?dataDicName=服务类型'" />&nbsp;<font
						color="red">*</font></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td>客户：</td>
					<td><input type="text" id="customer" name="customer"
						class="easyui-validatebox" required="true" />&nbsp;<font
						color="red">*</font></td>
				</tr>
				<tr>
					<td>概要：</td>
					<td colspan="4"><input type="text" id="overview"
						name="overview" style="width: 419px" class="easyui-validatebox"
						required="true" />&nbsp;<font color="red">*</font></td>
				</tr>
				<tr>
					<td>服务请求：</td>
					<td colspan="4"><textarea id="serviceRequest"
							name="serviceRequest" rows="5" cols="49"
							class="easyui-validatebox" required="true"></textarea>&nbsp;<font
						color="red">*</font></td>
				</tr>
				<tr>
					<td colspan="4"></td>
					<td><a href="javascript:saveCustomerService()"
						class="easyui-linkbutton" iconCls="icon-ok">保存</a>&nbsp;&nbsp; <a
						href="javascript:resetValue()" class="easyui-linkbutton"
						iconCls="icon-reset">重置</a></td>
				</tr>
			</table>
		</form>
	</div>
</body>

</html>
