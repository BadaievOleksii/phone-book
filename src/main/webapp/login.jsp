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
    <title>
        <spring:message code="LoginPage.title"/>
    </title>

    <link href="${contextPath}/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>


<div class="container">
    <div class="col-md-offset-4 col-md-4">
        <form method="POST" action="${contextPath}/login" class="form-signin">
            <h2 class="form-heading">
                <spring:message code="LoginPage.header"/>
            </h2>

            <div class="form-group ${param.error != null ? 'has-error' : ''}">
                <c:if test="${param.error != null}">
                    <span><spring:message code="LoginPage.errorMessage"/></span>
                </c:if>
                <c:if test="${param.logout != null}">
                    <span><spring:message code="LoginPage.logoutMessage"/></span>
                </c:if>


                <spring:message code="LoginPage.usernamePh" var="userPh"/>
                <spring:message code="LoginPage.passwordPh" var="passPh"/>
                <input name="username" id="usernameInput" type="text" class="form-control"
                       placeholder="${userPh}" required autofocus="true"/>
                </br>
                <input name="password" id="passwordInput" type="password" class="form-control"
                       placeholder="${passPh}" required/>
                </br>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <button class="btn btn-lg btn-primary btn-block" type="submit">
                    <spring:message code="LoginPage.loginButton"/>
                </button>
                <h4 class="text-center"><a href="${contextPath}/register">
                    <spring:message code="LoginPage.registerLink"/>
                </a></h4>
            </div>

        </form>
    </div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
 </body>
 </html>