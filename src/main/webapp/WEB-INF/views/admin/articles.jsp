<%@ include file="templates/adminStart.jspf" %>

<c:if test="${pageContext.request.userPrincipal.name != null}">
    <%@include file="templates/adminHeader.jspf" %>

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
            <a href="${contextPath}/admin/articles/add" class="btn btn-primary btn-lg btn-block" role="button">Add articles</a>
        </div>
    </div>
</c:if>
<%@ include file="templates/admintEnd.jspf" %>
