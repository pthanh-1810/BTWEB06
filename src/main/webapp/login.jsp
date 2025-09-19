<%@ page contentType="text/html; charset=UTF-8" %>
<html><body>
<h2>Login</h2>
<form method="post" action="${pageContext.request.contextPath}/login">
  Username: <input name="username"/><br/>
  Password: <input type="password" name="password"/><br/>
  <input type="submit" value="Login"/>
</form>
<c:if test="${not empty error}">
  <div style="color:red">${error}</div>
</c:if>
</body></html>
