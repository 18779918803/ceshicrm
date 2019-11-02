<html>
<head>
<#include "common.ftl">
    <script type="text/javascript" src="${ctx}/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="${ctx}/js/common.js"></script>
    <script type="text/javascript" src="${ctx}/js/module.js"></script>
</head>
<body>
<table id="dg" class="easyui-datagrid" pagination=true
       rownumbers=true toolbar="#tb" fit=true url="${ctx}/module/queryModulesByParams">
    <thead>
    <tr>
        <th field='cb' checkbox=true>id</th>
        <th field='moduleName'>模块名称</th>
        <th field='parentModuleName'>父模块名称</th>
        <th field='moduleStyle'>模块样式</th>
        <th field='optValue'>操作权限值</th>
        <th field='url'>url</th>
        <th field='grade' formatter="formatterGrade">层级</th>
        <th field='createDate'>创建时间</th>
        <th field='updateDate'>更新时间</th>
    </tr>
    </thead>
</table>
<div id="tb">
    <a href="javascript:openAddModuleDialog()" class="easyui-linkbutton" iconCLs="icon-save" plain=true>添加</a>
    <a href="javascript:openModifyModuleDialog()" class="easyui-linkbutton" iconCLs="icon-edit" plain=true>修改</a>
    <a href="javascript:deleteModule()" class="easyui-linkbutton" iconCLs="icon-remove" plain=true>删除</a>
    <br/>
    模块名称:<input id="moduleName" type="text"/>
    操作码值:<input id="optValue" type="text"/>
    父模块名称:<input id="parentModuleName" type="text"></input>
    <a href="javascript:searchModules()" class="easyui-linkbutton" iconCls="icon-search" plain=true>查询</a>
</div>
<div id='dlg' class="easyui-dialog" style="width: 500px;height: 400px" title="添加模块" closed=true buttons="#bt">
    <form id="fm" method="post">
        模块名称:<input id="cc" name="moduleName"/> <br/><br/>

        url:<input name="url" type="text"/><br/><br/>
        模块样式:<input name="cgjl" type="text"/><br/><br/>
        权限值:<input name="optValue" type="text"/><br/><br/>
        排序:<input name="orders" type="text"/><br/><br/>
        层级:<select class="easyui-combobox" id="grade" name="grade"
                   style="width: 154px" editable="false" panelHeight="auto">
        <option value="">请选择...</option>
        <option value="0">根级</option>
        <option value="1">一级</option>
        <option value="2">二级</option>
    </select><br/>
        <br/>
        <div id="parentMenu">
            上级菜单:
            <select class="easyui-combobox" id="parentId" name="parentId"
                    style="width: 154px" editable="false" panelHeight="auto">
            </select>
        </div>
        <input name="id" id="id" type="hidden"/>
    </form>
</div>
<div id="bt">
    <a href="javascript:saveOrUpdateModule()" class="easyui-linkbutton" iconCls="icon-save">保存</a>
    <a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
</body>
</html>