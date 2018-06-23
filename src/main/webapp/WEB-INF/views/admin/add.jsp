<%@ include file="templates/adminTop.jspf" %>

<c:if test="${pageContext.request.userPrincipal.name != null}">
    <div class="row">
        <div class="col-sm-8">
            <h3>Dashboard</h3>
        </div>
        <div class="col-sm-4">
            <form id="logoutForm" method="post" action="${contextPath}/logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
            Welcome ${pageContext.request.userPrincipal.name}
            <a href="${contextPath}/" class="badge badge-primary">Home</a>
            <a href="#" onclick="document.forms['logoutForm'].submit()" class="badge badge-primary">Logout</a>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <span class="navbar-brand">Menu</span>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <div class="navbar-nav">
                        <a class="nav-item nav-link" href="${contextPath}/admin">General</a>
                        <a class="nav-item nav-link" href="${contextPath}/admin/articles">Articles</a>
                    </div>
                </div>
            </nav>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <form:form method="POST" modelAttribute="articleForm" class="form-signin">
                <h4 class="form-signin-heading">Add new article</h4>
                <spring:bind path="title">
                    <div class="form-group">
                        <form:input type="text" path="title" class="form-control" placeholder="Title"
                                    autofocus="true"/>
                        <form:errors path="title"/>
                    </div>
                </spring:bind>

                <spring:bind path="content">
                    <div class="form-group">
                        <form:textarea path="content" class="form-control" placeholder="Data" rows="7"/>
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
                <button class="btn btn-lg btn-primary btn-block" type="submit">Add</button>
            </form:form>
        </div>
    </div>
</c:if>

<%@ include file="templates/adminBottom.jspf" %>

