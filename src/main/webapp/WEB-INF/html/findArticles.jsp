<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
         <c:import url="${contextPath}/WEB-INF/html/header.jsp"/>
    </head>

    <body>
        <c:import url="${contextPath}/WEB-INF/html/navibar.jsp"/>

        <div class="container">
        <h2 class>Show all articles</h2>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <td>Serial number</td>
                        <td>Title</td>
                        <td>Content</td>
                        <td>User</td>
                        <td>Creation Date</td>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach varStatus="status" items="${articles}" var="article">
                        <tr>
                            <td>
                                <c:out value="${status.count}"/>
                            </td>
                            <td>
                                <c:out value="${article.title}"/>
                            </td>
                            <td>
                                <c:out value="${article.content}"/>
                            </td>
                            <td>
                                <c:out value="${article.user.username}"/>
                            </td>
                            <td>
                                <fmt:formatDate value="${article.dateCreation}" pattern="yyyy-MM-dd" />
                            </td>
                    <security:authorize access="hasRole('ROLE_ADMIN')">
                            <td>
                                <a href="/articles/form/update/${article.id}">
                                    <input type="submit" value="Update"/>
                                </a>

                                <a href="/articles/delete?id=${article.id}">
                                    <input type="submit" value="Delete"/>
                                </a>
                            </td>
                    </security:authorize>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </body>
</html>
