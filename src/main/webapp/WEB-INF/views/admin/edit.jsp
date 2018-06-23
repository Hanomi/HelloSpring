<%@ include file="templates/adminStart.jspf" %>

<c:if test="${pageContext.request.userPrincipal.name != null}">
    <%@include file="templates/adminHeader.jspf" %>

    <div class="row">
        <div class="col">
            <h3>Edit article</h3>
            <form:form method="POST" modelAttribute="articleForm" action="${contextPath}/admin/articles/edit/save">
                <spring:bind path="id"><form:hidden path="id"/></spring:bind>
                <p>
                    <spring:bind path="title">
                        <form:input path="title" title="Title"/>
                        <form:errors path="title"/>
                    </spring:bind>
                </p>
                <p>
                    <spring:bind path="content">
                        <form:textarea path="content" title="Content"/>
                        <form:errors path="content"/>
                    </spring:bind>
                </p>
                <p>
                    <spring:bind path="slug">
                        <form:input path="slug" title="Slug"/>
                        <form:errors path="slug"/>
                    </spring:bind>
                </p>
                <p>
                    <input type="submit" value="Save edit"/>
                </p>
            </form:form>
        </div>
    </div>
</c:if>

<%@ include file="templates/admintEnd.jspf" %>

