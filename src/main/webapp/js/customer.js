function searchCustomer() {
    $("#dg").datagrid("load",{
        khno:$("#s_khno").val(),
        name:$("#s_name").val()
    });
}
function openCustomerAddDialog() {
    $("#fm").form("clear");
    $("#dlg").dialog("open").dialog("setTitle","添加客户信息");
}
function closeCustomerDialog() {
    $("#dlg").dialog("close")
}
function saveOrUpdateCustomer() {
    var id = $("#id").val();
    var url = ctx+"/customer/update";
    if(isEmpty(id)){
        url=ctx+"/customer/insert";
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
               searchCustomer();
               closeCustomerDialog();
           }else{
               $.messager.alert("来自crm",data.msg,"error");
           }
       }
    });
}

function openCustomerModifyDialog() {
    var rows = $("#dg").datagrid("getSelections");
    if(rows.length==0){
        $.messager.alert("来自crm","请至少选中一条记录","error");
        return;
    }
    if(rows.length>1){
        $.messager.alert("来自crm","只能一次修改一条记录","error");
        return;
    }
    $("#fm").form("load",rows[0]);
    $("#dlg").dialog("open").dialog("setTitle","修改客户信息");
}
function deleteCustomer() {
    var rows = $("#dg").datagrid("getSelections");
    if(rows.length==0){
        $.messager.alert("来自crm","请至少选中一条记录","error");
        return;
    }
    var params = "id=";
    for (var i = 0;i<rows.length;i++){
        if(i<rows.length-1){
            params= params+rows[i].id+"&id=";
        }else {
            params = params + rows[i].id;
        }
    }
    $.messager.confirm("来自crm","确定要删除所选项吗？",function (r) {
        if(r){
            $.ajax({
                type:'post',
                url:ctx+"/customer/delete",
                data:params,
                dataType:'json',
                success:function (data) {
                    if(data.code==200){
                        $.messager.alert("来自crm",data.msg,"info");
                        searchCustomer();
                    }else{
                        $.messager.alert("来自crm",data.msg,"error");
                    }
                }
            })
        }
    })
}

function openCustomerOtherInfo (title,type) {
   var rows=$("#dg").datagrid("getSelections");
   if(rows.length==0){
       $.messager.alert("来自crm","请选中一条记录！","error");
       return;
   }
   if(rows.length>1){
       $.messager.alert("来自crm","最多选中一条记录！","error");
       return;
   }
    window.parent.openTab(title,ctx+"/customer/openCustomerOtherInfo/"+type+"/"+rows[0].id);
}