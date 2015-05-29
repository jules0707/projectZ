<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:form="http://www.springframework.org/tags/form" version="2.0">
	<jsp:directive.page language="java" contentType="text/html" />
	
<html xmlns="http://www.w3.org/1999/xhtml">

<p> ${label.site.preference}:

	<c:choose>
		<c:when test="${currentSitePreference.normal}"> 
			Normal | 
			<a href="?site_preference=mobile">Mobile</a> | 
			<a href="?site_preference=tablet">Tablet</a>
		</c:when>
		<c:when test="${currentSitePreference.mobile}"> 
			<a href="?site_preference=normal">Normal</a> | 
			Mobile | 
			<a href="?site_preference=tablet">Tablet</a>
		</c:when>
		<c:when test="${currentSitePreference.tablet}"> 
			<a href="?site_preference=normal">Normal</a> | 
			<a href="?site_preference=mobile">Mobile</a> | 
			Tablet
		</c:when>
		<c:otherwise>
			<a href="?site_preference=normal">Normal</a> | 
			<a href="?site_preference=mobile">Mobile</a> | 
			<a href="?site_preference=tablet">Tablet</a>
		</c:otherwise>
	</c:choose>
</p>

</html>

</jsp:root>