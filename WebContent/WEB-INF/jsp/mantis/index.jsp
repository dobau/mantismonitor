<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="context" value="${pageContext.request.contextPath}" />

<html>
	<head></head>
	<body>
	
		<fmt:message key="error" />
		<fmt:message key="success" />
	
		<form method="post" action="${context}/login">
			<input type="text" name="login"/>
			<input type="password" name="password"/>
			<input type="button" value="sign in">
		</form>
	</body>
</html>