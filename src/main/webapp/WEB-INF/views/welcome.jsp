<%@ include file="templates/header.jspf"%>

<div class="row justify-content-md-center">
    <c:if test="${pageContext.request.userPrincipal.name != null}">

        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h4>Welcome ${pageContext.request.userPrincipal.name} |
            <a href="${contextPath}/" class="badge badge-primary">Home</a> |
            <a href="#" onclick="document.forms['logoutForm'].submit()" class="badge badge-primary">Logout</a>
        </h4>
    </c:if>
</div>
<div class="row justify-content-md-center">
    <c:if test="${!empty listStory}">
        <div class="col col-lg-8">
            <table class="table table-hover">
                <thead class="thead-light">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Title</th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listStory}" var="story">
                    <tr>
                        <th scope="row">${story.id}</th>
                        <td>${story.title}</td>
                        <td><a href="${contextPath}/element/${story.slug}" class="badge badge-primary">Read</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>
</div>

<%@ include file="templates/footer.jspf"%>