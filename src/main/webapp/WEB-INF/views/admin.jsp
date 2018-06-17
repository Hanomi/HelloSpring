<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Admin</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="post" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h3>Admin page ${pageContext.request.userPrincipal.name} | <a onclick="document.forms['logoutForm'].submit()">Logout</a>
        </h3>
        <form:form method="POST" modelAttribute="storyForm" class="form-signin">
            <h3 class="form-signin-heading">Add new story</h3>
            <spring:bind path="title">
                <div class="form-group">
                    <form:input type="text" path="title" class="form-control" placeholder="Title"
                                autofocus="true"/>
                    <form:errors path="title"/>
                </div>
            </spring:bind>

            <spring:bind path="content">
                <div class="form-group">
                    <form:input type="text" path="content" class="form-control" placeholder="Data"/>
                    <form:errors path="content"/>
                </div>
            </spring:bind>

            <spring:bind path="slug">
                <div class="form-group">
                    <form:input type="text" path="slug" class="form-control" placeholder="Slug"/>
                    <form:errors path="slug"/>
                </div>
            </spring:bind>
            <span>${message}</span>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
        </form:form>
    </c:if>
</div>
<!-- /container -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>