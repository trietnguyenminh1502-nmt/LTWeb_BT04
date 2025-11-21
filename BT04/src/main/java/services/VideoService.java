package vn.iotstar.services;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JpaUtils;
import vn.iotstar.entity.Video;

public class VideoService {

    // 1. Lấy danh sách tất cả Video
    public List<Video> findAll() {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            TypedQuery<Video> query = em.createQuery("SELECT v FROM Video v", Video.class);
            return query.getResultList();
        } finally {
            em.close(); // Luôn đóng kết nối sau khi dùng
        }
    }

    // 2. Tìm Video theo ID
    public Video findById(String videoId) {
        EntityManager em = JpaUtils.getEntityManager();
        try {
            return em.find(Video.class, videoId);
        } finally {
            em.close();
        }
    }

    // 3. Thêm mới Video
    public void create(Video video) {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();     // Bắt đầu giao dịch
            em.persist(video); // Lưu vào DB
            trans.commit();    // Xác nhận thay đổi
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();  // Hoàn tác nếu lỗi
            throw e;
        } finally {
            em.close();
        }
    }

    // 4. Cập nhật Video
    public void update(Video video) {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(video); // Cập nhật
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // 5. Xóa Video
    public void delete(String videoId) throws Exception {
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            Video video = em.find(Video.class, videoId);
            if (video != null) {
                em.remove(video); // Xóa
            } else {
                throw new Exception("Không tìm thấy Video");
            }
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}