function saveCustomerService() {
    $("#fm").form("submit",{
        url:ctx+'/customer_serve/insert',
        onSubmit:function (params) {
            params.createPeople = $.cookie("trueName");
            return $("#fm").form("validate")
        },
        success:function (data) {
            data = JSON.parse(data);
            $.messager.alert("crm",data.msg,"info")
            if(data.code == 200){
                $("#fm").form("clear");
            }
        }
    })
}

function resetValue() {
    $("#serveType").combobox("clear");
    $("#serviceRequest").val("");
    $("#customer").val("");
    $("#overview").val("");
}