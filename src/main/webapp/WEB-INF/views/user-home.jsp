<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!DOCTYPE html><html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Home</title>
</head>

<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<form:form action="${contextPath}/advert/logout" method="post" commandName="advert">
<input type="submit" value="Logout">
</form:form>

<h1>${user.firstName} seller, Welcome  </h1>

<a href="${contextPath}/category/add" >Click here to insert the Book Category</a> <br />
<a href="${contextPath}/advert/add" >Click here to  the Book Description</a> <br />
<a href="${contextPath}/advert/sellerlist" >Click here to view all the Books</a> <br />


</body>
</html>