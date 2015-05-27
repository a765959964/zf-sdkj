<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	request.setAttribute("basePath", request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/");
%>
<script type="text/javascript">
	$(function() {
		$('#icon_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/do/icon/datagrid',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'id',
			pageSize : 50,
			pageList : [ 50, 100 ],
			sortName : 'id',
			sortOrder : 'desc',
			checkOnSelect : false,
			selectOnCheck : false,
			nowrap : false,
			frozenColumns : [ [ {
				title : '编号',
				field : 'id',
				width : 150,
				sortable : true,
				checkbox : true
			}, {
				title : '名称',
				field : 'name',
				width : 150,
				sortable : true
			} ] ],
			columns : [ [ {
				title : 'url',
				field : 'url',
				width : 150,
				formatter : function(value, row, index) {
					return formatString('<span class="{0}" style="display: inline-block;height:16px;width:16px"> </span>', value);
				}
			}, {
				field : 'action',
				title : '动作',
				width : 100,
				formatter : function(value, row, index) {
					var str = '<span onclick="icon_edit_fun(\'{0}\');" class="icon-edit icon-block"></span>&nbsp;<span onclick="icon_dels_fun(\'{1}\');" class="icon-no icon-block"/>';
					return formatString(str, row.id, row.id);
				}
			} ] ],
			toolbar : '#icon_toolbar'
		});
	});
	//查看
	function icon_show_fun(index) {
		var rows = $('#icon_datagrid').datagrid('getRows');
		var row = rows[index];
		$('<div/>').dialog({
			title : '图标名称[' + row.name + ']',
			href : '${pageContext.request.contextPath}/do/icon/showView?id=' + row.id,
			modal : true,
			maximizable : true,
			width : 640,
			height : 200,
			onClose : function() {
				$(this).dialog('destroy');
			}
		});

		$('#icon_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
	}
	//添加
	function icon_add_fun() {
		$('#icon_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
		$('<div/>').dialog({
			href : '${pageContext.request.contextPath}/ht/icon/iconAdd.jsp',
			width : 500,
			height : 150,
			modal : true,
			title : '添加图标',
			buttons : [ {
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					var d = $(this).closest('.window-body');
					$('#icon_add_form').form({
						url : '${pageContext.request.contextPath}/do/icon/add',
						success : function(result) {
							try {
								var r = zj.toJson(result);
								console.info(r);
								if (r.success) {
									$('#icon_datagrid').datagrid('insertRow', {
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
								$('#icon_datagrid').datagrid('reload');
							}
						}
					});
					$('#icon_add_form').submit();
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			}
		});
	}
	//编辑
	function icon_edit_fun(id) {
		$('#icon_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
		$('<div/>').dialog({
			href : '${pageContext.request.contextPath}/do/icon/editView?id=' + id,
			width : 500,
			height : 150,
			modal : true,
			title : '编辑角色',
			buttons : [ {
				text : '编辑',
				iconCls : 'icon-edit',
				handler : function() {
					var d = $(this).closest('.window-body');
					$('#icon_edit_form').form('submit', {
						url : '${pageContext.request.contextPath}/do/icon/edit',
						success : function(result) {
							try {
								var r = $.parseJSON(result);
								if (r.success) {
									console.info(r.obj);
									$('#icon_datagrid').datagrid('updateRow', {
										index : $('#icon_datagrid').datagrid('getRowIndex', id),
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
								$('#icon_datagrid').datagrid('reload');
							}
						}
					});
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				var index = $('#icon_datagrid').datagrid('getRowIndex', id);
				var rows = $('#icon_datagrid').datagrid('getRows');
				var o = rows[index];
				$('#icon_edit_form').form('load', o);
			}
		});
	}

	//删除
	function icon_del_fun() {
		var rows = $('#icon_datagrid').datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
				if (r) {
					for (var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/do/icon/remove',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(result) {
							if (result.success) {
								$('#icon_datagrid').datagrid('load');
								$('#icon_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
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
	function icon_dels_fun(id) {
		$('#icon_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
		$('#icon_datagrid').datagrid('checkRow', $('#icon_datagrid').datagrid('getRowIndex', id));
		icon_del_fun();
	}
</script>
<table id="icon_datagrid"></table>
<div id="icon_toolbar" style="display: none;">
	<a href="javascript:void(0);" onclick="icon_add_fun();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" style="float: left;">增加</a>
	<div class="datagrid-btn-separator"></div>
	<a href="javascript:void(0);" onclick="icon_del_fun();" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" style="float: left;">批量删除</a>
	<div class="datagrid-btn-separator"></div>
	<input id="searchbox" class="easyui-searchbox" style="width:150px;" data-options="searcher:function(value,name){$('#icon_datagrid').datagrid('load',{name:value});},prompt:'可模糊查询名称'"></input>
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="$('#icon_datagrid').datagrid('load',{});$('#searchbox').searchbox('setValue','');">清空条件</a>
</div>