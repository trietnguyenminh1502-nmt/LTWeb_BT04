<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">
    <div class="container">
        <a class="navbar-brand fw-bold" href="<c:url value='/home'/>">
            <i class="bi bi-play-circle-fill text-danger"></i> IoTStar Video
        </a>

        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" 
                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" 
                aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="<c:url value='/home'/>">Trang chủ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/videos'/>">Danh sách Video</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" 
                       data-bs-toggle="dropdown" aria-expanded="false">
                        Thể loại
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="<c:url value='/category?type=music'/>">Âm nhạc</a></li>
                        <li><a class="dropdown-item" href="<c:url value='/category?type=tech'/>">Công nghệ</a></li>
                    </ul>
                </li>
            </ul>

            <form class="d-flex me-3" role="search" action="<c:url value='/videos'/>" method="get">
                <input class="form-control me-2" type="search" placeholder="Tìm video..." aria-label="Search" name="keyword">
                <button class="btn btn-outline-success" type="submit">Tìm</button>
            </form>

            <ul class="navbar-nav mb-2 mb-lg-0">
                <c:if test="${sessionScope.account == null}">
                    <li class="nav-item">
                        <a class="nav-link" href="<c:url value='/login'/>">Đăng nhập</a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-primary btn-sm ms-2 mt-1" href="<c:url value='/register'/>">Đăng ký</a>
                    </li>
                </c:if>

                <c:if test="${sessionScope.account != null}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" 
                           data-bs-toggle="dropdown" aria-expanded="false">
                            Xin chào, <b>${sessionScope.account.fullname}</b>
                            <c:if test="${not empty sessionScope.account.images}">
                                <img src="<c:url value='/uploads/${sessionScope.account.images}'/>" 
                                     class="rounded-circle" width="30" height="30" style="object-fit:cover;">
                            </c:if>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="userDropdown">
                            <li><a class="dropdown-item" href="<c:url value='/profile'/>">Cập nhật hồ sơ</a></li>
                            <li><a class="dropdown-item" href="<c:url value='/my-categories'/>">Quản lý danh mục</a></li>
                            <li><hr class="dropdown-divider"></li>
                            
                            <c:if test="${sessionScope.account.role == 'admin'}">
                                <li><a class="dropdown-item text-danger" href="<c:url value='/admin/home'/>">Trang quản trị</a></li>
                            </c:if>
                            
                            <li><a class="dropdown-item" href="<c:url value='/logout'/>">Đăng xuất</a></li>
                        </ul>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>