<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	request.setAttribute("basePath", request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/");
%>
<script type="text/javascript">
	$(function() {
		$('#tlinks_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/do/tlinks/datagrid',
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
				title : '名字',
				field : 'sitename',
				width : 250,
				sortable : true
			} ] ],
			columns : [ [ {
				title : 'URL',
				field : 'siteurl',
				width : 250,
				sortable : true
			} ,{
				title : '缩略图',
				field : 'imgsrc',
				width : 150,
				formatter : function(value, row, index) {
					if (value == null || value == "") {
						return "";
					} else {
						return formatString('<img alt="" src="{0}"   height="60px" />', value);
					}
				}
			}
			,{
				field : 'action',
				title : '动作',
				width : 100,
				formatter : function(value, row, index) {
					var str = '<span onclick="tlinks_edit_fun(\'{0}\');" class="icon-edit icon-block"></span>&nbsp;<span onclick="tlinks_dels_fun(\'{1}\');" class="icon-no icon-block"/>';
					return formatString(str, row.id, row.id);
				}
			} ] ],
			toolbar : '#tlinks_toolbar'
		});
	});
	//查看
	function tlinks_show_fun(index) {
		var rows = $('#tlinks_datagrid').datagrid('getRows');
		var row = rows[index];
		$('<div/>').dialog({
			title : '图标名称[' + row.name + ']',
			href : '${pageContext.request.contextPath}/do/tlinks/showView?id=' + row.id,
			modal : true,
			maximizable : true,
			width : 640,
			height : 200,
			onClose : function() {
				$(this).dialog('destroy');
			}
		});

		$('#tlinks_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
	}
	//添加
	function tlinks_add_fun() {
		$('#tlinks_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
		$('<div/>').dialog({
			href : '${pageContext.request.contextPath}/ht/tlinks/tlinksAdd.jsp',
			width : 500,
			height : 500,
			modal : true,
			title : '添加联系方式',
			buttons : [ {
				text : '增加',
				tlinksCls : 'tlinks-add',
				handler : function() {
					var d = $(this).closest('.window-body');
					$('#tlinks_add_form').form({
						url : '${pageContext.request.contextPath}/do/tlinks/add',
						success : function(result) {
							try {
								var r = zj.toJson(result);
								console.info(r);
								if (r.success) {
									$('#tlinks_datagrid').datagrid('insertRow', {
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
								$('#tlinks_datagrid').datagrid('reload');
							}
						}
					});
					$('#tlinks_add_form').submit();
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			}
		});
	}
	//编辑
	function tlinks_edit_fun(id) {
		$('#tlinks_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
		$('<div/>').dialog({
			href : '${pageContext.request.contextPath}/do/tlinks/editView?id=' + id,
			width : 500,
			height : 300,
			modal : true,
			title : '修改联系方式',
			buttons : [ {
				text : '编辑',
				tlinksCls : 'tlinks-edit',
				handler : function() {
					var d = $(this).closest('.window-body');
					$('#tlinks_edit_form').form('submit', {
						url : '${pageContext.request.contextPath}/do/tlinks/edit',
						success : function(result) {
							try {
								var r = $.parseJSON(result);
								if (r.success) {
									console.info(r.obj);
									$('#tlinks_datagrid').datagrid('updateRow', {
										index : $('#tlinks_datagrid').datagrid('getRowIndex', id),
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
								$('#tlinks_datagrid').datagrid('reload');
							}
						}
					});
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				var index = $('#tlinks_datagrid').datagrid('getRowIndex', id);
				var rows = $('#tlinks_datagrid').datagrid('getRows');
				var o = rows[index];
				$('#tlinks_edit_form').form('load', o);
			}
		});
	}

	//删除
	function tlinks_del_fun() {
		var rows = $('#tlinks_datagrid').datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
				if (r) {
					for (var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/do/tlinks/remove',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(result) {
							if (result.success) {
								$('#tlinks_datagrid').datagrid('load');
								$('#tlinks_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
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
	function tlinks_dels_fun(id) {
		$('#tlinks_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
		$('#tlinks_datagrid').datagrid('checkRow', $('#tlinks_datagrid').datagrid('getRowIndex', id));
		tlinks_del_fun();
	}
</script>
<table id="tlinks_datagrid"></table>
<div id="tlinks_toolbar" style="display: none;">
	<a href="javascript:void(0);" onclick="tlinks_add_fun();" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" style="float: left;">增加</a>
	<div class="datagrid-btn-separator"></div>
	<a href="javascript:void(0);" onclick="tlinks_del_fun();" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" style="float: left;">批量删除</a>
	<div class="datagrid-btn-separator"></div>
	<input id="searchbox" class="easyui-searchbox" style="width:150px;" data-options="searcher:function(value,name){$('#tlinks_datagrid').datagrid('load',{name:value});},prompt:'可模糊查询名称'"></input>
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="tlinksCls:'tlinks-cancel',plain:true" onclick="$('#tlinks_datagrid').datagrid('load',{});$('#searchbox').searchbox('setValue','');">清空条件</a>
</div>