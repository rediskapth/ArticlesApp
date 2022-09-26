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
            <form:form action="/articles/update/${id}" method="post" modelAttribute="articleDto">
            <h2 class>Update article with title - ${articleDto.title}</h2>
                <div class="form-group">

                    <spring:bind path="title">Article Title:
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="text" path="title" class="form-control" placeholder="Enter Article Title"></form:input>
                            <form:errors path="title"></form:errors>
                        </div>
                    </spring:bind>

                    <spring:bind path="content">Article Content:
                        <div class="form-group ${status.error ? 'has-error' : ''}">
                            <form:input type="text" path="content" class="form-control" placeholder="Enter Article Content"></form:input>
                            <form:errors path="content"></form:errors>
                        </div>
                    </spring:bind>

                    <spring:bind path="user">Choose User:
                        <select class="form-control" id="user" name="user">
                            <c:forEach items="${users}" var="user">
                                <option value="${user}">
                                    <c:out value="${user.username}"/><br>
                                </option>
                            </c:forEach>
                         </select>
                    </spring:bind>

                </div>
                    <a href="/articles/findArticles"><input type="button" value="Back"></a>
                    <input type="submit" value="Submit"/>
            </form:form>

        </div>
    </body>
</html>
