<%@ include file="templates/adminTop.jspf"%>

<c:if test="${pageContext.request.userPrincipal.name != null}">
    <div class="row justify-content-md-center">
        <form id="logoutForm" method="post" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <h4>Welcome ${pageContext.request.userPrincipal.name} |
            <a href="${contextPath}/" class="badge badge-primary">Home</a> |
            <a href="${contextPath}/" onclick="document.forms['logoutForm'].submit()"
               class="badge badge-primary">Logout</a>
        </h4>
    </div>
    <div class="row justify-content-md-center">
        <div class="col-6">
            <form:form method="POST" modelAttribute="storyForm" class="form-signin">
                <h4 class="form-signin-heading">Add new story</h4>
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
                <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
            </form:form>
        </div>
    </div>
</c:if>

<%@ include file="templates/adminBottom.jspf"%>

