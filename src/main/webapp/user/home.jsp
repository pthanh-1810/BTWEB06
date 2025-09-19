<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html><body>
<h2>User Home</h2>
<p><a href="${pageContext.request.contextPath}/logout">Logout</a></p>

<h3>All Categories</h3>
<table border="1">
<tr><th>ID</th><th>Name</th><th>Owner</th></tr>
<c:forEach var="c" items="${categories}">
<tr><td>${c.id}</td><td>${c.name}</td><td>${c.owner.username}</td></tr>
</c:forEach>
</table>
</body></html>
