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
	<form id="tlinks_add_form" method="post">
		<table class="tableForm">
				<tr style="display: none">
				<th>编号</th>
				<td>
					<input name="id" readonly="readonly" style="width: 370px;" />
				</td>
			</tr>
			<tr>
				<th>名称</th>
				<td>
					<input name="sitename" class="easyui-validatebox" style="width: 370px;" />
				</td>
			</tr>
			<tr>
				<th>缩略图</th>
				<td>
					<img alt="" src="" id="picshow" width="200px" width="100px" />
					<input name="imgsrc" id="pic" class="easyui-validatebox" style="display: none" />
					<input type="button" id="picbutton" value="选择">
				</td>
			</tr>
			
			
			<tr>
				<th>路径地址</th>
				<td>
					<input name="siteurl" class="easyui-validatebox" style="width: 370px;" />
				</td>
			</tr>
		</table>
	</form>
</div>