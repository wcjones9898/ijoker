<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>      
    <title>已冻结用户列表</title>
  </head>
  
  <body>
  	<s:form action="UserListAction">
	<table class="eTable">
		<thead>
			<tr>
				<th>序号</th>
				<th>用户名</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="tab">
			<s:iterator value="userList" status="st">
				<tr>
					<td>
						<s:property value="#st.Index+1" />
					</td>
					<td>
						<s:property value="userName" />
					</td>					
					
					<td>
						<s:url action="LoadUserForDetail" id="load">
							<s:param name="selectedUserIndex" value="#st.Index"></s:param>
						</s:url>
						<s:url action="UnlockUser" id="unlock">
							<s:param name="selectedUsername" value="userName"></s:param>
						</s:url>						

						<s:a href="%{load}">[查看]</s:a>						
						<s:a href="%{unlock}">[解冻]</s:a>

					</td>
				</tr>
			</s:iterator>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="6">
						<s:submit value="上一页" action="JokePrePage" cssClass="bt_login"/>
						<s:submit value="下一页" action="JokeNextPage" cssClass="bt_login"/>
					</td>					
				</tr>
			</tfoot>
			
		</table>
		</s:form>
  </body>
</html>
