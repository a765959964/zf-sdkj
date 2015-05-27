<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript">
	$(function() {
		$('#lmgl_treegrid').treegrid({
			url : '${pageContext.request.contextPath}/do/column/treegrid',
			idField : 'id',
			treeField : 'cname',
			parentField : 'pid',
			fit : true,
			fitColumns : true,
			border : false,
			frozenColumns : [ [] ],
			columns : [ [ {
				title : '编号',
				field : 'id',
				width : 150,
				sortable : true,
				checkbox : true
			}, {
				title : '栏目名',
				field : 'cname',
				width : 350,
				sortable : true
			}, {
				title : '提示',
				field : 'tip',
				width : 150
			}, {
				title : '是否为菜单',
				field : 'isMenu',
				width : 150,
				sortable : true,
				formatter : function(value, row, index) {
					if (value == "0") {
						return "否"
					} else {
						return "是";
					}
				},
			}, {
				title : '缩略图',
				field : 'pic',
				width : 150,
				formatter : function(value, row, index) {
					if (value == null || value == "") {
						return ''
					} else {
						return formatString('<img alt="" src="{0}"  width="100px"/>', value);
					}
				}
			}, {
				title : '类型',
				field : 'type',
				width : 150,
				formatter : function(value, row, index) {
					if (value == "1") {
						return "列表";
					} else {
						return "详情";
					}
				}
			}, {
				title : '父栏目ID',
				field : 'pid',
				width : 150,
				hidden : true
			}, {
				title : '父栏目名',
				field : 'pname',
				width : 150,
			},{
				title : '自定义排序',
				field : 'sort',
				width : 150,
				sortable : true	
			},{
				field : 'action',
				title : '动作',
				width : 100,
				formatter : function(value, row, index) {
					if (row.id == '0') {
						return "";
					} else {
						var str = '<span onclick="lmgl_editFun(\'{0}\');" class="icon-edit icon-block"></span>&nbsp;<span onclick="lmgl_deleteFun(\'{1}\');" class="icon-no icon-block"/>';
						return formatString(str, row.id, row.id);
					}
				}
			} ] ],
			toolbar : [ {
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					lmgl_add_fun();
				}
			}, '-', {
				text : '展开',
				iconCls : 'icon-redo',
				handler : function() {
					var node = $('#lmgl_treegrid').treegrid('getSelected');
					if (node) {
						$('#lmgl_treegrid').treegrid('expandAll', node.id);
					} else {
						$('#lmgl_treegrid').treegrid('expandAll');
					}
				}
			}, '-', {
				text : '折叠',
				iconCls : 'icon-undo',
				handler : function() {
					var node = $('#lmgl_treegrid').treegrid('getSelected');
					if (node) {
						$('#lmgl_treegrid').treegrid('collapseAll', node.id);
					} else {
						$('#lmgl_treegrid').treegrid('collapseAll');
					}
				}
			}, '-', {
				text : '刷新',
				iconCls : 'icon-reload',
				handler : function() {
					$('#lmgl_treegrid').treegrid('reload');
				}
			} ],
			onContextMenu : function(e, row) {
				e.preventDefault();
				$(this).treegrid('unselectAll');
				$(this).treegrid('select', row.id);
				$('#lmgl_menu').menu('show', {
					left : e.pageX,
					top : e.pageY
				});
			}
		});
	});

	function lmgl_add_fun() {
		$('<div/>').dialog({
			href : '${pageContext.request.contextPath}/ht/column/lmglAdd.jsp',
			width : 500,
			height : 350,
			modal : true,
			title : '资源添加',
			buttons : [ {
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					var d = $(this).closest('.window-body');
					$('#lmglAddForm').form('submit', {
						url : '${pageContext.request.contextPath}/do/column/add',
						success : function(result) {
							try {
								var r = $.parseJSON(result);
								if (r.success) {
									console.info(r.obj);
									$('#lmgl_treegrid').treegrid('append', {
										parent : r.obj.pid,
										data : [ r.obj ]
									});
									d.dialog('destroy');
								}
								$.messager.show({
									title : '提示',
									msg : r.msg
								});
							} catch (e) {
								$.messager.alert('提示', result);
							}
							$('#lmgl_treegrid').treegrid('reload');
						}
					});
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			}
		});
	}
	function lmgl_editFun(id) {
		if (id != undefined) {
			$('#lmgl_treegrid').treegrid('select', id);
		}
		var node = $('#lmgl_treegrid').treegrid('getSelected');
		$('<div/>').dialog({
			href : '${pageContext.request.contextPath}/ht/column/lmglEdit.jsp',
			width : 500,
			height : 350,
			modal : true,
			title : '资源编辑',
			buttons : [ {
				text : '编辑',
				iconCls : 'icon-edit',
				handler : function() {
					var d = $(this).closest('.window-body');
					$('#column_edit_form').form('submit', {
						url : '${pageContext.request.contextPath}/do/column/edit',
						success : function(result) {
							try {
								var r = $.parseJSON(result);
								if (r.success) {
									$('#lmgl_treegrid').treegrid('reload');
									d.dialog('destroy');
								}
								$.messager.show({
									title : '提示',
									msg : r.msg
								});
							} catch (e) {
								$.messager.alert('提示', result);
							}
							$('#lmgl_treegrid').treegrid('reload');
						}
					});
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				$('#column_edit_form').form('load', node);
			}
		});
	}
	function lmgl_deleteFun(id) {
		if (id != undefined) {
			$('#lmgl_treegrid').treegrid('select', id);
		}
		var node = $('#lmgl_treegrid').treegrid('getSelected');
		if (node) {
			$.messager.confirm('询问', '您确定要删除【' + node.cname + '】？', function(b) {
				if (b) {
					$.ajax({
						url : '${pageContext.request.contextPath}/do/column/remove',
						data : {
							id : node.id
						},
						cache : false,
						dataType : 'JSON',
						success : function(r) {
							console.info(r);
							if (r.success) {
								$('#lmgl_treegrid').treegrid('reload');
							}
							$.messager.show({
								msg : r.msg,
								title : '提示'
							});
						}
					});
				}
			});
		}
	}
</script>
<table id="lmgl_treegrid"></table>
<div id="lmgl_menu" class="easyui-menu" style="width:120px;display: none;">
	<div onclick="lmgl_add_fun();" data-options="iconCls:'icon-add'">增加</div>
	<div onclick="lmgl_deleteFun();" data-options="iconCls:'icon-remove'">删除</div>
	<div onclick="lmgl_editFun();" data-options="iconCls:'icon-edit'">编辑</div>
</div>