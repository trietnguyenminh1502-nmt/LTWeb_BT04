package entity;

@Entity
@Table(name = "categories")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    private String categoryName;

    // Quan hệ n-1 với User
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    // Quan hệ 1-n với Video
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Video> videos;
}
