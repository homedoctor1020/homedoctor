<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="com.dw.model.Resource"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			;
			pageContext.setAttribute("basePath", basePath);
%>
<!DOCTYPE HTML>
<html>
<head>
<title>显示病历记录页面</title>
</head>
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
		<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
		
		
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
				<!--服务器端请求地址  -->
				<!-- http://www.1020doctor.cn/Doctor1020/pre?method=getimage&openid=${re.openid} -->
				<td><c:import var="headimg"
						url="${basePath}/Doctor1020/pre?method=getimage&openid=${re.openid}" />
					<img class="user_img" src="${headimg}"></td>
				<td><c:import var="nickname"
						url="/pre?method=getnickname&openid=${re.openid}" /> <c:out
						value="${nickname}" /></td>
				<td><c:out value="${re.originName}" /></td>
				<td><c:out value="${re.size}" /></td>
				<td><c:if test="${re.type==1}">
						检查报告
						</c:if> <c:if test="${re.type==2}">
						健康方案
						</c:if> <c:if test="${re.type==3}">
					   就诊记录
						</c:if></td>
				<td><c:out value="${re.createtime}" /></td>
				<td>
				<!-- 服务器端访问地址 -->
				<!-- http://www.1020doctor.cn/generic/web/viewer.html?file=http://www.1020doctor.cn/Doctor1020/upload/${re.openid}/${re.newfileName} -->
				<!-- 本地访问地址 -->
				<!-- http://www.localhost:8080/generic/web/viewer.html?file=http://www.localhost:8080/Doctor1020/upload/${re.openid}/${re.newfileName} -->
				<c:url value="/pdf/web/viewer.html?file=${basePath}/upload/${re.openid}/${re.newfileName}"
						var="yulanurl">
					</c:url>
				<a href="${yulanurl}"
					class="btn btn-danger" target="_blank">预览</a> 
					<c:url value="/download"
						var="downurl">
						<c:param name="filename" value="${re.newfileName}"></c:param>
						<c:param name="openid" value="${re.openid}"></c:param>
					</c:url> 
					<a href="${downurl}"
					class="btn btn-primary">下载</a>
					<c:url value="/delete"
						var="deleteurl">
						<c:param name="filename" value="${re.newfileName}"></c:param>
						<c:param name="openid" value="${re.openid}"></c:param>
					</c:url> 
					<a href="${deleteurl}"
					class="btn btn-warning">删除</a></td>
			</tr>
		</c:forEach>
</body>
</html>