function openTab(text, url, iconCls){
    if($("#tabs").tabs("exists",text)){
        $("#tabs").tabs("select",text);
    }else{
        var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='" + url + "'></iframe>";
        $("#tabs").tabs("add",{
            title:text,
            iconCls:iconCls,
            closable:true,
            content:content
        });
    }
}

function logout() {
    $.messager.confirm("来自crm","你确定要退出系统吗？",function (r){
            if(r){
                setTimeout(function () {
                    $.removeCookie("id");
                    $.removeCookie("userName");
                    $.removeCookie("trueName");
                    window.location.href="index";
                },2000)
            }
        });
}
function openPasswordModifyDialog () {
    $("#dlg").dialog("open");
}
function closePasswordModifyDialog () {
    $("#dlg").dialog("close");
}
function modifyPassword() {
    $("#fm").form("submit",{
        url:ctx+"/user/updatePwd",
        onsubmit:function () {
            return $("#fm").form("validate");
        },
        success:function (data) {
            data=JSON.parse(data);
            if(data.code==200){
                $.messager.alert("来自crm系统","修改密码成功","两秒后退出系统","info");
                setTimeout(function () {
                    $.removeCookie("id");
                    $.removeCookie("userName");
                    $.removeCookie("trueName");
                    window.location.href="index";
                },2000)
            }else{
                $.messager.alert("来自Crm",data.msg,"info");
            }
        }
    });
}