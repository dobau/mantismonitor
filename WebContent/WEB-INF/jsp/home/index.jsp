<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="context" value="${pageContext.request.contextPath}" />


<html>
	<head></head>
	<body>
	
		<div id="msgError">${error}</div>
		<div id="msgSuccess">${success}</div>
	
		<form method="post" action="${context}/login">
			<input type="text" name="login"/>
			<input type="password" name="password"/>
			<input type="submit" value="sign in">
		</form>

	</body>
</html>