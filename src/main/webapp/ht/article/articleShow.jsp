<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script>
	KindEditor.ready(function(K) {
		var editor1 = K.create('textarea[name="content"]', {
			height : '400px',
			cssPath : 'static/kindeditor-4.1.10/plugins/code/prettify.css',
			uploadJson : 'static/kindeditor-4.1.10/jsp/upload_json.jsp',
			fileManagerJson : 'static/kindeditor-4.1.10/jsp/file_manager_json.jsp',
			allowFileManager : true,
			afterCreate : function() {
				this.sync();
			}
		});
		prettyPrint();
	});
</script>
<form name="articleShowForm" id="articleShowForm" style="width: 100%;height: 100%">
	<textarea name="content" cols="100" rows="8" style="width:99%;height:98%;">${article.content}</textarea>
</form>