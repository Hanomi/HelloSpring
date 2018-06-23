<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <title>404</title>
    <link href="${contextPath}/resources/css/error.css" rel="stylesheet">
</head>
<body>
<div class='c'>
    <div class='_404'>404</div>
    <hr>
    <div class='_1'>THE PAGE</div>
    <div class='_2'>WAS NOT FOUND</div>
    <a class='btn' href='/'>BACK</a>
</div>
</body>
</html>