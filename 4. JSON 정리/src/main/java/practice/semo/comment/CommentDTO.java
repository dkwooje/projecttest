package practice.semo.comment;

import lombok.Data;
import practice.semo.post.PostTable2;
import practice.semo.user.UserTable2;

import java.sql.Timestamp;

@Data
public class CommentDTO {

    private Long id;
    private PostTable2 post;
    private UserTable2 user;
    private String content;
    private Timestamp created_at;


}
