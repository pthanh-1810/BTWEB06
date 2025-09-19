<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html><body>
<h2>Manage Users</h2>
<p><a href="${pageContext.request.contextPath}/admin/home">Back</a></p>

<form method="get" action="${pageContext.request.contextPath}/admin/user">
  Search: <input name="q"/>
  <input type="submit" value="Search"/>
</form>

<h3>Add new</h3>
<form method="post" action="${pageContext.request.contextPath}/admin/user">
  <input type="hidden" name="action" value="create"/>
  Username: <input name="username"/>
  Password: <input name="password"/>
  RoleId: <select name="roleId"><option value="1">User</option><option value="2">Manager</option><option value="3">Admin</option></select>
  <input type="submit" value="Add"/>
</form>

<h3>List</h3>
<table border="1">
<tr><th>ID</th><th>Username</th><th>Role</th><th>Actions</th></tr>
<c:forEach var="u" items="${users}">
<tr>
  <td>${u.id}</td><td>${u.username}</td><td>${u.roleId}</td>
  <td>
    <!-- implement edit/delete similar to categories -->
  </td>
</tr>
</c:forEach>
</table>
</body></html>
