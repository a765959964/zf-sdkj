<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	request.setAttribute("basePath", request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/");
%>
<script type="text/javascript">
	$(function() {
		$('#articleDatagrid').datagrid({
			url : '${pageContext.request.contextPath}/do/article/datagrid',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'articleId',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'articleId',
			sortOrder : 'desc',
			checkOnSelect : false,
			selectOnCheck : false,
			nowrap : false,
			frozenColumns : [ [ {
				title : '编号',
				field : 'articleId',
				width : 150,
				sortable : true,
				checkbox : true
			}, {
				title : '标题',
				field : 'title',
				width : 150,
				sortable : true
			} ] ],
			columns : [ [ {
				title : '缩略图',
				field : 'pic',
				width : 150,
				formatter : function(value, row, index) {
					if (value == null || value == "") {
						return "";
					} else {
						return formatString('<img alt="" src="{0}"   height="60px" />', value);
					}
				}
			}, {
				title : '大图',
				field : 'bigpic',
				width : 150,
				formatter : function(value, row, index) {
					if (value == null || value == "") {
						return "";
					} else {
						return formatString('<img alt="" src="{0}"   height="60px" />', value);
					}
				}
			}, {
				title : '正文',
				field : 'content',
				width : 150,
				formatter : function(value, row, index) {
					return formatString('<span class="icon-search" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span><a href="javascript:void(0);" onclick="articleShowFun(\'{0}\');">查看详细</a>', index);
				}
			}, {
				title : '副标题',
				field : 'tip',
				width : 150,
			}, {
				title : '所属分类',
				field : 'cid',
				width : 150,
				hidden : false,
				sortable : true,
				formatter : function(value, row, index) {
					return row.cname;
				}
			}, {
				title : '所属分类名',
				field : 'cname',
				width : 150,
				hidden : true,
			}, {
				title : '创建时间',
				field : 'createTime',
				width : 150,
				formatter : function(value, row, index) {
					return new Date(value).format("yyyy-MM-dd hh:mm:ss");
				}
			}, {
				title : '自定义排序',
				field : 'sort',
				width : 150,
				sortable : true	
			},{
				field : 'action',
				title : '动作',
				width : 100,
				formatter : function(value, row, index) {
					return formatString('<img onclick="articleEditFun(\'{0}\');" src="{1}"/>&nbsp;<img onclick="articleDelsFun(\'{2}\');" src="{3}"/>', row.articleId, '${pageContext.request.contextPath}/ht/static/style/images/extjs_icons/pencil.png', row.articleId, '${pageContext.request.contextPath}/ht/static/style/images/extjs_icons/cancel.png');
				}
			} ] ],
			toolbar : '#toolbar'
		});
	});
	//查看
	function articleShowFun(index) {
		var rows = $('#articleDatagrid').datagrid('getRows');
		var row = rows[index];
		$('<div/>').dialog({
			title : '文章名称[' + row.title + ']',
			href : '${pageContext.request.contextPath}/do/article/showView?id=' + row.articleId,
			modal : true,
			maximizable : true,
			width : 640,
			height : 480,
			onClose : function() {
				$(this).dialog('destroy');
			}
		});

		$('#articleDatagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
	}
	//添加
	function articleAddFun() {
		$('#articleDatagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
		$('<div/>').dialog({
			href : '${pageContext.request.contextPath}/ht/article/articleAdd.jsp',
			width : 600,
			height : 550,
			modal : true,
			title : '添加内容',
			buttons : [ {
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					var d = $(this).closest('.window-body');
					$('#articleAddForm').form({
						url : '${pageContext.request.contextPath}/do/article/add',
						success : function(result) {
							try {
								var r = zj.toJson(result);
								console.info(r);
								if (r.success) {
									$('#articleDatagrid').datagrid('insertRow', {
										index : 0,
										row : r.obj
									});
									d.dialog('destroy');
								}
								$.messager.show({
									title : '提示',
									msg : r.msg
								});
							} catch (e) {
								d.dialog('destroy');
								$.messager.show({
									title : '提示',
									msg : '添加成功'
								});
							}
								$('#articleDatagrid').datagrid('reload');
						}
					});
					$('#articleAddForm').submit();
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			}
		});
	}
	//编辑
	function articleEditFun(id) {
		$('#articleDatagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
		$('<div/>').dialog({
			href : '${pageContext.request.contextPath}/do/article/editView?id=' + id,
			width : 600,
			height : 550,
			modal : true,
			title : '编辑角色',
			buttons : [ {
				text : '编辑',
				iconCls : 'icon-edit',
				handler : function() {
					var d = $(this).closest('.window-body');
					$('#articleEditForm').form('submit', {
						url : '${pageContext.request.contextPath}/do/article/edit',
						success : function(result) {
							try {
								var r = $.parseJSON(result);
								if (r.success) {
									console.info(r.obj);
									$('#articleDatagrid').datagrid('updateRow', {
										index : $('#articleDatagrid').datagrid('getRowIndex', id),
										row : r.obj
									});
									d.dialog('destroy');
								}
								$.messager.show({
									title : '提示',
									msg : r.msg
								});
							} catch (e) {
								d.dialog('destroy');
								$.messager.show({
									title : '提示',
									msg : '修改成功'
								});
							}
								$('#articleDatagrid').datagrid('reload');
						}
					});
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				var index = $('#articleDatagrid').datagrid('getRowIndex', id);
				var rows = $('#articleDatagrid').datagrid('getRows');
				var o = rows[index];
				$('#articleEditForm').form('load', o);
			}
		});
	}

	//删除
	function articleDelFun() {
		var rows = $('#articleDatagrid').datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
				if (r) {
					for (var i = 0; i < rows.length; i++) {
						ids.push(rows[i].articleId);
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/do/article/remove',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(result) {
							if (result.success) {
								$('#articleDatagrid').datagrid('load');
								$('#articleDatagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
							}
							$.messager.show({
								title : '提示',
								msg : result.msg
							});
						}
					});
				}
			});
		} else {
			$.messager.show({
				title : '提示',
				msg : '请勾选要删除的记录！'
			});
		}
	}
	function articleDelsFun(id) {
		$('#articleDatagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
		$('#articleDatagrid').datagrid('checkRow', $('#articleDatagrid').datagrid('getRowIndex', id));
		articleDelFun();
	}
</script>
<table id="articleDatagrid"></table>
<div id="toolbar" style="display: none;">
	<a href="javascript:void(0);" onclick="articleAddFun();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" style="float: left;">增加</a>
	<div class="datagrid-btn-separator"></div>
	<a href="javascript:void(0);" onclick="articleDelFun();" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" style="float: left;">批量删除</a>
	<div class="datagrid-btn-separator"></div>
	<input id="searchbox" class="easyui-searchbox" style="width:150px;" data-options="searcher:function(value,name){$('#articleDatagrid').datagrid('load',{name:value});},prompt:'可模糊查询BUG名称'"></input>
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="$('#articleDatagrid').datagrid('load',{});$('#searchbox').searchbox('setValue','');">清空条件</a>
</div>