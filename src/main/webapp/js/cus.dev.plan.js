function formatterDevResult(val) {
    switch (val){
        case 0:
            return "未开发";
        case 1:
            return "开发中";
        case 2:
            return "开发成功";
        case 3:
            return "开发失败";
        default:
            return "未定义";
    }
}
function formatterOp(val,rowData) {
    if(rowData.devResult==0||rowData.devResult==1){//未开发或者开发中
        var href = "javascript:openCusDevPlanDetailTab('客户开发计划项管理_" +rowData.id +"'," + rowData.id+ ")";
        return "<a href = " + href +"> 开发</a>"
    }else if(rowData.devResult==2||rowData.devResult==3){//开发成功或者开发失败
        var href = "javascript:openCusDevPlanDetailTab('客户开发计划项详情_" +rowData.id +"'," + rowData.id+ ")";
        return "<a href = " + href +"> 查看详情</a>"
    }
}
function openCusDevPlanDetailTab(title,id) {
    window.parent.openTab(title,ctx+"/cus_dev_plan/index/?id="+id);
}
$(function () {
    $("#dg").datagrid({
        rowStyler:function (index,rowData) {
            if(rowData.devResult==0||rowData.devResult==1){//0 未开发 1 开发中 黄色
                return "background-color:yellow";
            }else if(rowData.devResult==2){//成功
                return "background-color:green";
            }else if(rowData.devResult==3){//失败
                return "background-color:red";
            }
        }
    });
});