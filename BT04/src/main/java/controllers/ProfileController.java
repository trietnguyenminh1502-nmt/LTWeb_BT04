package controllers;

@WebServlet("/update-profile")
@MultipartConfig // Bắt buộc để nhận file
public class ProfileController extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 1. Nhận dữ liệu text
            String fullname = req.getParameter("fullname");
            
            // 2. Xử lý file upload
            Part filePart = req.getPart("image"); // "image" là name bên form
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            
            if(fileName != null && !fileName.isEmpty()){
                // Đường dẫn lưu file (Lưu ý: trong thực tế nên lưu ngoài thư mục deploy)
                String uploadPath = req.getServletContext().getRealPath("/uploads");
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdir();
                
                // Ghi file
                filePart.write(uploadPath + File.separator + fileName);
                
                // Lưu tên file vào User object
                currentUser.setImages(fileName);
            }
            
            // 3. Gọi Service lưu xuống DB
            userService.update(currentUser);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}