<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:spring="http://www.springframework.org/tags"
	      xmlns:c="http://java.sun.com/jsp/jstl/core" 
	      xmlns:form="http://www.springframework.org/tags/form" version="2.0">

	<jsp:directive.page language="java" contentType="text/html" />

	<html xmlns="http://www.w3.org/1999/xhtml">

<!-- Fixed navbar -->
<div class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div><jsp:include page="language.jsp" /></div>

	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/home">Project Z</a>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">

				<li class="active"><a href="/add"><spring:message code="label.list.property"/></a></li>
				<c:url var="searchUrl" value="/search" />
				<li><a href="${searchUrl}"><spring:message code="label.find.property"/></a></li>
				<c:url var="myPropertiesUrl" value="/myProperties" />
				<li><a href="${myPropertiesUrl}"><spring:message code="label.my.properties"/></a></li>
				<c:url var="aboutUrl" value="/about" />
				<li><a href="${aboutUrl}"><spring:message code="label.about"/></a></li>
				<c:url var="contactUrl" value="/contact" />
				<li><a href="${contactUrl}"><spring:message code="label.contact"/></a></li>
				<c:url var="logoutUrl" value="/logout" />
				<li><form:form class="logout-button" action="${logoutUrl}" method="post" >
						<spring:message code="label.signout" var="labelSignout" />
						<input type="submit" value="${labelSignout}" />
					</form:form></li>
			</ul>
		</div>
	</div>
</div>

	</html>
</jsp:root>