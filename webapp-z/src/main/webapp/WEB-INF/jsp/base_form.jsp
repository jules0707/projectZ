<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:spring="http://www.springframework.org/tags"
	xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:form="http://www.springframework.org/tags/form" version="2.0">
	<jsp:directive.page language="java" contentType="text/html" />
	<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<title>${label.list.property}</title>
<jsp:include page="includes/head.jsp" />
</head>

<body>
	<jsp:include page="includes/navbar.jsp" />

<hr/>
	<form:form action="save" commandName="property">

		<table>
			<tr>
				<td><label class="col-sm-2 control-label"><spring:message code="label.locality"/></label></td>
				<td><form:input path="address.locality" id="property_address_locality" class="form-control"
						placeholder="${label.locality}" /></td>
			</tr>

			<tr>
				<td><label class="col-sm-2 control-label"><spring:message code="label.price"/></label></td>
				<td><form:input path="price" id="property_price" class="form-control"
						placeholder="${label.price}" size="10" /></td>
			</tr>
			
			<tr>
				<td colspan="2"><button type="submit" class="btn btn-default" name="save_form" id="save_form"><spring:message code="label.list.property"/></button></td>
			</tr>

		</table>

	</form:form>


	<jsp:include page="includes/menu.jsp" />
	<jsp:include page="includes/content.jsp" />
	<jsp:include page="includes/footer.jsp" />

</body>
	</html>
</jsp:root>