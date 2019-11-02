function searchCustomerContributions() {
    $("#dg").datagrid("load",{
        customerName:$("#customerName").val()
    });
}