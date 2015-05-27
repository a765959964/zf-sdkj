<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<div align="center">
	<form id="icon_add_form" method="post">
		<table class="tableForm">
			<tr style="display: none">
				<th>编号</th>
				<td>
					<input name="id" readonly="readonly" style="width: 370px;" />
				</td>
			</tr>
			<tr>
				<th>名称</th>
				<td>
					<input name="name" class="easyui-validatebox" style="width: 370px;" />
				</td>
			</tr>

			<tr>
				<th>地址</th>
				<td>
					<input name="url" class="easyui-validatebox" style="width: 370px;" />
				</td>
			</tr>
		</table>
	</form>
</div>