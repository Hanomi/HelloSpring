<%@ include file="templates/adminStart.jspf" %>

<c:if test="${pageContext.request.userPrincipal.name != null}">
    <%@include file="templates/adminHeader.jspf" %>

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

<%@ include file="templates/admintEnd.jspf" %>

