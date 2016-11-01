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
    <h2 class="center-block">Your phone book</h2>





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
    </br>
    <hr>
    </br>
    <button class="btn btn-lg btn-primary btn-block"
            onclick="location.href='${contextPath}/addrecord';">
        <span class="glyphicon glyphicon-plus"></span>
        Add new record
    </button>

    <div>
        <h4>
            Filter by:
        </h4>
        <form>
            Surname: <input type="text" id="surnameField" class="form-control" value="${param.surname}">
            Name: <input type="text" id="nameField" class="form-control" value="${param.name}">
            Mobile phone: <input type="text" id="mobileField" class="form-control" value="${param.mobile}">
        </form>

        </br>
        </br>

        <button type="button" class="btn btn-primary btn-lg"
                onclick="filter('surnameField', 'nameField', 'mobileField')">
            <span class="glyphicon glyphicon-search"></span>
            Filter
        </button>
        <button type="button" class="btn btn-primary btn-lg"
                onclick="location.href='?'">
            <span class="glyphicon glyphicon-align-justify"></span>
            Clear filter
        </button>
    </div>


</div>
<script>
    function filter(surnameField, nameField, mobileField){
        surname = document.getElementById(surnameField).value;
        name = document.getElementById(nameField).value;
        mobile = document.getElementById(mobileField).value;
        window.location.href = '?surname='+surname+'&name='+name+'&mobile='+mobile;
    }

</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</body>
</html>