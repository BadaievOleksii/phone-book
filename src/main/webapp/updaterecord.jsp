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
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Update record</title>

    <link href="${contextPath}/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">


</head>

<body>


<!--

    private String surname;
    private String name;
    private String patronymic;
    private String mobilePhone;
    private String homePhone;
    private String address;
    private String email;
-->

<div class="container">

    <div class="col-md-offset-3 col-md-6">
        <form:form method="POST" modelAttribute="recordForm" class="form-signin">
            <h2 class="form-signin-heading">Updating record:</h2>

            <h4>Surname:</h4>
            <spring:bind path="surname">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="surname" class="form-control" autofocus="true"></form:input>
                    <form:errors path="surname"></form:errors>
                </div>
            </spring:bind>

            <h4>Name:</h4>
            <spring:bind path="name">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="name" class="form-control"></form:input>
                    <form:errors path="name"></form:errors>
                </div>
            </spring:bind>

            <h4>Patronymic:</h4>
            <spring:bind path="patronymic">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="patronymic" class="form-control"></form:input>
                    <form:errors path="patronymic"></form:errors>
                </div>
            </spring:bind>

            <h4>Mobile phone number:</h4>
            <spring:bind path="mobilePhone">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="mobilePhone" class="form-control"></form:input>
                    <form:errors path="mobilePhone"></form:errors>
                </div>
            </spring:bind>

            <h4>Home phone number:</h4>
            <spring:bind path="homePhone">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="homePhone" class="form-control"></form:input>
                    <form:errors path="homePhone"></form:errors>
                </div>
            </spring:bind>

            <h4>Address:</h4>
            <spring:bind path="address">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="address" class="form-control"></form:input>
                    <form:errors path="address"></form:errors>
                </div>
            </spring:bind>

            <h4>E-mail:</h4>
            <spring:bind path="email">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="email" class="form-control"></form:input>
                    <form:errors path="email"></form:errors>
                </div>
            </spring:bind>




            <spring:bind path="recordId">
                    <form:input type="hidden" path="recordId"></form:input>
            </spring:bind>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Update</button>
            <h4 class="text-center"><a href="${contextPath}/viewdata">Cancel</a></h4>

        </form:form>

    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="${contextPath}/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>