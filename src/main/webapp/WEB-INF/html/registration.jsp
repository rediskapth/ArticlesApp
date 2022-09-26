<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

 <!DOCTYPE html>
 <html lang="en">
 <head>
     <meta charset="utf-8">
     <title>Create an account</title>
     <style>
         <%@include file="/WEB-INF/css/common.css" %>
         <%@include file="/WEB-INF/css/bootstrap.min.css"%>
     </style>
 </head>

 <body>

 <div class="container">
     <form:form method="post" modelAttribute="userDto" class="form-signin" action="/users/registration">
         <h2 class="form-signin-heading">Create your account</h2>

         <spring:bind path="username"><span style="color:red">${message}</span>
             <div class="form-group ${status.error ? 'has-error' : ''}">
                 <form:input type="text" path="username" class="form-control" placeholder="Username"
                             autofocus="true"></form:input>
                 <form:errors path="username"></form:errors>
             </div>
         </spring:bind>

         <spring:bind path="password">
             <div class="form-group ${status.error ? 'has-error' : ''}">
                 <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                 <form:errors path="password"></form:errors>
             </div>
         </spring:bind>

         <a href="/login"><button class="btn btn-lg btn-primary btn-block" input type="button">Back</button></a>
         <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
     </form:form>
 </div>

 </body>
 </html>
