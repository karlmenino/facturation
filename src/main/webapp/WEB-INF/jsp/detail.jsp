<%--
  Created by IntelliJ IDEA.
  User: CDI
  Date: 16/11/2018
  Time: 12:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>detail</title>
</head>
<body>
<h1>detail</h1>
<p />
<hr width="100%" />

<table border="1" >
    <tr><th>Nom</th><th>Prénom</th><th>Ville</th><th>Pays</th></tr>
    <form method="post" action="/detail.jsp">
        <tr>
            <td><input type="text" id="nom" name="nom" value="${client.nom}" size="20" maxlength="60" readonly /> </td>
            <td> <input type="text" id="pnom" name="pnom" value="${client.pnom} " size="20" maxlength="60" readonly /></td>
            <td> <input type="text" id="loc" name="loc" value="${client.loc}" size="20" maxlength="60" /> </td>
            <td> <input type="text" id="pays" name="pays" value="${client.pays}" size="20" maxlength="60" /> </td>
            <td><input type="submit" value="changer"/></td>
        </tr>
    </form>
</table>


</body>
</html>
