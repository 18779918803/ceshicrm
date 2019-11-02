<html>
<head>
<#include "common.ftl">
<script type="text/javascript" src="${ctx}/js/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/js/user.js"></script>
</head>
<body>
	<table id="dg" class="easyui-datagrid"  pagination=true 
	     rownumbers=true    toolbar="#tb" fit=true url="${ctx}/user/queryUsersByParams">   
    <thead>   
        <tr>
            <th field='cb' checkbox=true >id</th>  
            <th field='id' >编号</th>      
            <th field='userName'>用户名</th>   
            <th field='trueName'>真实名称</th>   
            <th field='email'  >邮箱</th>
            <th field='phone'>手机号</th>
            <th field='roleName'>角色</th>
            <th field='createDate'>创建时间</th>
            <th field='updateDate'>更新时间</th>
        </tr>   
    </thead>   
</table>

<div id="tb">
   <a  href="javascript:openAddUserDialog()" class="easyui-linkbutton" iconCLs="icon-save"  plain=true>添加</a>
   <a   href="javascript:openModifyUserDialog()"  class="easyui-linkbutton"    iconCLs="icon-edit" plain=true>修改</a>
   <a  href="javascript:deleteUser()" class="easyui-linkbutton" iconCLs="icon-remove" plain=true>删除</a>
   <br/>
    用户名:<input name="userName" id="userName" type="text"/>
    真实名称:<input name="trueName" id="trueName" type="text"/>
    联系方式:<input id="phone"  name="phone" type="text" />
    邮箱:<input id="email"  name="email" type="text" />
 <a href="javascript:searchUsers()" class="easyui-linkbutton" iconCls="icon-search" plain=true>查询</a>
</div>
<div id='dlg' class="easyui-dialog"  style="width: 500px;height: 400px" title="添加用户记录" closed=true buttons="#bt">
        <form  id="fm" method="post">
            <br/>
            <br/>
            用户名称:<input name="userName" type="text"/><br/><br/>
            真实名称:<input name="trueName" type="text"/><br/><br/>
            邮箱:<input name="email" type="text"/><br/><br/>
            电话:<input name="phone" type="text"/><br/><br/>
            角色:<input  class="easyui-combobox" name="roleIds"
                       valueField='id'  textField='roleName'
                       url='${ctx}/role/queryAllRoles' panelHeight="auto" multiple=true />  <br/><br/>
            <input name="id" id="id" type="hidden"/>
        </form>
    </div>
<div id="bt">
   <a href="javascript:saveOrUpdateUser()" class="easyui-linkbutton" iconCls="icon-save">保存</a>
   <a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
</body>
</html>