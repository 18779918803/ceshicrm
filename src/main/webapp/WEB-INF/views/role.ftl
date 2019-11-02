<html>
<head>
<#include "common.ftl">
    <link rel="stylesheet" href="${ctx}/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${ctx}/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="${ctx}/js/common.js"></script>
    <script type="text/javascript" src="${ctx}/js/role.js"></script>
    <script type="text/javascript" src="${ctx}/zTree_v3/js/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="${ctx}/zTree_v3/js/jquery.ztree.excheck.js"></script>
</head>
<body>
<table id="dg" class="easyui-datagrid" pagination=true
       rownumbers=true singleSelect=true toolbar="#tb" fit=true url="${ctx}/role/queryRolesByParams">
    <thead>
    <tr>
        <th field='cb' checkbox=true>id</th>
        <th field='id'>编号</th>
        <th field='roleName'>角色名称</th>
        <th field='roleRemark'>角色备注</th>
        <th field='createDate'>创建时间</th>
        <th field='updateDate'>更新时间</th>
    </tr>
    </thead>
</table>

<div id="tb">
    <a href="javascript:openAddRoleDialog()" class="easyui-linkbutton" iconCLs="icon-save" plain=true>添加</a>
    <a href="javascript:openModifyRoleDialog()" class="easyui-linkbutton" iconCLs="icon-edit" plain=true>修改</a>
    <a href="javascript:deleteRole()" class="easyui-linkbutton" iconCLs="icon-remove" plain=true>删除</a>
    <a href="javascript:openRelatePermissionDlg()" class="easyui-linkbutton" iconCLs="icon-remove" plain=true>关联权限</a>
    <br/>
    角色名:<input name="roleName" id="roleName" type="text"/>
    <a href="javascript:searchRoles()" class="easyui-linkbutton" iconCls="icon-search" plain=true>查询</a>
</div>


<div id='dlg' class="easyui-dialog" style="width: 500px;height: 400px" title="添加角色记录" closed=true buttons="#bt">
    <form id="fm" method="post">
        <br/>
        <br/>
        角色名称:<input name="roleName" type="text"/><br/>
        角色备注:<input name="roleRemark" type="text"/><br/><br/>
        <input name="id" id="id" type="hidden"/>
    </form>

</div>


<div id="bt">
    <a href="javascript:saveOrUpdateRole()" class="easyui-linkbutton" iconCls="icon-save">保存</a>
    <a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>

<div id='dlg02' class="easyui-dialog"  style="width: 500px;height: 400px" title="关联权限" closed=true buttons="#bt2">
    <div>
        <ul id="treeDemo" class="ztree"></ul>
    </div>
    <input name="rid" id="rid" type="hidden"/>
    <input name="moduleIds" id="moduleIds" type="hidden"/>
</div>

<div id="bt2">
    <a href="javascript:addPermission()" class="easyui-linkbutton" iconCls="icon-save">授权</a>
    <a href="javascript:closeDialog02()" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>

</body>
</html>