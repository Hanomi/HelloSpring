<%@ include file="templates/start.jspf" %>
<%@include file="templates/header.jspf" %>

<div class="row">
    <c:if test="${!empty listArticles}">
        <div class="col">
            <table class="table table-hover">
                <thead class="thead-light">
                <tr>
                    <th scope="col">Articles:</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listArticles}" var="article">
                    <tr>
                        <td><a href="${contextPath}/element/${article.slug}">${article.title}</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>
</div>

<%@ include file="templates/end.jspf" %>