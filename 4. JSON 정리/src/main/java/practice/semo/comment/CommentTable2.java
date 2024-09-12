package practice.semo.comment;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import practice.semo.post.PostTable2;
import practice.semo.user.UserTable2;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@ToString
public class CommentTable2 {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="post_id", nullable = false)
    @JsonIgnore
    private PostTable2 post;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserTable2 user;

    private String content;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp created_at;

}
