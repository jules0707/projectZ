<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>Signin Project Z</title>
		<jsp:include page="../includes/head.jsp" />
	</head>

<body>

	<div class="container">

<c:url value="/signin" var="loginUrl"/>
		<form:form name="sign in form" action="${loginUrl}" method="post" class="form-signin" role="form">
				<h2 class="form-signin-heading">Please sign in</h2>
					
				<c:if test="${param.error != null}">
					<div class="alert alert-error">Invalid username and password.
					</div>
				</c:if>
				<c:if test="${param.logout != null}">
					<div class="alert alert-success">You have been logged out.</div>
				</c:if>
				
					<input type="text" id="j_username" name="username" value="${username}"
					class="form-control" required placeholder="Username" autofocus />
				
					<input type="password" id="j_password" name="password"
					class="form-control" placeholder="Password" required />
					
				<label class="checkbox">
					<input type="checkbox" value="remember-me"> Remember me </input>
				</label>
				
				<div class="form-actions">
					<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
				</div>
		</form:form>

	</div>
</body>
</html>
