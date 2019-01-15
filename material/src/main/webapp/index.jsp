<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>物资管理系统</title>
    <link rel="stylesheet" type="text/css" href="/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
    <script type="text/javascript" src="/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/easyui/locale/easyui-lang-zh_CN.js"></script>
</head>
<script type="text/javascript">
    $(function () {
        $("#menuTree").tree({
            url: "/menu/tree",
            loadFilter: function(data){
                if (data.data){
                    return data.data;
                } else {
                    return data;
                }
            },
            onClick:function(node){
                if(node.state=='closed'&&(!$("#menuTree").tree('isLeaf',node.target))){  //状态为关闭而且非叶子节点
                    $(this).tree('expand',node.target);//点击文字展开菜单  
                    if (node.attributes && node.attributes.url) {
                        openTab(node);
                    }
                }else{
                    if($("#menuTree").tree('isLeaf',node.target)){  //状态为打开而且为叶子节点
                        if (node.attributes && node.attributes.url) {
                            openTab(node);
                        }
                    }else{
                        $(this).tree('collapse',node.target);//点击文字关闭菜单  
                    }

                }
            }
        });

        function openTab(node){
            if($("#index_tabs").tabs("exists",node.text)){
                $("#index_tabs").tabs("select",node.text);
            }else{
                var content = '<iframe src="'+node.attributes.url + '" frameborder="0" style="border:0;width:100%;height:100%;"></iframe>';
                $("#index_tabs").tabs("add",{
                    title:node.text,
                    iconCls:node.iconCls,
                    closable:true,
                    content:content
                });
            }
        }

    });


</script>
<body class="easyui-layout" >
    <div data-options="region:'west',title:'菜单',split:true" style="width:150px;">
        <ul id="menuTree" class="easyui-tree"></ul>
    </div>
    <div data-options="region:'center',title:''"  >
        <div id="index_tabs" class="easyui-tabs" style="border:0;width:100%;height:100%;">
    </div>
</body>
</html>