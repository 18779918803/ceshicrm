$(function () {
    var devResult =$("#devResult").val();
    if(devResult==2||devResult==3){
        $("#toolbar").remove();
    }
    $("#dg").edatagrid({
        url:ctx+"/cus_dev_plan/queryCusDevPlans?saleChanceId="+ $("#saleChanceId").val(),
        saveUrl:ctx+"/cus_dev_plan/insert?saleChanceId="+$("#saleChanceId").val(),
        updateUrl:ctx+"/cus_dev_plan/update?saleChanceId="+$("#saleChanceId").val()
    });
});

function saveCusDevPlan() {
    $("#dg").edatagrid("saveRow");
    $("#dg").edatagrid("load");
}

function updateCusDevPlan() {
    $("#dg").edatagrid("saveRow");
    $("#dg").edatagrid("load");
}

function delCusDevPlan() {
    var row = $("#dg").datagrid("getSelected");
    if (row == null) {
        $.messager.alert("来自cem", "请至少选中一条记录！", "error");
        return;
    }
    $.messager.confirm("来自crm", "你确定要删除选中的记录吗？", function (r) {
        if (r) {
            $.ajax({
                type: 'post',
                data: "id=" + row.id,
                dataType: 'json',
                url: ctx + "/cus_dev_plan/delete",
                success: function (data) {
                    if (data.code == 200) {
                        $.messager.alert("来自crm", data.msg, "info");
                        $("#dg").edatagrid("load")
                    } else {
                        $.messager.alert("来自crm", data.msg, "error");
                    }
                }
            });
        }
    });
}

function updateSaleChanceDevResult(devResult) {
    $.messager.confirm("来自crm","确定执行此操作吗？",function (r) {
        if(r){
            $.ajax({
                type:'post',
                url:ctx+"/sale_chance/updateSaleChanceDevResult",
                data:"devResult="+ devResult +"&saleChanceId="+$("#saleChanceId").val(),
                dataType:'json',
                success:function (data) {
                    $.messager.alert("来自crm",data.msg,"info");
                    if(data.code==200){
                        $("#toolbar").remove();
                    }
                }
            });
        }
    });
}