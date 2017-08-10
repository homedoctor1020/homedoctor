<%@ page language="java" import="java.util.*,com.dw.model.Vip"
	pageEncoding="utf-8"%>
<%@ page import="com.dw.dao.impl.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>显示用户信息</title>

     <style type="text/css">
       input[type="text"]{
              height: auto;
	          margin-bottom: 15px;
	          padding: 3px 9px;
            }
            .user_img{
            width: 48px;
            height: 48px;
            }
     </style>
	</head>

	<body>
       <div>
       <div style="float: left;">
        <form action="searchBynickname" method="post">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                        昵称：<input type="text" name="searchnickname" placeholder="患者昵称...">
         <i class="icon-search"></i>&nbsp;<button type="submit" class="btn btn-inverse"> 查找</button>
        </form>
        </div>
        <div style="float: right;">
        <form action="refreshVip" method="post">
		<button type="submit" class="btn btn-default btn-lg">
			<span class="glyphicon glyphicon-star"></span>
		更新用戶列表
		</button>
	</form>
	</div>
	</div>
		<table  class="table table-hover table-condensed">
		    <tr>
				<td align="center">
					<strong>编号</strong>
				</td>
				<td align="center">
					
				</td>
				<td align="center">
					<strong>微信昵称</strong>
				</td>
				<td align="center">
					<strong>性别</strong>
				</td>
				<td align="center">
					<strong>城市</strong>
				</td>
				<!-- <td align="center">
					<strong>电话</strong>
				</td>
				<td align="center">
					<strong>微信</strong>
				</td>
				<td align="center">
					<strong>地址</strong>
				</td> -->
				<td align="center" >
					<strong>执行操作</strong>
				</td>
			</tr>
			<c:forEach items="${list}" var="vip" varStatus="status">
				<tr>
					<td>
						<c:out value="${ status.index + 1}" />
					</td>
					<td>
						<img class="user_img" src="${vip.headImgUrl}">
					</td>
					<td>
						<c:out value="${vip.nickname}" />
					</td>
					<td>
						<c:if test="${vip.sex==1}" >
						男
						</c:if>
						<c:if test="${vip.sex==2}" >
						女
						</c:if>
						<c:if test="${vip.sex==0}" >
						未知
						</c:if>
					</td>
					<td>
						<c:out value="${vip.city}" />
					</td>
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
						<a href="stdelete?id=${vip.openId}" class="btn btn-danger">删除</a>
						<a href="pre?method=updateStudent&id=${vip.openId}" class="btn btn-primary">更新</a>
					</td>
				</tr>
			</c:forEach>
		</table>

		<hr>
		<a href="main.jsp" class="btn btn-success">返回主菜单</a>
		<hr>


	</body>
</html>
