<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript">
	$(function() {
		$('#admin_yhglEdit_combogrid').combogrid({
			multiple : true,
			nowrap : false,
			url : '${pageContext.request.contextPath}/do/role/combogrid',
			panelWidth : 450,
			panelHeight : 200,
			idField : 'id',
			textField : 'text',
			pagination : true,
			fitColumns : true,
			editable : true,
			rownumbers : true,
			mode : 'remote',
			delay : 500,
			pageSize : 100,
			pageList : [ 100 ],
			columns : [ [ {
				field : 'id',
				title : '编号',
				width : 150,
				hidden : true
			}, {
				field : 'text',
				title : '角色名称',
				width : 150
			}, {
				title : '可访问资源ID',
				field : 'resourceIds',
				width : 300,
				hidden : true
			}, {
				title : '可访问资源',
				field : 'resourceNames',
				width : 300
			} ] ]
		});
	});
</script>
<script>
	KindEditor.ready(function(K) {
		var editor = K.editor({
			cssPath : '${pageContext.request.contextPath}/static/kindeditor-4.1.10/plugins/code/prettify.css',
			uploadJson : '${basePath}static/kindeditor-4.1.10/jsp/upload_json.jsp',
			fileManagerJson : '${basePath}static/kindeditor-4.1.10/jsp/file_manager_json.jsp',
			allowFileManager : true
		});
		K('#picselect').click(function() {
			editor.loadPlugin('image', function() {
				editor.plugin.imageDialog({
					imageUrl : K('#pic').val(),
					clickFn : function(url, title, width, height, border, align) {
						K('#pic').val(url);
						K('#user_picshow').attr('src', url);
						editor.hideDialog();
					}
				});
			});
		});
	});
</script>
<script>
	$(function() {
		$('#deptId').combotree({
			url : '${pageContext.request.contextPath}/do/dept/combotree',
			editable : false,
			textField : 'text',
			parentField : 'pid',
			onSelect : function(rec) {
				var url = '${pageContext.request.contextPath}/do/position/comboboxById?id=' + rec.id;
				$('#positionId').combobox('reload', url);
			}
		});
	})
</script>
<script type="text/javascript">
	$("#yhgledit_sex").combobox({
		valueField : 'id',
		textField : 'text',
		panelHeight : 'auto',
		required : true,
		data : [ {
			"id" : '男',
			"text" : '男'
		}, {
			"id" : '女',
			"text" : '女'
		} ]
	});
</script>
<div align="center">
	<form id="admin_yhglEdit_editForm" method="post">
		<table class="tableForm">
			<tr style="display: none">
				<th>编号</th>
				<td>
					<input name="id" readonly="readonly" style="width: 400px;" />
				</td>
			</tr>


			<tr>
				<th>姓名</th>
				<td>
					<input name="name" class="easyui-validatebox" style="width: 400px;" />
				</td>
			</tr>


			<tr>
				<th>性别</th>
				<td>
					<select id="yhgledit_sex" name="sex" style="width:400px;">
					</select>
				</td>
			</tr>
			<tr>
				<th>头像</th>
				<td>
					<img src="" id="user_picshow" width="100px" />
					<input name="pic" id="pic" class="easyui-validatebox" style="display: none" />
					<br />
					<input type="button" value="选择" id="picselect" />
				</td>
			</tr>
			<tr>
				<th>邮箱</th>
				<td>
					<input name="email" class="easyui-validatebox" style="width: 400px;" data-options="required:true" validType="email" />
				</td>
			</tr>
			<tr>
				<th>手机号</th>
				<td>
					<input name="phone" class="easyui-validatebox" style="width: 400px;" data-options="required:true" validType="phone" />
				</td>
			</tr>

			<tr>
				<th>所属角色</th>
				<td>
					<input id="admin_yhglEdit_combogrid" name="roleIds" style="width: 400px;" />
				</td>
			</tr>
		</table>
	</form>
</div>