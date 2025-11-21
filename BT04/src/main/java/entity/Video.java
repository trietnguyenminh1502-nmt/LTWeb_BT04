package entity;

@Entity
@Table(name = "videos")
public class Video implements Serializable {
    @Id
    private String videoId;
    private String title;
    private String description;
    private String poster; // Lưu tên file ảnh

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
}
