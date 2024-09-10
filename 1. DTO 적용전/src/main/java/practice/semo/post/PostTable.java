package practice.semo.post;


import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import practice.semo.user.UserTable;


import java.sql.Timestamp;

@Getter
@Setter
@Entity
@ToString
public class PostTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserTable user;

    @Column(nullable = false,unique = true)
    private String title;

    @Column(nullable = false)
    @Lob
    private String content;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp created_at;

    @Column(nullable = false)
    @UpdateTimestamp
    private Timestamp updated_at;


}
