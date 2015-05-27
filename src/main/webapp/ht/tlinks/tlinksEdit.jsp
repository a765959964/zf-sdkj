<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<script>
	KindEditor.ready(function(K) {
		var editor = K.editor({
			cssPath : '${basePath}static/kindeditor-4.1.10/plugins/code/prettify.css',
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
						K('#article_picshow').attr('src', url);
						editor.hideDialog();
					}
				});
			});
		});

		
	});
</script>


<div align="center">
	<form id="tlinks_edit_form" method="post">
		<table class="tableForm">
			<tr style="display: none">
				<th>编号</th>
				<td>
					<input name="id" readonly="readonly" value="${tlinks.id }" style="width: 370px;" />
				</td>
			</tr>
			<tr>
				<th>名称</th>
				<td>
					<input name="sitename" value="${tlinks.sitename }" class="easyui-validatebox" style="width: 370px;" />
				</td>
			</tr>
			<tr>
				<th>缩略图</th>
				<td>
					<img alt="" src="${tlinks.imgsrc}" id="article_picshow" width="100px" />
					<input name="imgsrc" id="pic" class="easyui-validatebox"
						style="display: none" value="${tlinks.imgsrc}" />
					<br />
					<input type="button" value="选择" id="picselect" />
				</td>
			</tr>
			<tr>
				<th>URL地址</th>
				<td>
					<input name="siteurl" value="${tlinks.siteurl }" class="easyui-validatebox" style="width: 370px;" />
				</td>
			</tr>
			
		</table>
	</form>
</div>