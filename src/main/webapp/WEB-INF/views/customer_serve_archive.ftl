<html>
<head>
<#include "common.ftl">
<script type="text/javascript" src="${ctx}/js/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/js/base.js"></script>
</head>
<body>
	<table id="dg" class="easyui-datagrid"  pagination=true 
	     rownumbers=true    toolbar="#tb" fit=true url="${ctx}/customer_serve/queryCustomerServesByParams?state=5">   
    <thead>   
        <tr>
            <th field='cb' checkbox=true >id</th>    
            <th field='serveType'>服务类型</th>   
            <th field='overview'>概要</th>   
            <th field='customer'  >客户名称</th>
            <th field='serviceRequest'>请求内容</th>
            <th field='createPeople'>创建人</th>
            <th field='assigner'>分配人</th>
            <th field='assignTime'>分配时间</th>
            <th field='serviceProcePeople'>处理人</th>
            <th field='serviceProceTime'>处理时间</th>
             <th field='serviceProceResult'>反馈结果</th>
            <th field='myd'>满意度</th>
            <th field='createDate'>创建时间</th>
            <th field='updateDate'>更新时间</th>
        </tr>   
    </thead>   
</table>
</body>
</html>