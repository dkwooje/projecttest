package practice.semo.comment;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import practice.semo.post.PostRepository;
import practice.semo.post.PostTable;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    public void commentWrite(Long post_id ,String content) {

        PostTable post = postRepository.findById(post_id)
                .orElseThrow(()-> new IllegalArgumentException("NO POST ID"));

        CommentTable table = new CommentTable();
        table.setPost(post);
        table.setContent(content);
        commentRepository.save(table);
    }

    public void AutoWrite(Long post_id, String content){

        PostTable post = postRepository.findById(post_id)
                .orElseThrow(()->  new IllegalArgumentException("NO POST ID AUTO"));

        for(int i = 0; i<5000 ; i++){
            CommentTable table = new CommentTable();
            String autoGeneratedContent = (i+1)+"번쨰 댓글 작성";

           table.setPost(post);
           table.setContent(autoGeneratedContent);
           commentRepository.save(table);
        }
    }
}
