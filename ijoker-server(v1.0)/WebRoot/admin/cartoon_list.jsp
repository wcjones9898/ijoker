<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>      
    <title>漫画列表</title>
  </head>
  
  <body>
  	<s:form action="CartoonListAction">
	<table class="eTable">
		<thead>
			<tr>
				<th>序号</th>
				<th>标题</th>
				<th>作者</th>			
				<th>上传时间</th>				
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="tab">
			<s:iterator value="cartoonList" status="st">
				<tr>
					<td>
						<s:property value="#st.Index+1" />
					</td>
					<td>
						<s:property value="cartoonTitle" />
					</td>
					<td>
						<s:property value="authorName" />
					</td>					
					<td>
						<s:property value="uploadTime" />
					</td>
					
					<td>
						<s:url action="LoadCartoonForModify" id="load">
							<s:param name="selectedCartoonIndex" value="#st.Index"></s:param>
						</s:url>
						<s:url action="DeleteCartoon" id="delete">
							<s:param name="selectedCartoonId" value="cartoonId"></s:param>
						</s:url>						

						<s:a href="%{load}">[修改]</s:a>						
						<s:a href="%{delete}">[删除]</s:a>

					</td>
				</tr>
			</s:iterator>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="6">
						<s:submit value="上一页" action="CartoonPrePage" cssClass="bt_login"/>
						<s:submit value="下一页" action="CartoonNextPage" cssClass="bt_login"/>
					</td>					
				</tr>
			</tfoot>
			
		</table>
		</s:form>
  </body>
</html>
