<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<script type="text/javascript" charset="utf-8">
	function logoutFun() {
		$.messager.confirm('注销', '你确定要退出系统?', function(r) {
			if (r) {
				$.getJSON('${pageContext.request.contextPath}/do/user/logout', function(result) {
					location.replace('${pageContext.request.contextPath}/ht/index.jsp');
				});
			}
		});

	}
	function adminInfoFun() {
		$('<div/>').dialog({
			href : '${pageContext.request.contextPath}/do/user/adminInfo',
			width : 650,
			height : 300,
			modal : true,
			title : '管理员信息',
			buttons : [ {
				text : '修改密码',
				iconCls : 'icon-edit',
				handler : function() {
					var d = $(this).closest('.window-body');
					$('#admin_adminInfo_form').form('submit', {
						url : '${pageContext.request.contextPath}/do/user/modifyCurrentAdminPwd',
						success : function(result) {
							try {
								var r = $.parseJSON(result);
								if (r.success) {
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
			}
		});
	}
</script>
<div id="sessionInfoDiv" style="position: absolute;right: 5px;top:10px;">
	<c:if test="${sessionInfo.adminId != null}">[<strong>${sessionInfo.loginName}</strong>]，欢迎你！</c:if>
</div>
<div style="position: absolute; right: 0px; bottom: 0px; ">
	<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_kzmbMenu',iconCls:'icon-help'">控制面板</a>
	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-logout',plain:true" onclick="logoutFun()">注销</a>
</div>

<div id="layout_north_kzmbMenu" style="width: 100px; display: none;padding-left: 0px;">
	<div onclick="adminInfoFun();"><span class="icon-my_account icon-bolck"></span>个人信息</div>
	<div>
		<span>更换主题</span>
		<div style="width: 120px;">
			<div onclick="changeTheme('default');">default</div>
		</div>
	</div>
</div>
