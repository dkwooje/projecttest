package practice.semo.post;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import practice.semo.user.UserTable;

import java.sql.Timestamp;

@Data
public class PostDTO {


    private Long id;
    private UserTable user;
    private String title;
    private String content;
    private Timestamp created_at;
    private Timestamp updated_at;



}
