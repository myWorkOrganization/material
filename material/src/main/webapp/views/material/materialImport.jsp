<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>物料管理</title>
    <link rel="stylesheet" type="text/css" href="/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
    <script type="text/javascript" src="/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<script type="text/javascript">
    function fileUpload() {
        $("#materialForm").form("submit",{
            url:"/materialmanagement/materialImportFile",
            success:function (data) {
                var result=$.parseJSON(data);
                if(result.res){
                    $.messager.alert("通知",result.desc,"info");
                }else{
                    $.messager.alert("错误",result.desc,"error");
                }

            }
        });
    }
</script>
<body>
<div class="easyui-layout" data-options="fit : true,border : false">
    <div data-options="region:'north',title:'查询条件',border:false" style="height: 160px; overflow: hidden;">
        <form id="materialForm" method="post" enctype="multipart/form-data">
            <table class="table table-hover table-condensed" style="">
                <tr>
                    <th>材料导入：</th>
                    <td>
                        <input class="easyui-filebox" name="materialFile" style="width:300px" buttonText="选择文件">
                    </td>
                    <td>
                        <input type="button" onclick="fileUpload()" value="上传">
                    </td>
                    <td>
                        <p style  ="color: red;">（只能上传.xls或.xlsx文件）</p>
                    </td>
                </tr>
            </table>
        </form>
    </div>
   <%-- <div data-options="region:'center',border:false">
        <table id="dataGrid"></table>
    </div>--%>
</div>
</body>
</html>