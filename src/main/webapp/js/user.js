function searchUsers() {
    $("#dg").datagrid("load",{
        userName:$("#userName").val(),
        trueName:$("#trueName").val(),
        phone:$("#phone").val(),
        email:$("#email").val()
    });
}
function openAddUserDialog() {
    $("#dlg").dialog("open").dialog("setTitle","添加用户");
    $("#fm").form("clear");
}
function closeDialog() {
    $("#dlg").dialog("close");
}

function saveOrUpdateUser() {
    var id = $("#id").val();
    var url=ctx+"/user/update";
    if(isEmpty(id)){
        url=ctx+"/user/insert";
    }
    $("#fm").form("submit",{
        url:url,
        onSubmit:function () {
            return $("#fm").form("validate");
        },
        success:function (data) {
            data = JSON.parse(data);
            if(data.code==200){
                $.messager.alert("来自crm",data.msg,"info");
                searchUsers();
                closeDialog();
            }else{
                $.messager.alert("来自crm",data.msg,"error");
            }
        }
    })
}
function openModifyUserDialog() {
   var row =  $("#dg").datagrid("getSelections");
   if(row.length==0){
       $.messager.alert("来自crm","请至少选中一条记录","error");
       return;
   }
    if(row.length>1){
        $.messager.alert("来自crm","一次只能修改一条记录","error");
        return;
    }
    $("#fm").form("load",row[0]);
    $("#dlg").dialog("open").dialog("setTitle","修改用户");
}
function deleteUser () {
    var row =  $("#dg").datagrid("getSelections");
    if(row.length==0){
        $.messager.alert("来自crm","请至少选中一条记录","error");
        return;
    }
    if(row.length>1){
        $.messager.alert("来自crm","不允许同时删除多条记录!","info");
        return;
    }
    $.messager.confirm("来自crm","确定删除选中的记录吗？",function (r) {
        if(r){
           $.ajax({
               type:'post',
               url:ctx+"/user/delete",
               data:'id='+row[0].id,
               dataType:'json',
               success:function (data) {
                   $.messager.alert("来自crm",data.msg,"info");
                   if(data.code==200){
                       closeDialog();
                       searchUsers();
                   }
               }
           })
        }
    })
}