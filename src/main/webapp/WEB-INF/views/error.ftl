<#include "common.ftl" >
<script>
    $(function () {
        alert("${msg}")
        if("${uri}"=="/main"){
            window.location.href=ctx + "/index";
        }else{
            window.parent.location.href = ctx + "/index";
        }
    });
</script>