package entity;

import java.io.Serializable;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name = "users")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User  implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "nvarchar(200)")
    private String fullname;
    private String phone;
    private String email;
    private String password;
    private String images; // Lưu tên file ảnh

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Category> categories;
	

}
