<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Đăng xuất</title>
    <meta http-equiv="refresh" content="3;url=${pageContext.request.contextPath}/login" />
</head>
<body>
    <h2>Bạn đã đăng xuất thành công!</h2>
    <p>Hệ thống sẽ tự động quay về trang đăng nhập sau 3 giây...</p>
    <p>Nếu không tự chuyển, <a href="${pageContext.request.contextPath}/login">bấm vào đây</a>.</p>
</body>
</html>
