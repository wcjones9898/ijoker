<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>      
    <title>分类管理</title>
  </head>
  
  <body>
  	
	<table class="eTable">
		<caption></caption>
		<thead>
			<tr>
				<th>序号</th>
				<th>类名</th>							
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="tab">
			<tr>
				<s:form action="AddCatalog">
				<td></td>
				<td>
					<s:textfield name="newCatalogName"/>
				</td>
				<td align="center">
					<s:submit value="添加" action="AddCatalog" cssClass="bt_register"/>	
				</td>
				</s:form>
			</tr>
			<s:iterator value="catalogList" status="st">
				<tr>
					<td>
						<s:property value="#st.Index+1" />
					</td>
					<td>
						<s:property value="catalogName" />
					</td>					
					
					<td>
						<s:url action="LoadCatalogForModify" id="modify">
							<s:param name="selectedIndex" value="#st.Index"></s:param>
						</s:url>						
						<s:url action="DeleteCatalog" id="delete">
							<s:param name="selectedCatalogId" value="catalogId"></s:param>
						</s:url>
						<s:a href="%{modify}">[修改]</s:a>											
						<s:a href="%{delete}">[删除]</s:a>
					</td>
				</tr>
			</s:iterator>
			</tbody>
		</table>
  </body>
</html>
