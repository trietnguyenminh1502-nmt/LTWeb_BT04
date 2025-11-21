<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<head>
    <title>Thông tin cá nhân</title>
</head>

<body>
    <div class="container mt-5 mb-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card shadow-sm">
                    <div class="card-header bg-primary text-white">
                        <h4 class="mb-0">Cập nhật hồ sơ</h4>
                    </div>
                    <div class="card-body">
                        
                        <c:if test="${not empty message}">
                            <div class="alert alert-success">${message}</div>
                        </c:if>
                        <c:if test="${not empty error}">
                            <div class="alert alert-danger">${error}</div>
                        </c:if>

                        <form action="<c:url value='/update-profile'/>" method="post" enctype="multipart/form-data">
                            
                            <div class="row">
                                <div class="col-md-4 text-center mb-3">
                                    <div class="mb-3">
                                        <c:choose>
                                            <c:when test="${not empty sessionScope.account.images}">
                                                <img src="<c:url value='/uploads/${sessionScope.account.images}'/>" 
                                                     class="img-thumbnail rounded-circle" 
                                                     style="width: 150px; height: 150px; object-fit: cover;" 
                                                     alt="Avatar">
                                            </c:when>
                                            <c:otherwise>
                                                <img src="https://via.placeholder.com/150" 
                                                     class="img-thumbnail rounded-circle" 
                                                     alt="Default Avatar">
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    
                                    <label for="image" class="form-label fw-bold">Đổi Avatar</label>
                                    <input type="file" class="form-control form-control-sm" name="image" id="image" accept="image/*">
                                </div>

                                <div class="col-md-8">
                                    <div class="mb-3">
                                        <label for="fullname" class="form-label">Họ và tên:</label>
                                        <input type="text" class="form-control" id="fullname" name="fullname" 
                                               value="${sessionScope.account.fullname}" required>
                                    </div>

                                    <div class="mb-3">
                                        <label for="phone" class="form-label">Số điện thoại:</label>
                                        <input type="tel" class="form-control" id="phone" name="phone" 
                                               value="${sessionScope.account.phone}">
                                    </div>

                                    <div class="mb-3">
                                        <label for="email" class="form-label">Email:</label>
                                        <input type="email" class="form-control" id="email" 
                                               value="${sessionScope.account.email}" disabled readonly>
                                        <div class="form-text">Email không thể thay đổi.</div>
                                    </div>

                                    <hr>
                                    
                                    <div class="d-flex justify-content-end">
                                        <a href="<c:url value='/home'/>" class="btn btn-secondary me-2">Hủy</a>
                                        <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                        </div>
                </div>
            </div>
        </div>
    </div>
</body>