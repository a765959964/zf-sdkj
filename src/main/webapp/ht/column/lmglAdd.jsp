<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script>
	KindEditor.ready(function(K) {
		var editor = K.editor({
			cssPath : '${basePath}static/kindeditor-4.1.10/plugins/code/prettify.css',
			uploadJson : '${basePath}static/kindeditor-4.1.10/jsp/upload_json.jsp',
			fileManagerJson : '${basePath}static/kindeditor-4.1.10/jsp/file_manager_json.jsp',
			allowFileManager : true
		});
		K('#picbutton').click(function() {
			editor.loadPlugin('image', function() {
				editor.plugin.imageDialog({
					imageUrl : K('#pic').val(),
					clickFn : function(url, title, width, height, border, align) {
						K('#pic').val(url);
						K('#picshow').attr('src', url);
						editor.hideDialog();
					}
				});
			});
		});
	});
</script>
<div align="center">
	<form id="lmglAddForm" method="post">
		<table class="tableForm">
			<tr style="display: none;">
				<th>编号</th>
				<td>
					<input name="id" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<th>栏目名</th>
				<td>
					<input name="cname" class="easyui-validatebox" data-options="required:true" style="width: 370px;" />
				</td>
			</tr>
			<tr>
				<th>小提示</th>
				<td colspan="3">
					<input name="tip" class="easyui-validatebox" style="width: 370px;" />
				</td>
			</tr>
			<tr>
				<th>是否为菜单</th>
				<td colspan="3">
					<select name="isMenu" style="width: 370px;">
						<option value="1">是</option>
						<option value="0">否</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>缩略图</th>
				<td>
					<img alt="" src="" id="picshow" width="200px" width="100px" />
					<input name="pic" id="pic" class="easyui-validatebox" style="display: none" />
					<input type="button" id="picbutton" value="选择">
				</td>
			</tr>
			<tr>
				<th>类型</th>
				<td colspan="3">
					<select name="type" style="width: 370px;">
						<option value="1">列表</option>
						<option value="2">详情</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>父栏目</th>
				<td colspan="3">
					<input name="pid" class="easyui-combotree" data-options="url:'${pageContext.request.contextPath}/do/column/allTreeNode',parentField : 'pid',lines : true" style="width: 370px;" />
				</td>
			</tr>
			<tr>
				<th>自定义排序</th>
				<td>
					<input name="sort" style="width: 370px;" class="easyui-numberspinner" data-options="min:0,max:999,editable:false,required:true,missingMessage:'请选择排序'" value="5" style="width: 155px;" />
				</td>
			</tr>
		</table>
	</form>
</div>