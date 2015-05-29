<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:spring="http://www.springframework.org/tags"
	xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:form="http://www.springframework.org/tags/form" version="2.0">
	<jsp:directive.page language="java" contentType="text/html" />

	<html xmlns="http://www.w3.org/1999/xhtml">

<style>
table {
	margin: 30px;
}

table {
	border-collapse: seperate;
	border-spacing: 0px;
	border: 1px solid gray;
}

td {
	border: 1px solid gray;
	padding: 6px;
}

thead {
	font-weight: bold;
}

tbody {
	text-align: center;
}
</style>

<table>
	<tr>
		<td>
			<p>${label.view}:
				<c:choose>
					<c:when test="${currentSitePreference.normal}">
						Normal
				</c:when>
					<c:when test="${currentSitePreference.mobile}">
						Mobile
				</c:when>
					<c:when test="${currentSitePreference.tablet}">
						Tablet
				</c:when>
					<c:otherwise>
						None
				</c:otherwise>
				</c:choose>
			</p>
		</td>


		<td>
				<p>${label.device}:
				<c:choose>
					<c:when test="${currentDevice.normal}">
						Normal
				</c:when>
					<c:when test="${currentDevice.mobile}">
						Mobile
				</c:when>
					<c:when test="${currentDevice.tablet}">
						Tablet
				</c:when>
					<c:otherwise>
						None
				</c:otherwise>
				</c:choose>
			</p>
		</td>
	</tr>
</table>

	</html>

</jsp:root>