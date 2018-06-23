<%@ include file="templates/adminStart.jspf" %>

<c:if test="${pageContext.request.userPrincipal.name != null}">
    <%@include file="templates/adminHeader.jspf" %>

    <div class="row">
        <div class="col">
            <h4>Edit article</h4>
            <form:form method="POST" modelAttribute="articleForm" action="${contextPath}/admin/articles/edit/save">
                <form:hidden path="id"/>
                <div class="form-group">
                    <form:input path="title" title="Title" class="form-control" placeholder="Title"/>
                    <form:errors path="title" class="form-text text-muted"/>
                </div>
                <div class="form-group">
                    <form:textarea path="content" title="Content" class="form-control" placeholder="Content"/>
                    <form:errors path="content" class="form-text text-muted"/>
                </div>
                <div class="form-group">
                    <form:input path="slug" title="Slug" class="form-control" placeholder="Slug"/>
                    <form:errors path="slug" class="form-text text-muted"/>
                </div>
                <div class="form-group">
                    <input type="submit" class="btn btn-primary btn-lg btn-block" value="Save"/>
                </div>
            </form:form>
        </div>
    </div>
</c:if>

<%@ include file="templates/admintEnd.jspf" %>

