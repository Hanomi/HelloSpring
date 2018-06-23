<%@ include file="templates/start.jspf" %>
<%@include file="templates/header.jspf" %>

<div class="row">
    <c:if test="${currentArticle != null}">
        <div class="col">
            <table class="table">
                <thead class="thead-light">
                <tr>
                    <th scope="col">${currentArticle.title}</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${currentArticle.content}</td>
                </tr>
                </tbody>
            </table>
        </div>
    </c:if>
</div>

<%@ include file="templates/end.jspf" %>
