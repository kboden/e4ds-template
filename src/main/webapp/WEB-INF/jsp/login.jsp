<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.Locale"%>
<%@page import="org.springframework.web.servlet.support.RequestContextUtils"%>
<!doctype html> 
<html>
<head>
    <meta charset="utf-8">
    <title>e4ds-template</title>    
    <link rel="stylesheet" type="text/css" href="extjs/resources/css/ext-all.css">    
        
    <script src="i18n.js"></script>
    
    <spring:eval expression="@environment.acceptsProfiles('development')" var="isDevelopment" />    
    <c:if test="${isDevelopment}">
            <link rel="stylesheet" type="text/css" href="resources/css/app.css">
	    <script src="extjs/ext-all-debug.js"></script>	    
	    <script src="login.js"></script>
    </c:if>
    <c:if test="${not isDevelopment}">
      <link rel="stylesheet" type="text/css" href="wro/login.css?v=<spring:eval expression='@environment["application.version"]'/>" />
      <script src="extjs/ext-all.js"></script> 
      <script src="wro/login.js?v=<spring:eval expression='@environment["application.version"]'/>"></script>        
    </c:if>
	    
	<% Locale locale = RequestContextUtils.getLocale(request); %>
    <% if (locale != null && locale.getLanguage().toLowerCase().equals("de")) { %>
      <script src="extjs/locale/ext-lang-de.js"></script>
    <% } %>	
	<c:if test="${not empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}">
	   <script type="text/javascript">
	   Ext.onReady(function() {
	   Ext.Msg.alert(i18n.error, i18n.login_failed);
	   });
	   </script>
	</c:if>    
</head>
<body>


</body>
</html>