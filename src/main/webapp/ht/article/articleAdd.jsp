<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
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

		K('#bigpicselect').click(function() {
			editor.loadPlugin('image', function() {
				editor.plugin.imageDialog({
					imageUrl : K('#bigpic').val(),
					clickFn : function(url, title, width, height, border, align) {
						K('#bigpic').val(url);
						K('#article_bigpicshow').attr('src', url);
						editor.hideDialog();
					}
				});
			});
		});
	});
</script>

<script>
	KindEditor.ready(function(K) {
		var editor1 = K.create('textarea[name="content"]', {
			width : '200px',
			height : '200px',
			cssPath : '${basePath}static/kindeditor-4.1.10/plugins/code/prettify.css',
			uploadJson : '${basePath}static/kindeditor-4.1.10/jsp/upload_json.jsp',
			fileManagerJson : '${basePath}static/kindeditor-4.1.10/jsp/file_manager_json.jsp',
			htmlTags : {
				font : [ 'color', 'size', 'face', '.background-color' ],
				span : [ '.color', '.background-color', '.font-size', '.font-family', '.background', '.font-weight', '.font-style', '.text-decoration', '.vertical-align', '.line-height' ],
				div : [ 'align', '.border', '.margin', '.padding', '.text-align', '.color', '.background-color', '.font-size', '.font-family', '.font-weight', '.background', '.font-style', '.text-decoration', '.vertical-align', '.margin-left' ,'class'],
				table : [ 'border', 'cellspacing', 'cellpadding', 'width', 'height', 'align', 'bordercolor', '.padding', '.margin', '.border', 'bgcolor', '.text-align', '.color', '.background-color', '.font-size', '.font-family', '.font-weight', '.font-style', '.text-decoration', '.background', '.width', '.height', '.border-collapse' ],
				'td,th' : [ 'align', 'valign', 'width', 'height', 'colspan', 'rowspan', 'bgcolor', '.text-align', '.color', '.background-color', '.font-size', '.font-family', '.font-weight', '.font-style', '.text-decoration', '.vertical-align', '.background', '.border' ],
				a : [ 'href', 'target', 'name','id' ],
				embed : [ 'src', 'width', 'height', 'type', 'loop', 'autostart', 'quality', '.width', '.height', 'align', 'allowscriptaccess' ],
				img : [ 'src', 'width', 'height', 'border', 'alt', 'title', 'align', '.width', '.height', '.border' ],
				'p,ol,ul,li,blockquote,h1,h2,h3,h4,h5,h6' : [ 'align', '.text-align', '.color', '.background-color', '.font-size', '.font-family', '.background', '.font-weight', '.font-style', '.text-decoration', '.vertical-align', '.text-indent', '.margin-left', '.margin', 'class', 'id' ],
				pre : [ 'class' ],
				hr : [ 'class', '.page-break-after' ],
				'br,tbody,tr,strong,b,sub,sup,em,i,u,strike,s,del' : []
			},
			allowFileManager : true,
			afterCreate : function() {
				this.sync();
			},
			afterChange : function() {
				this.sync();
			}
		});
		prettyPrint();
	});
</script>
<div align="center">
	<form id="articleAddForm" method="post">
		<table class="tableForm">
			<tr style="display: none">
				<th>编号</th>
				<td>
					<input name="articleId" readonly="readonly" style="width: 370px;" />
				</td>
			</tr>
			<tr>
				<th>标题</th>
				<td>
					<input name="title" class="easyui-validatebox"
						style="width: 370px;" />
				</td>
			</tr>

			<tr>
				<th>副标题</th>
				<td>
					<input name="tip" class="easyui-validatebox" style="width: 370px;" />
				</td>
			</tr>
			<tr>
				<th>所属栏目</th>
				<td colspan="3">
					<input name="cid" class="easyui-combotree"
						data-options="url:'${pageContext.request.contextPath}/do/column/allTreeNode',parentField : 'pid',lines : true"
						style="width: 370px;" />
				</td>
			</tr>
			<tr>
				<th>缩略图</th>
				<td>
					<img alt="" src="" id="article_picshow" width="100px" />
					<input name="pic" id="pic" class="easyui-validatebox"
						style="display: none" />
					<br />
					<input type="button" value="选择" id="picselect" />
				</td>
			</tr>
			<tr>
				<th>大图</th>
				<td>
					<img alt="" src="" id="article_bigpicshow" width="100px" />
					<input name="bigpic" id="bigpic" class="easyui-validatebox"
						style="display: none" />
					<br />
					<input type="button" value="选择" id="bigpicselect" />
				</td>
			</tr>
			<tr>
				<th>自定义排序</th>
				<td>
					<input name="sort" style="width: 370px;"
						class="easyui-numberspinner"
						data-options="min:0,max:999,editable:false,required:true,missingMessage:'请选择排序'"
						value="5" style="width: 155px;" />
				</td>
			</tr>
			<tr>
				<th>内容</th>
			</tr>
			<tr>
				<td colspan="2">
					<textarea name="content" style="width: 500px"></textarea>
				</td>
			</tr>

		</table>
	</form>
</div>