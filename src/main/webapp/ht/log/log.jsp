<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	request.setAttribute("basePath", request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/");
%>
<script type="text/javascript">
	$(function() {
		$('#log_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/do/log/datagrid',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'id',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
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
				checkbox : false
			} ] ],
			columns : [ [ {
				title : '事件',
				field : 'event',
				width : 150,
				formatter : function(value, row, index) {
					return value;
				}
			}, {
				title : '类型',
				field : 'type',
				width : 150,
				formatter : function(value, row, index) {
					return value;
				}
			}, {
				title : '创建时间',
				field : 'createtime',
				width : 150,
				formatter : function(value, row, index) {
					return new Date(value).format("yyyy-MM-dd hh:mm:ss");
				}
			}, {
				field : 'action',
				title : '动作',
				width : 100,
				formatter : function(value, row, index) {
					return formatString('<span class="icon-search" style="display:inline-block;vertical-align:middle;width:16px;height:16px;"></span><a href="javascript:void(0);" onclick="log_show_fun(\'{0}\');">查看详细</a>', index);
				}
			} ] ],
			toolbar : '#toolbar'
		});
	});
	//查看
	function log_show_fun(index) {
		var rows = $('#log_datagrid').datagrid('getRows');
		var row = rows[index];
		$('<div/>').dialog({
			title : '日志时间[' +  new Date(row.createtime).format("yyyy-MM-dd hh:mm:ss")+ ']',
			href : '${pageContext.request.contextPath}/do/log/showView?id=' + row.id,
			modal : true,
			maximizable : true,
			width : 700,
			height : 480,
			onClose : function() {
				$(this).dialog('destroy');
			}
		});

		$('#log_datagrid').datagrid('uncheckAll').datagrid('unselectAll').datagrid('clearSelections');
	}
</script>
<table id="log_datagrid"></table>
<div id="toolbar" style="display: none;">
	<input id="searchbox" class="easyui-searchbox" style="width:150px;" data-options="searcher:function(value,name){$('#log_datagrid').datagrid('load',{name:value});},prompt:'可模糊查询名称'"></input>
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="$('#log_datagrid').datagrid('load',{});$('#searchbox').searchbox('setValue','');">清空条件</a>
</div>