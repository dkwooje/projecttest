package practice.semo.comment;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import practice.semo.post.PostTable;
import practice.semo.user.UserTable;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@ToString
public class CommentTable {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="post_id", nullable = false)
    private PostTable post;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserTable user;

    private String content;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp created_at;

}
