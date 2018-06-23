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
        <c:if test="${!empty listArticles}">
            <div class="col">
                <table class="table table-hover">
                    <thead class="thead-light">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Title</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${listArticles}" var="article">
                        <tr>
                            <th scope="row">${article.id}</th>
                            <td>${article.title}</td>
                            <td>
                                <a href="${contextPath}/element/${article.slug}" class="badge badge-primary">Read</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
    </div>
    <div class="row">
        <div class="col">
            <a href="${contextPath}/admin/articles/add" class="btn btn-primary btn-lg btn-block" role="button">Add new article</a>
        </div>
    </div>
</c:if>
<%@ include file="templates/adminBottom.jspf" %>
