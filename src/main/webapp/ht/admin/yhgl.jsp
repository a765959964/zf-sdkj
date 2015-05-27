<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript">
	$(function() {

		$('#admin_yhgl_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/do/user/datagrid',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'id',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'name',
			sortOrder : 'asc',
			checkOnSelect : false,
			selectOnCheck : false,
			nowrap : true,
			frozenColumns : [ [ {
				field : 'id',
				title : '编号',
				width : 150,
				checkbox : true
			} ] ],
			columns : [ [

			{
				title : '姓名',
				field : 'name',
				width : 150,
				formatter : function(value, row, index) {
					return value;
				}
			}, {
				title : '性别',
				field : 'sex',
				width : 150,
				formatter : function(value, row, index) {
					return value;
				}
			}, {
				title : '头像',
				field : 'pic',
				width : 150,
				formatter : function(value, row, index) {
					return value;
				}
			}, {
				title : '邮箱',
				field : 'email',
				width : 150,
				formatter : function(value, row, index) {
					return value;
				}
			}, {
				title : '手机号',
				field : 'phone',
				width : 150,
				formatter : function(value, row, index) {
					return value;
				}
			}, {
				title : '创建时间',
				field : 'createtime',
				width : 150,
				formatter : function(value, row, index) {
					return new Date(value).format("yyyy-MM-dd");
				}
			}, {
				field : 'action',
				title : '动作',
				width : 100,
				formatter : function(value, row, index) {
					if (row.id == '0') {
						return '系统管理员';
					} else {
						var str = '<span onclick="admin_yhgl_editFun(\'{0}\');" class="icon-edit icon-block"></span>&nbsp;<sapn onclick="admin_yhgl_deleteFun(\'{1}\');" class="icon-no icon-block"/>&nbsp;<span onclick="admin_yhgl_modifyPwdFun(\'{2}\');" class="icon-locked"/>';
						return formatString(str, row.id, row.id, row.id);
					}
				}
			} ] ],
			toolbar : [ {
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					admin_yhgl_appendFun();
				}
			}, '-', {
				text : '批量删除',
				iconCls : 'icon-remove',
				handler : function() {
					admin_yhgl_removeFun();
				}
			}, '-', {
				text : '批量设置角色',
				iconCls : 'icon-edit',
				handler : function() {
					admin_yhgl_modifyRoleFun();
				}
			}, '-' ]
		});

	});

	function admin_yhgl_searchFun() {
		$('#admin_yhgl_datagrid').datagrid('load', serializeObject($('#admin_yhgl_searchForm')));
	}
	function admin_yhgl_cleanFun() {
		$('#admin_yhgl_searchForm input').val('');
		$('#admin_yhgl_datagrid').datagrid('load', {});
	}
	function admin_yhgl_editFun(id) {
		$('#admin_yhgl_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
		$('<div/>').dialog({
			href : '${pageContext.request.contextPath}/ht/admin/yhglEdit.jsp',
			width : 520,
			height : 360,
			modal : true,
			title : '编辑管理员',
			buttons : [ {
				text : '编辑',
				iconCls : 'icon-edit',
				handler : function() {
					var d = $(this).closest('.window-body');
					$('#admin_yhglEdit_editForm').form('submit', {
						url : '${pageContext.request.contextPath}/do/user/edit',
						success : function(result) {
							try {
								var r = $.parseJSON(result);
								if (r.success) {
									$('#admin_yhgl_datagrid').datagrid('updateRow', {
										index : $('#admin_yhgl_datagrid').datagrid('getRowIndex', id),
										row : r.obj
									});
									d.dialog('destroy');
								}
								$.messager.show({
									title : '提示',
									msg : r.msg
								});
								$('#admin_yhgl_datagrid').datagrid('reload');
							} catch (e) {
								$.messager.alert('提示', result);
							}
						}
					});
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				var index = $('#admin_yhgl_datagrid').datagrid('getRowIndex', id);
				var rows = $('#admin_yhgl_datagrid').datagrid('getRows');
				var o = rows[index];
				o.roleIds = stringToList(rows[index].roleIds);
				$('#admin_yhglEdit_editForm').form('load', o);
			}
		});
	}
	function admin_yhgl_appendFun() {
		$('#admin_yhgl_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
		$('<div/>').dialog({
			href : '${pageContext.request.contextPath}/ht/admin/yhglAdd.jsp',
			width : 520,
			height : 360,
			modal : true,
			title : '添加管理员',
			buttons : [ {
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					var d = $(this).closest('.window-body');
					$('#admin_yhglAdd_addForm').form('submit', {
						url : '${pageContext.request.contextPath}/do/user/add',
						success : function(result) {
							try {
								var r = $.parseJSON(result);
								if (r.success) {
									$('#admin_yhgl_datagrid').datagrid('insertRow', {
										index : 0,
										row : r.obj
									});
									d.dialog('destroy');
								}
								$.messager.show({
									title : '提示',
									msg : r.msg
								});
								$('#admin_yhgl_datagrid').datagrid('reload');
							} catch (e) {
								$.messager.alert('提示', result);
							}
						}
					});
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			}
		});
	}
	function admin_yhgl_removeFun() {
		var rows = $('#admin_yhgl_datagrid').datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
				if (r) {
					var currentAdminId = '${sessionInfo.adminId}';/*当前登录管理员的ID*/
					var flag = false;
					for (var i = 0; i < rows.length; i++) {
						if (currentAdminId != rows[i].id) {
							ids.push(rows[i].id);
						} else {
							flag = true;
						}
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/do/user/remove',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(result) {
							if (result.success) {
								$('#admin_yhgl_datagrid').datagrid('load');
								$('#admin_yhgl_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
								$('#admin_yhgl_datagrid').datagrid('reload');
							}
							if (flag) {
								$.messager.show({
									title : '提示',
									msg : '不可以删除自己！'
								});
							} else {
								$.messager.show({
									title : '提示',
									msg : result.msg
								});
							}
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
	function admin_yhgl_deleteFun(id) {
		$('#admin_yhgl_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
		$('#admin_yhgl_datagrid').datagrid('checkRow', $('#admin_yhgl_datagrid').datagrid('getRowIndex', id));
		admin_yhgl_removeFun();
	}
	function admin_yhgl_modifyPwdFun(id) {
		$('<div/>').dialog({
			href : '${pageContext.request.contextPath}/ht/admin/yhglEditPwd.jsp',
			width : 300,
			height : 200,
			modal : true,
			title : '编辑管理员密码',
			buttons : [ {
				text : '编辑',
				iconCls : 'icon-edit',
				handler : function() {
					var d = $(this).closest('.window-body');
					$('#admin_yhglEditPwd_editForm').form('submit', {
						url : '${pageContext.request.contextPath}/do/user/modifyPwd',
						success : function(result) {
							try {
								var r = $.parseJSON(result);
								if (r.success) {
									d.dialog('destroy');
									$('#admin_yhgl_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
								}
								$.messager.show({
									title : '提示',
									msg : r.msg
								});
								$('#admin_yhgl_datagrid').datagrid('reload');
							} catch (e) {
								$.messager.alert('提示', result);
							}
						}
					});
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				$('#admin_yhglEditPwd_editForm').form('load', {
					id : id
				});
			}
		});
	}
	function admin_yhgl_modifyRoleFun() {
		var rows = $('#admin_yhgl_datagrid').datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			for (var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id);
			}
			$('<div/>').dialog({
				href : '${pageContext.request.contextPath}/ht/admin/yhglEditRole.jsp',
				width : 300,
				height : 200,
				modal : true,
				title : '批量编辑管理员角色',
				buttons : [ {
					text : '编辑',
					iconCls : 'icon-edit',
					handler : function() {
						var d = $(this).closest('.window-body');
						$('#admin_yhglEditRole_editForm').form('submit', {
							url : '${pageContext.request.contextPath}/do/user/modifyRole',
							success : function(result) {
								try {
									var r = $.parseJSON(result);
									if (r.success) {
										$('#admin_yhgl_datagrid').datagrid('load');
										$('#admin_yhgl_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
										d.dialog('destroy');
									}
									$.messager.show({
										title : '提示',
										msg : r.msg
									});
								} catch (e) {
									$.messager.alert('提示', result);
								}
							}
						});
					}
				} ],
				onClose : function() {
					$(this).dialog('destroy');
				},
				onLoad : function() {
					$('#admin_yhglEditRole_editForm').form('load', {
						ids : ids
					});
				}
			});
		} else {
			$.messager.show({
				title : '提示',
				msg : '请勾选要编辑的记录！'
			});
		}
	}
</script>
<div class="easyui-layout" data-options="fit : true,border : false">
	<div data-options="region:'north',title:'查询条件',border:false" style="height: 90px;overflow: hidden;" align="center">
		<form id="admin_yhgl_searchForm">
			<table class="tableForm">
				<tr>
					<th style="width: 150px;">检索名称(可模糊查询)</th>
					<td>
						<input name="name" style="width: 315px;" />
					</td>
				</tr>
			</table>
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="admin_yhgl_searchFun();">过滤条件</a>
			<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="admin_yhgl_cleanFun();">清空条件</a>
		</form>
	</div>
	<div data-options="region:'center',border:false">
		<table id="admin_yhgl_datagrid"></table>
	</div>
</div>