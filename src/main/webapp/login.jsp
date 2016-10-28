<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sign in</title>

    <link href="${contextPath}/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<!--
<form action="/login" method="post">
    <div><label> User Name : <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <div><input type="submit" value="Sign In"/></div>
</form>

<div th:inline="text">
    <a href="/register">Register</a>
</div>
-->

<!--
<div class="container">

    <form action="${contextPath}/login" method="post" class="form-signin">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input type="text" name="username" id="inputEmail" class="form-control" placeholder="Username" required autofocus>
        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required>
         <input class="btn btn-lg btn-primary btn-block" type="submit" value="Sign In"/>
     </form>

 </div>

-->

<div class="container">
    <div class="col-md-offset-4 col-md-4">
        <form method="POST" action="${contextPath}/login" class="form-signin">
            <h2 class="form-heading">Log in</h2>

            <div class="form-group ${error != null ? 'has-error' : ''}">
                <span>${message}</span>
                <span>${error}</span>


                <input name="username" id="usernameInput" type="text" class="form-control"
                       placeholder="Username" required autofocus="true"/>
                </br>
                <input name="password" id="passwordInput" type="password" class="form-control"
                       placeholder="Password" required/>
                </br>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
                <h4 class="text-center"><a href="${contextPath}/register">Create an account</a></h4>
            </div>

        </form>
    </div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
 </body>
 </html>