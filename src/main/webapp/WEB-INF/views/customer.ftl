<html>
<head>
<#include "common.ftl">
    <script type="text/javascript" src="${ctx}/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="${ctx}/js/common.js"></script>
    <script type="text/javascript" src="${ctx}/js/base.js"></script>
    <script type="text/javascript" src="${ctx}/js/customer.js"></script>
</head>
<body>
<table id="dg" title="客户信息管理" class="easyui-datagrid" pagination="true"
       rownumbers="true" url="${ctx}/customer/queryCustomersByParams"
       fit="true" toolbar="#tb">
    <thead data-options="frozen:true">
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="id" width="50" align="center" hidden="true">编号</th>
        <th field="khno" width="150" align="center">客户编号</th>
        <th field="name" width="200" align="center">客户名称</th>
        <th field="cusManager" width="100" align="center">客户经理</th>
        <th field="level" width="100" align="center">客户等级</th>
        <th field="phone" width="100" align="center">联系电话</th>
    </tr>
    </thead>
    <thead>
    <tr>
        <th field="area" width="80" align="center">客户地区</th>
        <th field="myd" width="80" align="center">客户满意度</th>
        <th field="xyd" width="80" align="center">客户信用度</th>
        <th field="address" width="200" align="center">客户地址</th>
        <th field="postCode" width="100" align="center">邮政编码</th>
        <th field="fax" width="100" align="center">传真</th>
        <th field="webSite" width="100" align="center">网址</th>
        <th field="yyzzzch" width="100" align="center">营业执照注册号</th>
        <th field="fr" width="100" align="center">法人</th>
        <th field="zczj" width="100" align="center">注册资金(万元)</th>
        <th field="nyye" width="100" align="center">年营业额(万元)</th>
        <th field="khyh" width="100" align="center">开户银行</th>
        <th field="khzh" width="100" align="center">开户帐号</th>
        <th field="dsdjh" width="100" align="center">地税登记号</th>
        <th field="gsdjh" width="100" align="center">国税登记号</th>
    </tr>
    </thead>
</table>

<div id="tb">
    <div>
        <a href="javascript:openCustomerAddDialog()"
           class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a> <a
            href="javascript:openCustomerModifyDialog()"
            class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a> <a
            href="javascript:deleteCustomer()" class="easyui-linkbutton"
            iconCls="icon-remove" plain="true">删除</a>
        <a href="javascript:openCustomerOtherInfo('联系人管理',1)"
           class="easyui-linkbutton" iconCls="icon-lxr" plain="true">联系人管理</a>
        <a href="javascript:openCustomerOtherInfo('交往记录管理',2)"
           class="easyui-linkbutton" iconCls="icon-jwjl" plain="true">交往记录管理
        </a> <a href="javascript:openCustomerOtherInfo('历史订单查看',3)"
                class="easyui-linkbutton" iconCls="icon-jwjl" plain="true">历史订单查看
    </a>
    </div>
    <div>
        &nbsp;客户编号：&nbsp;<input type="text" id="s_khno" size="20"
                                onkeydown="if(event.keyCode==13) searchCustomer()"/>
        &nbsp;客户名称：&nbsp;<input type="text" id="s_name" size="20"
                                onkeydown="if(event.keyCode==13) searchCustomer()"/> <a
            href="javascript:searchCustomer()" class="easyui-linkbutton"
            iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>


<div id="dlg" class="easyui-dialog"
     style="width:750px;height:450px;padding: 10px 20px" closed="true"
     buttons="#dlg-buttons"  title="添加客户信息">
    <form id="fm" method="post">
        <input type="hidden" id="id" name="id"/>
        <table cellspacing="8px">
            <tr>
                <td>客户名称：</td>
                <td><input type="text" id="name" name="name"
                           class="easyui-validatebox" required="true"/>&nbsp;<font
                        color="red">*</font></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>地区</td>
                <td><select class="easyui-combobox" style="width: 154px"
                            id="area" name="area" editable="false" panelHeight="auto">
                    <option value="">请选择地区...</option>
                    <option value="北京">北京</option>
                    <option value="南京">南京</option>
                    <option value="上海">上海</option>
                    <option value="广州">广州</option>
                    <option value="天津">天津</option>
                </select>&nbsp;<font color="red">*</font></td>
            </tr>
            <tr>
                <td>客户经理：</td>
                <td><input class="easyui-combobox" id="cusManager"
                           name="cusManager"
                           data-options="panelHeight:'auto',editable:false,valueField:'trueName',textField:'trueName',url:'${ctx}/user/queryAllCustomerManager'"/>&nbsp;
                    <font color="red">*</font></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>客户等级：</td>
                <td>
                    <input class="easyui-combobox" id="level" name="level"
                           panelHeight='auto' editable=false valueField='dataDicValue'
                           textField='dataDicValue'
                           url="${ctx}/data_dic/queryDataDicValueByDataDicName?dataDicName=客户等级"
                    />&nbsp;<font
                        color="red">*</font>
                </td>
            </tr>
            <tr>
                <td>客户满意度：</td>
                <td><select class="easyui-combobox" style="width: 154px"
                            id="myd" name="myd" editable="false" panelHeight="auto">
                    <option value="">请选择...</option>
                    <option value="☆">☆</option>
                    <option value="☆☆">☆☆</option>
                    <option value="☆☆☆">☆☆☆</option>
                    <option value="☆☆☆☆">☆☆☆☆</option>
                    <option value="☆☆☆☆☆">☆☆☆☆☆</option>
                </select>&nbsp;<font color="red">*</font></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>客户信用度</td>
                <td><select class="easyui-combobox" id="xyd" name="xyd"
                            style="width: 154px" editable="false" panelHeight="auto">
                    <option value="">请选择...</option>
                    <option value="☆">☆</option>
                    <option value="☆☆">☆☆</option>
                    <option value="☆☆☆">☆☆☆</option>
                    <option value="☆☆☆☆">☆☆☆☆</option>
                    <option value="☆☆☆☆☆">☆☆☆☆☆</option>
                </select>&nbsp;<font color="red">*</font></td>
            </tr>
            <tr>
                <td>邮政编码：</td>
                <td><input type="text" id="postCode" name="postCode"
                           class="easyui-validatebox" required="true"/>&nbsp;<font
                        color="red">*</font></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>联系电话：</td>
                <td><input type="text" id="phone" name="phone"
                           class="easyui-validatebox" required="true"/>&nbsp;<font
                        color="red">*</font></td>
            </tr>
            <tr>
                <td>传真：</td>
                <td><input type="text" id="fax" name="fax"
                           class="easyui-validatebox" required="true"/>&nbsp;<font
                        color="red">*</font></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>网址：</td>
                <td><input type="text" id="webSite" name="webSite"
                           class="easyui-validatebox" required="true"/>&nbsp;<font
                        color="red">*</font></td>
            </tr>
            <tr>
                <td>客户地址</td>
                <td colspan="4"><input type="text" id="address" name="address"
                                       style="width: 400px" class="easyui-validatebox" required="true"/>&nbsp;<font
                        color="red">*</font></td>
            </tr>
            <tr>
                <td>营业执照注册号：</td>
                <td><input type="text" id="yyzzzch" name="yyzzzch"/></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>法人：</td>
                <td><input type="text" id="fr" name="fr"
                           class="easyui-validatebox" required="true"/>&nbsp;<font
                        color="red">*</font></td>
            </tr>
            <tr>
                <td>注册资金(万元)：</td>
                <td><input type="text" id="zczj" name="zczj"/></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>年营业额(万元)：</td>
                <td><input type="text" id="nyye" name="nyye"
                           class="easyui-validatebox" required="true"/>&nbsp;<font
                        color="red">*</font></td>
            </tr>
            <tr>
                <td>开户银行：</td>
                <td><input type="text" id="khyh" name="khyh"
                           class="easyui-validatebox" required="true"/>&nbsp;<font
                        color="red">*</font></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>开户帐号：</td>
                <td><input type="text" id="khzh" name="khzh"
                           class="easyui-validatebox" required="true"/>&nbsp;<font
                        color="red">*</font></td>
            </tr>
            <tr>
                <td>地税登记号：</td>
                <td><input type="text" id="dsdjh" name="dsdjh"/></td>
                <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                <td>国税登记号：</td>
                <td><input type="text" id="gsdjh" name="gsdjh"/></td>
            </tr>
        </table>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:saveOrUpdateCustomer()" class="easyui-linkbutton"
       iconCls="icon-ok">保存</a>
    <a href="javascript:closeCustomerDialog()"
                                   class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>

</body>
</html>