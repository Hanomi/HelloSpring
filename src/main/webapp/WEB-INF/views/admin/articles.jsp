<%@ include file="templates/adminStart.jspf" %>

<c:if test="${pageContext.request.userPrincipal.name != null}">
    <%@include file="templates/adminHeader.jspf" %>

    <div class="row">
        <c:if test="${!empty listArticles}">
            <div class="col">
                <table class="table table-hover">
                    <thead class="thead-light">
                    <tr class="d-flex">
                        <th class="col-1">#</th>
                        <th class="col-8">Title</th>
                        <th class="col-3"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${listArticles}" var="article">
                        <tr class="d-flex">
                            <td class="col-1">${article.id}</td>
                            <td class="col-8">${article.title}</td>
                            <td class="col-3">
                                <a href="${contextPath}/element/${article.slug}" class="btn btn-outline-success" role="button">Read</a>
                                <a href="${contextPath}/admin/articles/edit/${article.slug}" class="btn btn-outline-primary" role="button">Edit</a>
                                <a href="${contextPath}/admin/articles/delete/${article.slug}" class="btn btn-outline-danger" role="button">Delete</a>
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
