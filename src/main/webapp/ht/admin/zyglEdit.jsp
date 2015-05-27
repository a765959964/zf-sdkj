<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript">
	$('#iconCls').combogrid({
		panelWidth : 250,
		value : 'icon-wrench',
		pagination : true,
		pageSize : 50,
		pageList : [ 50, 100 ],
		idField : 'url',
		textField : 'name',
		url : '${pageContext.request.contextPath}/do/icon/datagrid',
		columns : [ [ {
			field : 'url',
			title : '图标',
			width : 50,
			formatter : function(value, row, index) {
				return formatString('<span class="{0}" style="display: inline-block;height:16px;width:16px"> </span>', value);
			}
		}, {
			field : 'name',
			title : '注释',
			width : 150,
			formatter : function(value, row, index) {
				return value;
			}
		} ] ]
	}).combogrid('grid').datagrid('getPager').pagination({
		showPageList : false,
		showRefresh : false,
		beforePageText : '',
		afterPageText : '/{pages}',
		displayMsg : ''
	});
</script>
<script type="text/javascript">
	$("#zygledit_type").combobox({
		valueField : 'id',
		textField : 'text',
		panelHeight : 'auto',
		required : true,
		data : [ {
			"id" : 1,
			"text" : '菜单'
		}, {
			"id" : 2,
			"text" : '资源'
		} ]
	});
</script>
<div align="center">
	<form id="admin_zyglEdit_editForm" method="post">
		<table class="tableForm">
			<tr>
				<th>编号</th>
				<td>
					<input name="id" readonly="readonly" style="width:370px;" />
				</td>

			</tr>
			<tr>
				<th>资源名称</th>
				<td>
					<input name="text" class="easyui-validatebox" data-options="required:true" style="width:370px;" />
				</td>
			</tr>
			<tr>
				<th>资源地址</th>
				<td>
					<input name="url" style="width:370px;" />
				</td>

			</tr>
			<tr>
				<th>资源排序</th>
				<td>
					<input name="seq" class="easyui-numberspinner" data-options="min:0,max:999,editable:false,required:true,missingMessage:'请选择菜单排序'" value="10" style="width:370px;" />
				</td>
			</tr>
			<tr>
				<th>图标</th>
				<td>
					<select name="iconCls" id="iconCls" style="width: 370px;">
					</select>
				</td>

			</tr>
			<tr>
				<th>上级资源</th>
				<td colspan="3">
					<input id="admin_zyglEdit_pid" name="pid" class="easyui-combotree" data-options="url:'${pageContext.request.contextPath}/do/resource/allTreeNode',parentField : 'pid',lines : true" style="width: 370px;" />
					<span class="icon-cut icon-block" onclick="$('#admin_zyglEdit_pid').combotree('clear');"></span>
				</td>
			</tr>
			<tr>
				<th>类型</th>
				<td colspan="3">
					<input name="type" id="zygledit_type" style="width:370px;" />
				</td>
			</tr>
		</table>
	</form>
</div>