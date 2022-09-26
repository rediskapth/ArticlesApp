<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
    <head>
         <c:import url="${contextPath}/WEB-INF/html/header.jsp"/>
    </head>

    <body>
        <c:import url="${contextPath}/WEB-INF/html/navibar.jsp"/>

        <div class="container">
            <form:form action="/users/" method="post" modelAttribute="userDto">
            <h2 class>Create new user</h2>
                <div class="form-group">

                    <spring:bind path="username">Username:
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="text" path="username" class="form-control" placeholder="Enter Username"></form:input>
                            <form:errors path="username"></form:errors>
                        </div>
                    </spring:bind>

                    <spring:bind path="password">Password:
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="password" path="password" class="form-control" placeholder="Enter Password"></form:input>
                            <form:errors path="password"></form:errors>
                        </div>
                    </spring:bind>

                    <spring:bind path="userRole">Choose User Role:
                        <select class="form-control" id="userRole" name="userRole">
                            <c:forEach items="${userRoles}" var="userRole">
                                <option value="${userRole}">
                                    <c:out value="${userRole}"/><br>
                                </option>
                            </c:forEach>
                         </select>
                    </spring:bind>

                </div>
                    <a href="/users/findUsers"><input type="button" value="Back"></a>
                    <input type="submit" value="Submit"/>
            </form:form>

        </div>
    </body>
</html>
