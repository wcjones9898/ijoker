<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>      
    <title>修改笑话信息</title>
  </head>
  
  <body>
  	<s:form action="ModifyCartoon">
	<table class="eTable">		
		<tbody id="tab">
			<tr>
				<td width="30%">标题</td>
				<td width="70%">
					<s:textfield name="selectedCartoon.cartoonTitle"/>
				</td>
			</tr>			
			<tr>
				<td>作者</td>
				<td>					
					<s:property value="selectedCartoon.authorName" />
				</td>
			</tr>
			<tr>
				<td>内容</td>
				<td>
					<s:iterator value="selectedCartoonFiles" status="st">
						<s:if test="#st.Index==0">
							<img src="http://59.77.5.42:80/<s:property value='filePath'/><s:property value='fileName'/><s:property value='fileExtension'/>">
							<br>						
						</s:if>
						<s:else>
							<a target="blank" href="http://59.77.5.42:80/<s:property value='filePath'/><s:property value='fileName'/><s:property value='fileExtension'/>">
							[图片<s:property value="#st.Index+1" />]
							</a>
						</s:else>
					</s:iterator>
					
				</td>
				
			</tr>
			<tr>
				<td>上传时间</td>
				<td>
					<s:property value="selectedCartoon.uploadTime" />
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
