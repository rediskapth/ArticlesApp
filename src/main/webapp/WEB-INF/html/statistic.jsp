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
        <h2 class>Show statistic</h2>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <td>Date</td>
                        <td>Number of creations and updates Articles</td>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach items="${result}" var="result">
                        <tr>
                            <td>
                                <fmt:formatDate value="${result.key}" pattern="yyyy-MM-dd" />
                            </td>
                            <td>
                                <c:out value="${result.value}"/>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </body>
</html>
