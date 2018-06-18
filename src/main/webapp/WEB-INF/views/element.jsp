    <div class="row justify-content-md-center">
        <c:if test="${pageContext.request.userPrincipal.name != null}">

            <form id="logoutForm" method="POST" action="${contextPath}/logout">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>

            <h4>Welcome ${pageContext.request.userPrincipal.name} |
                <a href="${contextPath}/" class="badge badge-primary">Home</a> |
                <a href="${contextPath}/" onclick="document.forms['logoutForm'].submit()" class="badge badge-primary">Logout</a>
            </h4>
        </c:if>
    </div>
    <div class="row justify-content-md-center">
        <c:if test="${currentStory != null}">
            <div class="col col-lg-8">
                <table class="table">
                    <thead class="thead-light">
                    <tr>
                        <th scope="col">${currentStory.title}</th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>${currentStory.content}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </c:if>
    </div>

