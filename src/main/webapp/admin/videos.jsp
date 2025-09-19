<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Quản lý Video</title>
    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="bg-light">
<div class="container mt-4">

    <h2 class="mb-4 text-center">Quản lý Video</h2>

    <!-- Form tìm kiếm -->
    <form class="row mb-4" method="get" action="${pageContext.request.contextPath}/admin/video">
        <div class="col-md-10">
            <input type="text" name="q" class="form-control" placeholder="Tìm kiếm video..." value="${param.q}">
        </div>
        <div class="col-md-2">
            <button class="btn btn-primary w-100">Tìm kiếm</button>
        </div>
    </form>

    <!-- Form thêm mới -->
    <div class="card mb-4">
        <div class="card-header bg-success text-white">Thêm Video mới</div>
        <div class="card-body">
            <form method="post" action="${pageContext.request.contextPath}/admin/video">
                <input type="hidden" name="action" value="create"/>

                <div class="mb-3">
                    <label class="form-label">Tiêu đề</label>
                    <input type="text" name="title" class="form-control" required/>
                </div>

                <div class="mb-3">
                    <label class="form-label">URL</label>
                    <input type="text" name="url" class="form-control"/>
                </div>

                <div class="mb-3">
                    <label class="form-label">Category</label>
                    <select name="categoryId" class="form-select" required>
                        <c:forEach var="c" items="${categories}">
                            <option value="${c.id}">${c.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <button type="submit" class="btn btn-success">Thêm Video</button>
            </form>
        </div>
    </div>

    <!-- Danh sách Video -->
    <div class="card">
        <div class="card-header bg-primary text-white">Danh sách Video</div>
        <div class="card-body p-0">
            <table class="table table-striped table-bordered mb-0">
                <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Tiêu đề</th>
                    <th>URL</th>
                    <th>Category</th>
                    <th>Uploader</th>
                    <th>Hành động</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="v" items="${videos}">
                    <tr>
                        <td>${v.id}</td>
                        <td>${v.title}</td>
                        <td><a href="${v.url}" target="_blank">${v.url}</a></td>
                        <td>${v.category.name}</td>
                        <td>${v.uploader.username}</td>
                        <td>
                            <!-- Form update -->
                            <form method="post" action="${pageContext.request.contextPath}/admin/video" class="d-inline">
                                <input type="hidden" name="action" value="update"/>
                                <input type="hidden" name="id" value="${v.id}"/>
                                <input type="text" name="title" value="${v.title}" class="form-control mb-1"/>
                                <input type="text" name="url" value="${v.url}" class="form-control mb-1"/>
                                <select name="categoryId" class="form-select mb-1">
                                    <c:forEach var="c" items="${categories}">
                                        <option value="${c.id}" <c:if test="${v.category.id==c.id}">selected</c:if>>${c.name}</option>
                                    </c:forEach>
                                </select>
                                <button class="btn btn-warning btn-sm">Cập nhật</button>
                            </form>

                            <!-- Form delete -->
                            <form method="post" action="${pageContext.request.contextPath}/admin/video" class="d-inline">
                                <input type="hidden" name="action" value="delete"/>
                                <input type="hidden" name="id" value="${v.id}"/>
                                <button class="btn btn-danger btn-sm" onclick="return confirm('Xóa video này?')">Xóa</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <div class="mt-3 text-center">
        <a href="${pageContext.request.contextPath}/admin/home" class="btn btn-secondary">⬅ Quay về Admin Home</a>
        <a href="${pageContext.request.contextPath}/logout" class="btn btn-outline-danger">Đăng xuất</a>
    </div>
</div>
</body>
</html>
