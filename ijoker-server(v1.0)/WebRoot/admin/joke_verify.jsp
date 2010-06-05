<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>      
    <title>审核笑话</title>
  </head>
  
  <body>
  
  	<embed		
		align="middle" 		
		height="45" 
		width="500" 
		loop="false" 
		src="http://www.9i5j.com/ringfile/1/19/9i5j.com_心情车站.wav"/>
<%--		src="<s:property value='selectedJoke.location'/>"/>--%>
  	
	<s:form action="VerifyJoke">
	<table class="eTable">		
		<tbody id="tab">
			<tr>
				<td width="30%">标题</td>
				<td width="70%">
					<s:textfield name="selectedJoke.title"/>
				</td>
			</tr>
			<tr>
				<td>关键字</td>
				<td>
					<s:textfield name="selectedJoke.keyWord" />
				</td>
			</tr>
			<tr>
				<td>分类</td>
				<td>
					<s:select name="selectedCatalogId" list="catalogList" listKey="catalogId" listValue="catalogName">						
					</s:select>
				</td>
			</tr>
			<tr>
				<td>作者</td>
				<td>					
					<s:property value="selectedJoke.author" />
				</td>
			</tr>
			<tr>
				<td>上传时间</td>
				<td>
					<s:property value="selectedJoke.uploadTime" />
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
