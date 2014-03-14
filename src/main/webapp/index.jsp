<%@ page import="store.User" %>
<html>
<body>
<h2>Log in, please:</h2>
<%
    User user = (User) session.getAttribute("curUser");
    if (user == null) {
        session.invalidate();
    }
%>

<form name="input1" action="/login" method="POST">
    <input type="text" name="login"/>
    <input type="password" name="password"/>
    <input type="submit" value="Submit"/>
</form>

</body>
</html>
