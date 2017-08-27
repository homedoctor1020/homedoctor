<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.dw.model.Resource"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>下载文件显示页面</title>
</head>
<style type="text/css">
input[type="text"] {
	height: auto;
	margin-bottom: 15px;
	padding: 3px 9px;
}

.user_img {
	width: 48px;
	height: 48px;
}
</style>
<body>
	<!-- 遍历Map集合 -->
	<%-- <c:forEach var="me" items="${fileNameMap}">
        <c:url value="/servlet/DownLoadServlet" var="downurl">
            <c:param name="filename" value="${me.key}"></c:param>
        </c:url>
        ${me.value}<a href="${downurl}">下载</a>
        <br/>
    </c:forEach> --%>
	<table class="table table-hover table-condensed">
		<tr>
			<td align="center"><strong>编号</strong></td>
			<td align="center">头像</td>
			<td align="center"><strong>微信昵称</strong></td>
			<td align="center"><strong>文件名称</strong></td>
			<td align="center"><strong>文件大小</strong></td>
			<td align="center"><strong>报告类型</strong></td>
			<td align="center"><strong>上传时间</strong></td>
			<td align="center"><strong>执行操作</strong></td>
		</tr>
		<c:forEach items="${list}" var="re" varStatus="status">
			<tr>
				<td><c:out value="${ status.index + 1}" /></td>
				<td><c:import var="headimg"
						url="http://localhost:8080/Doctor1020/pre?method=getimage&openid=${re.openid}" />
					<img class="user_img" src="${headimg}"></td>
				<td><c:import var="nickname"
						url="/pre?method=getnickname&openid=${re.openid}" /> <c:out
						value="${nickname}" /></td>
				<td><c:out value="${re.originName}" /></td>
				<td><c:out value="${re.size}" /></td>
				<td><c:if test="${re.type==1}">
						类型1
						</c:if> <c:if test="${re.type==2}">
						类型2
						</c:if> <c:if test="${re.type==3}">
					    类型3
						</c:if></td>
				<td><c:out value="${re.createtime}" /></td>
				<%-- <td>
						<c:out value="${student.stTel}" />
					</td>
					<td>
						<c:out value="${student.stDept}" />
					</td>
					<td>
						<c:out value="${student.stAddress}" />
					</td> --%>
				<td>
				<c:url value="http://localhost:8080/generic/web/viewer.html?http://localhost:8080/Doctor1020/WEB-INF/upload/${re.openid}/${re.newfileName}"
						var="yulanurl">
						<c:param name="filename" value="${re.newfileName}"></c:param>
						<c:param name="openid" value="${re.openid}"></c:param>
					</c:url>
				<a href="${yulanurl}"
					class="btn btn-danger">预览</a> 
					<c:url value="/download"
						var="downurl">
						<c:param name="filename" value="${re.newfileName}"></c:param>
						<c:param name="openid" value="${re.openid}"></c:param>
					</c:url> 
					<a href="${downurl}"
					class="btn btn-primary">下载</a></td>
			</tr>
		</c:forEach>
</body>
</html>