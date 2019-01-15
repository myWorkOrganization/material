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
    <script type="text/javascript" src="/scripts/user/login.js"></script>
</head>
<script type="text/javascript">
    var dataGrid;
    $(function() {
        dataGrid = $('#dataGrid').datagrid({
            url : '/materialmanagement/datagrid',
            fit : true,
            fitColumns : true,
            border : false,
            pagination : true,
            idField : 'id',
            pageSize : 10,
            pageList : [ 10, 20, 30, 40, 50 ],
            sortName : 'materialName',
            sortOrder : 'asc',
            checkOnSelect : false,
            selectOnCheck : false,
            nowrap : false,
            frozenColumns : [ [ {
                field : 'id',
                title : '编号',
                width : 150,
                checkbox : true
            }, {
                field : 'materialName',
                title : '名称',
                width : 150
            } ] ],
            columns : [ [ {
                field : 'materialNums',
                title : '数量',
                width : 150
            },{
                field : 'materialManufacturers',
                title : '生产厂家',
                width : 150
            },{
                field : 'materialBatchNumber',
                title : '批号',
                width : 150
            }, {
                field : 'productDate',
                title : '生产日期',
                width : 150

                }, {
                field : 'validdate',
                title : '有效日期',
                width : 150
            }, {
                field : 'hadExpired',
                title : '是否过期',
                width : 150
            }, {
                field : 'createTime',
                title : '创建时间',
                width : 150,
                sortable : true
            }, {
                field : 'lastUpdateTime',
                title : '最后修改时间',
                width : 150
            } ] ],
            rowStyler: function(index,row){
                if (row.hadExpired=="是"){
                    return 'background-color:red;';
                }
            },
            toolbar : [{
                text:'新建',
                iconCls:'icon-add',
                handler:function(){
                    addFun();
                }
            },'-',{
                text:'编辑',
                iconCls:'icon-edit',
                handler:function(){
                    editFun();
                }
            },'-',{
                text:'删除',
                iconCls:'icon-remove',
                handler:function(){
                    batchDeleteFun();
                }
            },'-',{
                text:'查询',
                iconCls:'icon-search',
                handler:function(){
                    search();
                }
            },'-',{
                text:'清空',
                iconCls:'icon-clear',
                handler:function(){
                    debugger;
                    $('#searchForm input').val('');
                    $('#hadExpired').combobox('setValue',"-1");
                    dataGrid.datagrid('load', {});
                }
            }],

            onRowContextMenu : function(e, rowIndex, rowData) {
                e.preventDefault();
                $(this).datagrid('unselectAll').datagrid('uncheckAll');
                $(this).datagrid('selectRow', rowIndex);
                $('#menu').menu('show', {
                    left : e.pageX,
                    top : e.pageY
                });
            }
        });
    });
    //将表单序列化
    function serializeObject(form){
        var o={};
        $.each(form.serializeArray(),function(index){
            if(o[this['name'] ]){
                o[this['name'] ] = o[this['name'] ] + "," + this['value'];
            }else{
                o[this['name'] ]=this['value'];
            }
        })
        return o;
    }

    function search() {
        dataGrid.datagrid('load', serializeObject($('#searchForm')));
        dataGrid.datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
    }

    function addFun() {
        modalDialog({
            title : '添加材料',
            width : 300,
            height: 260,
            href : '/materialmanagement/addPage',
            buttons : [ {
                text : '添加',
                handler : function() {
                    modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                    var f = modalDialog.handler.find('#form');
                    f.submit();
                }
            } ]
        });
    }
    function editFun() {
        var materialId;
        var rows = $('#dataGrid').datagrid('getChecked');

        if(rows!=null&&rows!=undefined&&rows.length==1){
            materialId = rows[0].materialId;
        }else{
            $.messager.alert('提示','请选择一条数据');
            return;
        }
        
        modalDialog({
            title : '编辑材料',
            width : 300,
            height : 260,
            href : '/materialmanagement/editPage?materialId=' + materialId,
            buttons : [ {
                text : '编辑',
                handler : function() {
                    modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                    var f = modalDialog.handler.find('#form');
                    f.submit();
                }
            } ]
        });
    }

    function batchDeleteFun() {
        var rows = dataGrid.datagrid('getChecked');
        var ids = [];
        if (rows.length > 0) {
            parent.$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
                if (r) {
                    parent.$.messager.progress({
                        title : '提示',
                        text : '数据处理中，请稍后....'
                    });
                    for ( var i = 0; i < rows.length; i++) {
                            ids.push(rows[i].materialId);
                    }
                    $.ajax({
                        type: "POST",
                        url:"/materialmanagement/materialDelete",
                        contentType: "application/json",
                        data:JSON.stringify({materialIds:ids}),
                        success:function (result) {
                            if (result.res.success) {
                                parent.$.messager.alert('提示', result.desc, 'info');
                                modalDialog.openner_dataGrid = dataGrid;
                                dataGrid.datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
                            }else{
                                parent.$.messager.alert('提示', result.desc, 'info');
                            }
                            parent.$.messager.progress('close');
                        }
                    });
                }
            });
        } else {
            parent.$.messager.show({
                title : '提示',
                msg : '请勾选要删除的记录！'
            });
        }
    }
    /**
     * @author 孙宇
     *
     * @requires jQuery,EasyUI
     *
     * 创建一个模式化的dialog
     *
     * @returns $.modalDialog.handler 这个handler代表弹出的dialog句柄
     *
     * @returns $.modalDialog.xxx 这个xxx是可以自己定义名称，主要用在弹窗关闭时，刷新某些对象的操作，可以将xxx这个对象预定义好
     */
    modalDialog = function(options) {
        if (modalDialog.handler == undefined) {// 避免重复弹出
            var opts = $.extend({
                title : '',
                width : 840,
                height : 680,
                modal : true,
                onClose : function() {
                    modalDialog.handler = undefined;
                    $(this).dialog('destroy');
                },
                onOpen : function() {
                    parent.$.messager.progress({
                        title : '提示',
                        text : '数据处理中，请稍后....'
                    });
                }
            }, options);
            opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
            return modalDialog.handler = $('<div/>').dialog(opts);
        }
    };
</script>
<body>
<div class="easyui-layout" data-options="fit : true,border : false">
    <div data-options="region:'north',title:'查询条件',border:false" style="height: 160px; overflow: hidden;">
        <form id="searchForm">
            <table class="table table-hover table-condensed" style="">
                <tr>
                    <th>材料名称</th>
                    <td>
                        <input name="materialName" class="easyui-validatebox" style="width:200px;">
                    </td>
                </tr>
                <tr>
                    <th>生产日期</th>
                    <td>
                        <input name="productDateBegin" class="easyui-datebox" label="" labelPosition="top" style="width:100px;">
                        至
                        <input name="productDateEnd" class="easyui-datebox" label="" labelPosition="top" style="width:100px;">
                    </td>
                </tr>
                <tr>
                    <th>有效日期</th>
                    <td>
                        <input name="validdateBegin" class="easyui-datebox" label="" labelPosition="top" style="width:100px;">
                        至
                        <input name="validdateEnd" class="easyui-datebox" label="" labelPosition="top" style="width:100px;">
                    </td>
                </tr>
                <tr>
                    <th>是否过期</th>
                    <td>
                        <select id="hadExpired" class="easyui-combobox" panelHeight="80px;" name="hadExpired">
                            <option value="-1">请选择</option>
                            <option value="1">是</option>
                            <option value="0">否</option>
                        </select>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:false">
        <table id="dataGrid"></table>
    </div>
</div>
</body>
</html>