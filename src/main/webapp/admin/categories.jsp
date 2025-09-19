<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html><body>
<h2>Manage Categories</h2>
<p><a href="${pageContext.request.contextPath}/admin/home">Back</a></p>

<form method="get" action="${pageContext.request.contextPath}/admin/category">
  Search: <input name="q"/>
  <input type="submit" value="Search"/>
</form>

<h3>Add new</h3>
<form method="post" action="${pageContext.request.contextPath}/admin/category">
  <input type="hidden" name="action" value="create"/>
  Name: <input name="name"/>
  Desc: <input name="description"/>
  <input type="submit" value="Add"/>
</form>

<h3>List</h3>
<table border="1">
<tr><th>ID</th><th>Name</th><th>Owner</th><th>Actions</th></tr>
<c:forEach var="c" items="${categories}">
<tr>
  <td>${c.id}</td>
  <td>${c.name}</td>
  <td>${c.owner.username}</td>
  <td>
    <a href="${pageContext.request.contextPath}/admin/category?action=edit&id=${c.id}">Edit</a>
    <form style="display:inline" method="post" action="${pageContext.request.contextPath}/admin/category">
      <input type="hidden" name="action" value="delete"/>
      <input type="hidden" name="id" value="${c.id}"/>
      <input type="submit" value="Delete" onclick="return confirm('Delete?')"/>
    </form>
  </td>
</tr>
</c:forEach>
</table>

<c:if test="${not empty category}">
  <h3>Edit</h3>
  <form method="post" action="${pageContext.request.contextPath}/admin/category">
    <input type="hidden" name="action" value="update"/>
    <input type="hidden" name="id" value="${category.id}"/>
    Name: <input name="name" value="${category.name}"/>
    Desc: <input name="description" value="${category.description}"/>
    <input type="submit" value="Update"/>
  </form>
</c:if>

</body></html>
