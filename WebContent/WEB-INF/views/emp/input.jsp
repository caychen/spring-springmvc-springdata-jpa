<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<c:set value="${pageContext.request.contextPath }/emp" var="url"></c:set>
	<c:if test="${employee.id != null }">
		<c:set value="${pageContext.request.contextPath }/emp/${employee.id }" var="url"></c:set>
	</c:if>

	<form:form action="${url }" method="post" modelAttribute="employee">
		
		<c:if test="${employee.id != null }">
			<input type="hidden" id="_oldLastName" value="${employee.lastName }"/>
			<form:hidden path="id"/>
			<input type="hidden" name="_method" value="PUT"/>
		</c:if>
	
		LastName:<form:input path="lastName" id="lastName"/>
		<br/>
		Email:<form:input path="email"/>
		<br/>
		Birth:<form:input path="birth"/>
		<br/>
		Department:<form:select path="department.id" items="${departments }" itemLabel="departmentName" itemValue="id"></form:select>
		<br/>
		<input type="submit" value="Submit" />
	</form:form>
	
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#lastName").blur(function(){
				$.ajax({
					url:'${pageContext.request.contextPath}/validateLastName',
					data:{
						lastName: $.trim($("#lastName").val()),
						time: new Date(),
					},
					type:'post',
					dataType:'json',
					success:function(data){
						if(data == "0"){
							alert('名字可用!');
						}else{
							alert('名字不可用!');
						}
					},
					error:function(){
						
					}
					
				});
			});
		});
	</script>
</body>
</html>