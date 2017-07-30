<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<c:if test="${page == null || page.totalElements == 0 }">
		没有任何记录。
	</c:if>
	
	<c:if test="${page != null && page.numberOfElements != 0}">
		<table border="1" cellpadding="10" cellspacing="0" align="center">
			<thead>
				<tr>
					<th>ID</th>
					<th>LastName</th>
					<th>Email</th>
					<th>Birth</th>
					<th>CreateTime</th>
					<th>Department</th>
					<th>Edit</th>
					<th>Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.content }" var="emp">
					<tr align="center">
						<td>${emp.id }</td>
						<td>${emp.lastName }</td>
						<td>${emp.email }</td>
						<td>
							<fmt:formatDate value="${emp.birth }" pattern="yyyy-MM-dd"/>
						</td>
						<td>
							<fmt:formatDate value="${emp.createTime }" pattern="yyyy-MM-dd hh:mm:ss"/>
						</td>
						<td>${emp.department.departmentName }</td>
						<td><a href="${pageContext.request.contextPath }/emp/${emp.id}">Edit</a></td>
						<td>
							<a href="${pageContext.request.contextPath }/emp/${emp.id}" class="delete">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="8">
						共${page.totalElements }条记录，
						共${page.totalPages }页，
						当前第${page.number + 1 }页，
						<a href="?pageNo=1">首页</a>
						<a href="?pageNo=${page.number + 1 - 1 }">上一页</a>
						<a href="?pageNo=${page.number + 1 + 1 }">下一页</a>
						<a href="?pageNo=${page.totalPages }">末页</a>
					</td>
				</tr>
			</tfoot>
		</table>
	</c:if>
	
	<!-- 用于将a链接的get请求先转为post请求，再将post转为delete请求 -->
	<form action="" method="POST" id="_form">
		<input type="hidden" id="_method" name="_method" value="delete"/>
	</form>
	
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$(".delete").on('click', function(){
				//var lastName = $(this).parents("tr").children().eq(1).html();
				var lastName = $(this).parents("tr").find("td:eq(1)").html();
				//alert(lastName);
				var flag = confirm('确定要删除' + lastName + "吗?");
				if(flag){
					$("#_form").attr('action',this.href);
					$("#_form").submit();
				}
				return false;
			});
		});
	</script>
</body>
</html>