<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Phone book records</title>

    <link href="${contextPath}/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">



</head>
<body>
<div class="container">

    <table class="table">
        <thead>
        <tr>
            <th>Surname</th>
            <th>Name</th>
            <th>Patronymic</th>
            <th>Mobile phone number</th>
            <th>Home phone number</th>
            <th>Address</th>
            <th>E-mail</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${not empty records}">

            <c:forEach var="record" items="${records}">
                <tr>
                    <td>${record.surname}</td>
                    <td>${record.name}</td>
                    <td>${record.patronymic}</td>
                    <td>${record.mobilePhone}</td>
                    <td>${record.homePhone}</td>
                    <td>${record.address}</td>
                    <td>${record.email}</td>
                    <td>
                        <div class="btn-group">
                            <button type="button" class="btn btn-primary btn-xs"
                                    onclick="location.href='${contextPath}/updaterecord?id=${record.recordId}';">
                                <span class="glyphicon glyphicon-cog"></span>
                            </button>
                            <button type="button" class="btn btn-primary btn-xs"
                                    onclick="location.href='${contextPath}/deleterecord?id=${record.recordId}';">
                                <span class="glyphicon glyphicon-remove"></span>
                            </button>
                        </div>
                    </td>

                </tr>
            </c:forEach>

        </c:if>
        </tbody>
    </table>
    <button class="btn btn-lg btn-primary btn-block"
            onclick="location.href='${contextPath}/addrecord';">
        <span class="glyphicon glyphicon-plus"></span>
        Add new record
    </button>


</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>