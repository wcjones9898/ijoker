<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>      
    <title>未审核列表</title>
  </head>
  
  <body>
  	<s:form action="UnverifyListAction">
	<table class="eTable">
		<thead>
			<tr>
				<th>序号</th>
				<th>标题</th>
				<th>作者</th>
				<th>关键字</th>
				<th>上传时间</th>				
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="tab">
			<s:iterator value="unverifyList" status="st">
				<tr>
					<td>
						<s:property value="#st.Index+1" />
					</td>
					<td>
						<s:property value="title" />
					</td>
					<td>
						<s:property value="author" />
					</td>
					<td>
						<s:property value="keyWord" />
					</td>
					<td>
						<s:property value="uploadTime" />
					</td>
					
					<td>
						<s:url action="LoadJokeForVerify" id="load">
							<s:param name="selectedJokeIndex" value="#st.Index"></s:param>
						</s:url>
						<s:url action="DeleteJoke" id="delete">
							<s:param name="selectedJokeId" value="Id"></s:param>
						</s:url>						

						<s:a href="%{load}" cssClass="bb">[审核]</s:a>						
						<s:a href="%{delete}" cssClass="bb">[删除]</s:a>

					</td>
				</tr>
			</s:iterator>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="6">
						<s:submit value="上一页" action="UnverifyPrePage" cssClass="bt_login"/>
						<s:submit value="下一页" action="UnverifyNextPage" cssClass="bt_login"/>
					</td>					
				</tr>
			</tfoot>
			
		</table>
		</s:form>
  </body>
</html>
