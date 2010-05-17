<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>      
    <title>修改分类</title>
  </head>
  
  <body>
  	<s:form action="ModifyCatalog">
	<table class="eTable">		
		<tbody id="tab">
			<tr>
				<td width="30%">分类ID</td>
				<td width="70%">
					<s:property value="selectedCatalog.catalogId"/>
				</td>
			</tr>
			<tr>
				<td>类名</td>
				<td>
					<s:textfield name="selectedCatalog.catalogName" />
				</td>
			</tr>
			
			<tr>
				<td>笑话数量</td>
				<td>
					<s:property value="selectedCatalog.jokeNum" />
				</td>
			</tr>
			
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
					<s:submit value="确定" cssClass="bt_register"/>												
					<s:reset value="重置" cssClass="bt_login"/>						
				</td>
			</tr>
		</tfoot>
	</table>
	</s:form>
  </body>
</html>
