<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>      
    <title>修改分类</title>
  </head>
  
  <body>
  	<s:form action="UserListAction">
	<table class="eTable">		
		<tbody id="tab">
			<tr>
				<td width="30%">用户ID</td>
				<td width="70%">
					<s:property value="selectedUser.userId"/>
				</td>
			</tr>
			<tr>
				<td>用户名</td>
				<td>
					<s:property value="selectedUser.userName" />
				</td>
			</tr>
			
			<tr>
				<td>昵称</td>
				<td>
					<s:property value="selectedUser.nickName" />
				</td>
			</tr>
			
		</tbody>
		<tfoot>			
		</tfoot>
	</table>
	</s:form>
  </body>
</html>
