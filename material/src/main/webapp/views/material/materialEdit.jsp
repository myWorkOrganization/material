<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function() {
		parent.$.messager.progress('close');
		$('#form').form({
			url :'/materialmanagement/materialEdit',
			onSubmit : function() {
				parent.$.messager.progress({
					title : '提示',
					text : '数据处理中，请稍后....'
				});
				var isValid = $(this).form('validate');
				if (!isValid) {
					parent.$.messager.progress('close');
				}
				return isValid;
			},
			success : function(result) {
				parent.$.messager.progress('close');
				result = $.parseJSON(result);
				if (result.res) {
                    $.messager.alert('通知', result.desc,'info');
                    //之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
                    modalDialog.openner_dataGrid.datagrid('reload');
                    modalDialog.handler.dialog('close');
                    modalDialog.openner_dataGrid.datagrid('clearChecked');
                } else {
					parent.$.messager.alert('错误', result.desc, 'error');
				}
			}
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;">
		<form id="form" method="post">
			<table class="table table-hover table-condensed" style="">

                <input name="materialId" hidden="true" class="easyui-validatebox" value="${material.materialId}" data-options="required:true"  style="width:200px;">
                <tr>
					<th>名称</th>
					<td>
						<input name="materialName" class="easyui-validatebox" value="${material.materialName}" data-options="required:true"  style="width:200px;">
					</td>
				</tr>
                <tr>
					<th>数量</th>
					<td>
						<input name="materialNums" class="easyui-validatebox" value="${material.materialNums}" data-options="required:true"  style="width:200px;">
					</td>
				</tr>
                <tr>
					<th>生产厂家</th>
					<td>
						<input name="materialManufacturers" class="easyui-validatebox" value="${material.materialManufacturers}" data-options="required:true"  style="width:200px;">
					</td>
				</tr>
                <tr>
					<th>批号</th>
					<td>
						<input name="materialBatchNumber" class="easyui-validatebox" value="${material.materialBatchNumber}" data-options="required:true"  style="width:200px;">
					</td>
				</tr>
				<tr>
					<th>生产日期</th>
					<td>
						<input name="productDate" class="easyui-datebox" value="${material.productDate}" data-options="required:true" label="" labelPosition="top" style="width:200px;">
					</td>
				</tr>
				<tr>
					<th>有效日期</th>
					<td>
						<input name="validdate" class="easyui-datebox" value="${material.validdate}" data-options="required:true" label="" labelPosition="top" style="width:200px;">
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>