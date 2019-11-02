function addCustomerServeServiceFeedBack() {
    $("#fm").form("submit",{
        url:ctx+'/customer_serve/update',
        onSubmit:function (params) {
            params.state=4;
            return $("#fm").form("validate");
        },
        success:function (data) {
            data=JSON.parse(data);
            $.messager.alert("来自crm",data.msg,"info")
            if(data.code==200){
                closeCustomerServeDialog();
                $("#dg").datagrid("load");
            }
        }
    })
}
function closeCustomerServeDialog() {
    $("#dlg").dialog("close");
}

function openFeedBackDlg() {
    var row = $("#dg").datagrid("getSelections");
    if(row.length==0){
        $.messager.alert("来自crm","请选择一条记录","info");
        return ;
    }
    if(row.length>1){
        $.messager.alert("来自crm","不能同时处理多条记录","info");
        return ;
    }
    $("#fm").form("load",row[0]);
    $("#dlg").dialog("open");
}