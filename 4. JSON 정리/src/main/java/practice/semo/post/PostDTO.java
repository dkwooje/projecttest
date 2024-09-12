package practice.semo.post;

import lombok.Data;
import practice.semo.user.UserTable2;

import java.sql.Timestamp;

@Data
public class PostDTO {


    private Long id;
    private UserTable2 user;
    private String title;
    private String content;
    private Timestamp created_at;
    private Timestamp updated_at;



}
