<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
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
	    <link rel="stylesheet" type="text/css" href="extjs/ux/css/ItemSelector.css">
	    
	    <script src="extjs/ext-all-debug.js"></script>
	    <script src="loader.js"></script>
		
	    <script src="api.js"></script>
	    <script src="direct.js"></script>
	    	    
	    <script src="app.js"></script>
    </c:if> 
    
    <c:if test="${not isDevelopment}">
		<link rel="stylesheet" type="text/css" href="wro/app.css?v=<spring:eval expression='@environment["application.version"]'/>" />
		<script src="extjs/ext-all.js"></script>
	    <script src="wro/app.js?v=<spring:eval expression='@environment["application.version"]'/>"></script>   
    </c:if>

	<% Locale locale = RequestContextUtils.getLocale(request); %>
    <% if (locale != null && locale.getLanguage().toLowerCase().equals("de")) { %>
      <script src="extjs/locale/ext-lang-de.js"></script>
    <% } %>	
    
</head>
<body></body>
</html>