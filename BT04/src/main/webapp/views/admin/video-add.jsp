<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="insert" method="post">
    ID: <input type="text" name="videoId"><br>
    Tiêu đề: <input type="text" name="title"><br>
    Mô tả: <textarea name="description"></textarea><br>
    Trạng thái: 
    <select name="active">
        <option value="1">Hoạt động</option>
        <option value="0">Khóa</option>
    </select><br>
    <button type="submit">Lưu</button>
</form>
</body>
</html>