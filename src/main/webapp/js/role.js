function searchRoles() {
    $("#dg").datagrid("load",{
        roleName:$("#roleName").val()
    });
}
function openAddRoleDialog() {
    $("#dlg").dialog("open").dialog("setTitle","修改用户记录");
}

function closeDialog() {
    $("#dlg").dialog("close");
}
function saveOrUpdateRole() {
    var id = $("#id").val();
    var url = ctx+"/role/update";
    if(isEmpty(id)){
        url = ctx+"/role/insert";
    }
    $("#fm").form("submit",{
        url:url,
        onSubmit:function () {
            return $("#fm").form("validate");
        },
        success:function (data) {
            data=JSON.parse(data);
            if(data.code==200){
                $.messager.alert("来自crm",data.msg,"info");
                searchRoles();
                closeDialog();
            }else{
                $.messager.alert("来自crm",data.msg,"error");
            }
        }
    })
}
function openModifyRoleDialog() {
    var rows= $("#dg").datagrid("getSelections");
    if(rows.length==0){
        $.messager.alert("来自crm","请选中待更新记录!","info");
        return;
    }
    $("#fm").form("load",rows[0]);
    $("#dlg").dialog("open").dialog("setTitle","修改用户记录");
}
function deleteRole() {
    var rows= $("#dg").datagrid("getSelections");
    if(rows.length==0){
        $.messager.alert("来自crm","请选中待删除记录!","info");
        return;
    }
    $.messager.confirm("来自crm","确定删除选中的"+rows.length+"条记录?",function(r){
        if(r){
            $.ajax({
                type:"post",
                url:ctx+"/role/delete",
                data:"id=" + rows[0].id,
                dataType:"json",
                success:function(data){
                    $.messager.alert("来自crm",data.msg,"info");
                    if(data.code==200){
                        closeDialog();
                        searchRoles();
                    }
                }
            });
        }
    });
}
function openRelatePermissionDlg() {
    var row = $("#dg").datagrid("getSelections");
    if(row.length==0){
        $.messager.alert("来自crm","请选择角色进行授权!","info");
        return;
    }
    if(row.length>1){
        $.messager.alert("来自crm","只能选择一条角色进行授权!","info");
        return;
    }
    $("#rid").val(row[0].id);
    loadModuleData();
    $("#dlg02").dialog("open");
}
var ztreeObj;
function loadModuleData() {
    $.ajax({
        type:"post",
        url:ctx+"/module/queryAllsModuleDtos",
        data:"rid="+$("#rid").val(),
        dataType:"json",
        success:function(data){
            // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
            var setting = {
                check: {
                    enable: true
                },
                data: {
                    simpleData: {
                        enable: true
                    }
                },
                callback: {
                    onCheck: zTreeOnCheck
                }
            };
            // zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
            var zNodes =data;
            ztreeObj= $.fn.zTree.init($("#treeDemo"), setting, zNodes);
        }
    });
}
function zTreeOnCheck() {
    var znodes=ztreeObj.getCheckedNodes(true);
    var moduleIds="moduleIds=";
    if(znodes.length>0){
        for(var i=0;i<znodes.length;i++){
            if(i<znodes.length-1){
                moduleIds=moduleIds+znodes[i].id+"&moduleIds=";
            }else{
                moduleIds=moduleIds+znodes[i].id;
            }
        }
    }
    console.log(moduleIds);
    $("#moduleIds").val(moduleIds);
}
function closeDialog02() {
    $("#dlg02").dialog("close");
}
function addPermission() {
    /**
     * 1.角色id
     * 2.模块id
     */
    $.ajax({
        type:'post',
        url:ctx+"/role/addPermission",
        data:"rid="+$("#rid").val()+"&"+$("#moduleIds").val(),
        dataType:'json',
        success:function (data) {
            if(data.code==200){
                $.messager.alert("来自crm",data.msg,"info");
                $("#moduleIds").val("");
                $("#rid").val("");
                closeDialog02();
            }else{
                $.messager.alert("来自crm",data.msg,"info");
            }
        }
    })
}