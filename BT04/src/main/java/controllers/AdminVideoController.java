package vn.iotstar.controllers;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.entity.Video;
import vn.iotstar.services.VideoService;

// Định nghĩa các URL mà Servlet này sẽ bắt
@WebServlet(urlPatterns = {
    "/admin/videos",         // Danh sách
    "/admin/video/add",      // Form thêm mới
    "/admin/video/insert",   // Xử lý thêm mới
    "/admin/video/edit",     // Form sửa
    "/admin/video/update",   // Xử lý sửa
    "/admin/video/delete"    // Xử lý xóa
})
public class AdminVideoController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private VideoService videoService = new VideoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy URL hiện tại để kiểm tra
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");

        if (url.contains("videos")) {
            // 1. HIỂN THỊ DANH SÁCH
            List<Video> list = videoService.findAll();
            req.setAttribute("listVideo", list);
            req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
            
        } else if (url.contains("add")) {
            // 2. HIỂN THỊ FORM THÊM MỚI
            req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);
            
        } else if (url.contains("edit")) {
            // 3. HIỂN THỊ FORM SỬA (Lấy dữ liệu cũ lên form)
            String id = req.getParameter("id");
            Video video = videoService.findById(id);
            req.setAttribute("video", video);
            req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
            
        } else if (url.contains("delete")) {
            // 4. XỬ LÝ XÓA (Gọi Service xóa rồi quay lại trang list)
            String id = req.getParameter("id");
            try {
                videoService.delete(id);
                req.setAttribute("message", "Xóa thành công!");
            } catch (Exception e) {
                req.setAttribute("error", "Lỗi: " + e.getMessage());
            }
            // Load lại danh sách
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");

        if (url.contains("insert")) {
            // 5. XỬ LÝ THÊM MỚI (Nhận dữ liệu từ Form Add)
            String videoId = req.getParameter("videoId");
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            int active = Integer.parseInt(req.getParameter("active"));
            
            // Tạo đối tượng Video
            Video video = new Video();
            video.setVideoId(videoId);
            video.setTitle(title);
            video.setDescription(description);
            video.setActive(active == 1);
            video.setPoster("default.jpg"); // Tạm thời set cứng, bạn xử lý Upload file sau
            
            videoService.create(video);
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
            
        } else if (url.contains("update")) {
            // 6. XỬ LÝ CẬP NHẬT (Nhận dữ liệu từ Form Edit)
            String videoId = req.getParameter("videoId");
            String title = req.getParameter("title");
            String description = req.getParameter("description");
            int active = Integer.parseInt(req.getParameter("active"));
            
            Video video = new Video();
            video.setVideoId(videoId);
            video.setTitle(title);
            video.setDescription(description);
            video.setActive(active == 1);
            // Lưu ý: Nếu làm upload ảnh, cần giữ ảnh cũ nếu không chọn ảnh mới
            video.setPoster("default.jpg"); 
            
            videoService.update(video);
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        }
    }
}