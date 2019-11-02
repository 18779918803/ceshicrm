function formatterState(val) {
    if(val==0){
        return "未支付";
    }
    if(val==1){
        return "已支付";
    }
}
function formatterOp() {
    return "<a href=" + "javascript:openOrderDetailById()" +">查看详情</a>";
}

function openOrderDetailById() {
    var rows= $("#dg").datagrid("getSelections");
    $.ajax({
        type:'post',
        url:ctx+"/customer_order/queryOrderInfoById",
        data:'orderId='+rows[0].orderNo,
        dataType:'json',
        success:function (data) {
            if(data.state==1){
                data.state= "已支付";
            }else{
                data.state= "未支付";
            }
            data.orderDate=new Date(data.orderDate).format("yyyy-MM-dd hh:mm:ss");
            $("#fm").form("load",data);
        }
    })
    $("#dg2").datagrid("load",{
        orderId:rows[0].orderNo
    });
    $("#dlg").dialog("open");
}
function closeOrderDetailDialog() {
    $("#dlg").dialog("close");
}