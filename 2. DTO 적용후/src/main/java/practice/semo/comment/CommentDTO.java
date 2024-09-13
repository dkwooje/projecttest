package practice.semo.comment;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import practice.semo.post.PostTable;
import practice.semo.user.UserTable;

import java.sql.Timestamp;

@Data
public class CommentDTO {

    private Long id;
    private PostTable post;
    private UserTable user;
    private String content;
    private Timestamp created_at;


}
