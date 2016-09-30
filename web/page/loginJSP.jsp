<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<c:if test="${authorized eq true}">
    Authorized !
</c:if>

<c:if test="${authorized eq false}">
    Authorization Error
</c:if>

</body>
</html>
