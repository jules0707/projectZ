<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:spring="http://www.springframework.org/tags"
	xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:form="http://www.springframework.org/tags/form" version="2.0">
	<jsp:directive.page import="house.my.model.Property" />
	<jsp:directive.page language="java" contentType="text/html" />
	<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>${label.my.properties}</title>
<span><jsp:include page="../includes/head.jsp" /></span>
</head>

<body>
	<jsp:include page="../includes/navbar.jsp" />
	<hr />
	<hr />
	<header>
		<h3>${label.my.properties}</h3>
	</header>

	<table>
		<c:forEach items="${myPropertiesList}" var="property">
			<c:if test="${property.cancelationDate==null}">
				<tr>
					<td><c:out value="${property.address.locality}" /></td>
					<td><c:out value="${property.price}" /></td>
					<td><a href="../remove_listing/${property.idProperty}"><spring:message code="label.remove.this.property" /> </a></td>
				</tr>
			</c:if>
		</c:forEach>
	</table>


	<jsp:include page="../includes/menu.jsp" />
	<jsp:include page="../includes/content.jsp" />
	<jsp:include page="../includes/footer.jsp" />



</body>
	</html>
</jsp:root>