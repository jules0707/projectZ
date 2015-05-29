<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:spring="http://www.springframework.org/tags"
	xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:form="http://www.springframework.org/tags/form" version="2.0">
	<jsp:directive.page language="java" contentType="text/html" />
	<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>${label.search.property}</title>
<span><jsp:include page="../includes/head.jsp" /></span>
</head>

<body>
	<jsp:include page="../includes/navbar.jsp" />
	<hr />
	<hr />

	<form:form commandName="PropertyForm">
		<table>
			<tr>
				<td><label class="col-sm-2 control-label"><spring:message code="label.locality" /></label></td>
				<td><form:input path="propertyAddressLocality" class="form-control" placeholder="${label.locality}" size="20" /></td>
			</tr>
			<tr>
				<td><label class="col-sm-2 control-label"><spring:message code="label.price" /></label></td>
				<td><form:input path="propertyPrice" class="form-control" placeholder="${label.price}" /></td>
			</tr>
			<tr>
				<spring:message code="label.find.property" var="labelFindProperty" />
				<td colspan="2"><input type="submit" value="${labelFindProperty}" /></td>
			</tr>
		</table>
	</form:form>


	<p>
	<table>
		<c:forEach items="${liste}" var="property">
			<tr>
				<td><c:out value="${property.price}" /></td>
				<td><c:out value="${property.address.locality}" /></td>
				<td><c:forEach var="member" items="${property.members}">
						<li>${member.name}</li>
					</c:forEach></td>

			</tr>
		</c:forEach>
	</table>
	</p>

	<jsp:include page="../includes/menu.jsp" />
	<jsp:include page="../includes/content.jsp" />
	<jsp:include page="../includes/footer.jsp" />



</body>
	</html>
</jsp:root>