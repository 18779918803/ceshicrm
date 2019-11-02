$(function () {
    $("#dg").datagrid({
        rowStyler:function (index,rowData) {
            if(rowData.state==0){
                return "background-color:yellow";
            }else if(rowData.state==1){
                return "background-color:red";
            }
        }
    });
});
function formatterState(val) {
    switch (val){
        case 0:
            return "暂缓流失！";
        case 1:
            return "确认流失！";
        default:
            return "未定义！"
    }
}
function formatterOp(val,rowData) {
    if(rowData.state==0){
        return " <a href=" + "javascript:openCustomerRepriDetailTab('客户流失暂缓处理"+rowData.id+"'," +rowData.id +")" + ">添加暂缓措施</a>";
    }
    if(rowData.state==1){
        return " <a href=" + "javascript:openCustomerRepriDetailTab('客户流失情况"+rowData.id+"'," +rowData.id +")" + ">查看流失详情</a>";
    }
}
function searchCustomerLosses() {
    $("#dg").datagrid("load",{
        customerName: $("#customerName").val(),
        customerManager :$("#customerManager").combobox("getValue"),
        customerNum:$("#customerNum").val(),
        time:$("#time").datebox("getValue")
    });
}
function openCustomerRepriDetailTab(title,lossId) {
    window.parent.openTab(title,ctx+"/customer_loss/toRepreivePage/"+lossId);
}