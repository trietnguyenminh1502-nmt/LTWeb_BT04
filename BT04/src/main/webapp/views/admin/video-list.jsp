<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<a href="<c:url value='/admin/video/add'/>" class="btn btn-success">Thêm Video</a>

<table class="table">
    <thead>
        <tr>
            <th>ID</th>
            <th>Tiêu đề</th>
            <th>Trạng thái</th>
            <th>Hành động</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${listVideo}" var="v">
            <tr>
                <td>${v.videoId}</td>
                <td>${v.title}</td>
                <td>${v.active ? 'Hiện' : 'Ẩn'}</td>
                <td>
                    <a href="<c:url value='/admin/video/edit?id=${v.videoId}'/>" class="btn btn-primary">Sửa</a>
                    <a href="<c:url value='/admin/video/delete?id=${v.videoId}'/>" class="btn btn-danger">Xóa</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table> 